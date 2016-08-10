/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import static com.disc.jammers.Constant.HEIGHT;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.event.EventType;

/**
 *
 * @author daniel
 */
public class Disc extends BoxDisplay {

    public boolean isCaught;
    public boolean isFlying;

    private int startX;
    private int startY;

    private final String picFileName = "disc.png";
    
    private Body discBody;

    private Texture discTex;
    private Sprite discSprite;
    
    private World world;
    
    public Disc(World world, EventQueue queue, AssetManager manager) {
        super(queue, manager);
        
        this.world = world;
        assetDesc = new AssetDescriptor(picFileName, Texture.class);
    }

    @Override
    public void handleEvents(EventMessage message) {

        if (message.getMap().containsKey(EventType.TOUCH_UP)) {
            discBody.setLinearVelocity(message.getIntArray(EventType.TOUCH_UP)[0] / PIXEL_PER_METER, message.getIntArray(EventType.TOUCH_UP)[1] / PIXEL_PER_METER);
            isCaught = false;
        }

        if (message.getMap().containsKey(EventType.STOP_DISC)) {
            isCaught = true;
            discBody.setAngularVelocity(0);
            discBody.setLinearVelocity(0,0);
        }
    }

    @Override
    public void update(float dt) {
        
        discSprite.setPosition((discBody.getPosition().x * PIXEL_PER_METER) - (discSprite.getWidth() / 2)
                , (discBody.getPosition().y * PIXEL_PER_METER) - (discSprite.getHeight() / 2));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        discSprite.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
    }

    public void setVelocity(float x, float y) {
        discBody.applyForceToCenter(x, y, true);
    }


    @Override
    public void init() {
        discTex = assetManager.get(picFileName);
        discSprite = new Sprite(discTex);
        
        createBox2dDisc(this.world);
        initDiscSprite();
    }

    
    private void createBox2dDisc(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, (HEIGHT / 2) / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        discBody = world.createBody(bodyDef);
        discBody.setLinearVelocity(2, 0);

        CircleShape circle = new CircleShape();
        circle.setRadius(((discSprite.getWidth() / 4) + (discSprite.getHeight()/ 4)) / PIXEL_PER_METER);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        //fdef.isSensor = true;
        fdef.density = 1f;
        fdef.friction = 0;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = Constant.BIT_DISC;
        fdef.filter.maskBits = Constant.BIT_PLAYERS | Constant.BIT_BOUNDARY;
        fixture = discBody.createFixture(fdef);
        fixture.setUserData(Constant.DISC);
        
        circle.dispose();
    }
    
    private void initDiscSprite(){
        discSprite.setOriginCenter();
    }
}
