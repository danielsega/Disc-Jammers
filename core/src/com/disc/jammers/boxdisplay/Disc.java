/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import static com.disc.jammers.Constant.HEIGHT;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventType;

/**
 *
 * @author daniel
 */
public class Disc implements BoxDisplay {

    public boolean isCaught;
    public boolean isFlying;

    private int startX;
    private int startY;

    private Body discBody;

    public Disc(World world) {
        createBox2dDisc(world);
    }

    @Override
    public void handleEvents(EventMessage message) {

        if (message.getMap().containsKey(EventType.TOUCH_UP)) {
            discBody.applyForceToCenter(message.getInt(EventType.TOUCH_UP), message.getInt(EventType.TOUCH_UP), isCaught);
        }

        if (isCaught) {

            if (message.getMap().containsKey(EventType.TOUCH_DOWN)) {
                message.getMap().get(EventType.TOUCH_DOWN);
            }

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

    public void setVelocity(float x, float y) {
        discBody.applyForceToCenter(x, y, true);
    }

    private void createBox2dDisc(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((WIDTH / 2) / PIXEL_PER_METER, (HEIGHT / 2) / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        discBody = world.createBody(bodyDef);
        discBody.setLinearVelocity(2, 0);

        CircleShape circle = new CircleShape();
        circle.setRadius(20 / PIXEL_PER_METER);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        //fdef.isSensor = true;
        fdef.density = 1f;
        fdef.friction = 0;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = 2;
        discBody.createFixture(fdef).setUserData(Constant.DISC);

        circle.dispose();
    }
}
