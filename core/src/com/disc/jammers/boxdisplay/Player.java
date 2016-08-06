/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import com.disc.jammers.entity.Country;
import com.disc.jammers.entity.Level;
import com.disc.jammers.entity.SpecialMoveType;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.event.EventType;

/**
 *
 * @author daniel
 */
public class Player extends BoxDisplay {

    protected String firstName;
    protected String lastName;
    
    protected Country country;
    
    protected Level level;
    
    protected int throwingSpeed;   
    protected int curvePower;
    protected int slidePower;
    
    protected int dexterity;
    
    protected int overallSpeed;
    protected int overallPower;
    
    public SpecialMoveType special;
    
    public Player(World world, EventQueue queue) {
        super(queue);
        createPlayerBox(world);
    }

    @Override
    public void handleEvents(EventMessage message) {
            
            //Move to Location
            if (message.getMap().containsKey(EventType.TOUCH_UP)) {
            }

            //Slide to location
            if (message.getMap().containsKey(EventType.TOUCH_DRAGGED)) {
            }
    }

    public void update(float dt) {
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }

    private void createPlayerBox(World world) {
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
        bottomBody.createFixture(fdef);

        shape.dispose();
    }
}
