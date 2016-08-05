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
import com.disc.jammers.entity.Entity;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventType;

/**
 *
 * @author daniel
 */
public class Player extends Entity implements BoxDisplay {

    private Disc disc;

    public Player(World world, Disc disc) {
        this.disc = disc;
        createPlayerBox(world);
    }

    @Override
    public void handleEvents(EventMessage message) {
        if (!disc.isCaught) {
            
            //Move to Location
            if (message.getMap().containsKey(EventType.TOUCH_UP)) {
            }

            //Slide to location
            if (message.getMap().containsKey(EventType.TOUCH_DRAGGED)) {
            }
        }
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
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
