/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.states;

import com.disc.jammers.event.MyInputProcessor;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import com.disc.jammers.GameManager;
import com.disc.jammers.GameStateManager;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventObject;
import com.disc.jammers.event.EventQueue;
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
    private GameManager manager;

    private EventQueue eventQueue;

    public StatePlay(GameStateManager gsm) {
        super(gsm);
        eventQueue = new EventQueue();

        camera.setToOrtho(false, WIDTH, HEIGHT);
        Gdx.input.setInputProcessor(new MyInputProcessor(eventQueue));

        //Box2d Initialization
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new MyContactListener(eventQueue));
        b2dr = new Box2DDebugRenderer();

        manager = new GameManager(world, eventQueue);
        //--Sprites

        createBoxBoundaries();
        tempBackground = new Texture("court.png");
    }

    @Override
    public void handleEvents() {
        manager.handleEvents();
    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
        camera.update();

        manager.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        debugMatrix = sb.getProjectionMatrix().cpy().scale(PIXEL_PER_METER, PIXEL_PER_METER, 0);
        b2dr.render(world, debugMatrix);

        manager.render(sb);
    }

    @Override
    public void dispose() {
        manager.dispose();
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
        fdef.filter.categoryBits = Constant.BIT_BOUNDARY;
        fdef.filter.maskBits = Constant.BIT_PLAYERS | Constant.BIT_DISC;

        bottomBody.createFixture(fdef).setUserData(Constant.BOUNDARY);
        
        float[] boxPosition = {WIDTH / 2, 100};      
        EventObject botObjPos = new EventObject();
        botObjPos.setObject(boxPosition);
        eventQueue.addEvent(new EventMessage(EventType.LOWER_BOUNDARY_POSITION, botObjPos));
        float[] boxSize = {300, 2};
        EventObject botUpObjSize = new EventObject();
        botUpObjSize.setObject(boxSize);
        eventQueue.addEvent(new EventMessage(EventType.LOWER_BOUNDARY_SIZE, botUpObjSize));
        

        //Top Part
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, (HEIGHT - 100) / PIXEL_PER_METER);
        Body topBody = world.createBody(bodyDef);
        topBody.createFixture(fdef).setUserData(Constant.BOUNDARY);;

        float[] boxPosition2 = {WIDTH / 2, HEIGHT - 100};
        EventObject upObjPos = new EventObject();
        upObjPos.setObject(boxPosition2);
        eventQueue.addEvent(new EventMessage(EventType.UPPER_BOUNDARY_POSITION, upObjPos));
        eventQueue.addEvent(new EventMessage(EventType.UPPER_BOUNDARY_SIZE, botUpObjSize));
        
        //Left Part
        bodyDef.position.set(100 / PIXEL_PER_METER, (HEIGHT - 240) / PIXEL_PER_METER);
        Body leftBody = world.createBody(bodyDef);
        shape.setAsBox(2 / PIXEL_PER_METER, 150 / PIXEL_PER_METER);
        leftBody.createFixture(fdef).setUserData(Constant.BOUNDARY);;

        //Right Side
        bodyDef.position.set((WIDTH - 100) / PIXEL_PER_METER, (HEIGHT - 240) / PIXEL_PER_METER);
        Body rightBody = world.createBody(bodyDef);
        rightBody.createFixture(fdef).setUserData(Constant.BOUNDARY);;

        //Middle
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, (HEIGHT - 240) / PIXEL_PER_METER);
        Body centerBody = world.createBody(bodyDef);
        fdef.filter.categoryBits = Constant.BIT_BOUNDARY_MID;
        fdef.filter.maskBits = Constant.BIT_PLAYERS;
        centerBody.createFixture(fdef).setUserData(Constant.BOUNDARY);;
        
        float[] boxPosition3 = {WIDTH / 2, HEIGHT - 240};
        float[] boxSize3 = {2, 150};
        EventObject midObjPos = new EventObject();
        midObjPos.setObject(boxPosition3);
        eventQueue.addEvent(new EventMessage(EventType.MID_BOUNDARY_POSITION, midObjPos));
        EventObject midObjSize = new EventObject();
        midObjSize.setObject(boxSize3);
        eventQueue.addEvent(new EventMessage(EventType.MID_BOUNDARY_SIZE, midObjSize));
        
        shape.dispose();
    }
}
