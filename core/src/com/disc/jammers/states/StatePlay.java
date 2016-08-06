/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.states;

import com.disc.jammers.event.MyInputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import static com.disc.jammers.Constant.WIDTH;
import static com.disc.jammers.Constant.HEIGHT;
import static com.disc.jammers.Constant.PIXEL_PER_METER;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.GameStateManager;
import com.disc.jammers.boxdisplay.Disc;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventType;
import com.disc.jammers.event.MyContactListener;

/**
 *
 * @author daniel
 */
public class StatePlay extends State {

    private World world;
    private Box2DDebugRenderer b2dr;
    private Matrix4 debugMatrix;

    private Texture tempBackground;
    
    private Disc disc;
    
    public StatePlay(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, WIDTH, HEIGHT);
        Gdx.input.setInputProcessor(new MyInputProcessor());
        
        //Box2d Initialization
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new MyContactListener());
        b2dr = new Box2DDebugRenderer();
        
        //--Sprites
        disc = new Disc(world);
        
        createBoxBoundaries();
        tempBackground = new Texture("court.png");
    }

    @Override
    public void handleEvents() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            disc.handleEvents(new EventMessage(EventType.TOUCH_UP, 20));
            System.out.println("Space Bar Pressed");
        }
    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        debugMatrix = sb.getProjectionMatrix().cpy().scale(PIXEL_PER_METER, PIXEL_PER_METER, 0);
        b2dr.render(world, debugMatrix);
        
        //sb.begin();
        //sb.draw(tempBackground, 0, 0);
        //sb.end();
    }

    @Override
    public void dispose() {
    }

    private void createBoxBoundaries() {
        //Bottom Part
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, 100 / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body bottomBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(300 / PIXEL_PER_METER, 2 / PIXEL_PER_METER);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.filter.categoryBits = 2;
        bottomBody.createFixture(fdef).setUserData("ground");

        //Top Part
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, (HEIGHT - 100) / PIXEL_PER_METER);
        Body topBody = world.createBody(bodyDef);
        topBody.createFixture(fdef).setUserData("celling");
        
        //Left Part
        bodyDef.position.set( 100 / PIXEL_PER_METER, (HEIGHT - 240) / PIXEL_PER_METER);
        Body leftBody = world.createBody(bodyDef);
        shape.setAsBox(2 / PIXEL_PER_METER, 150 / PIXEL_PER_METER);
        leftBody.createFixture(fdef).setUserData("left wall");
        
        
        //Right Side
        bodyDef.position.set( (WIDTH - 100) / PIXEL_PER_METER, (HEIGHT - 240) / PIXEL_PER_METER);
        Body rightBody = world.createBody(bodyDef);
        rightBody.createFixture(fdef).setUserData("right wall");
        
        shape.dispose();
    }
}