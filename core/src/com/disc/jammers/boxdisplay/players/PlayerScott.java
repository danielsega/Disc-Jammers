/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay.players;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import com.disc.jammers.boxdisplay.Disc;
import com.disc.jammers.boxdisplay.Player;
import com.disc.jammers.entity.Country;
import com.disc.jammers.event.EventQueue;

/**
 *
 * @author daniel
 */
public class PlayerScott extends Player {

    public PlayerScott(World world, EventQueue queue) {
        super(world, queue);
        
        initData();
        createPlayerBox(world);
    }

    private void initData(){
        firstName = "Scott";
        lastName = "Walker";

        country = Country.USA;

        throwingSpeed = 8;
        curvePower = 4;
        slidePower = 3;
        dexterity = 3;

        overallPower = 8;
        overallPower = 3;
    }
    
    private void createPlayerBox(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((WIDTH / 4) / PIXEL_PER_METER, (Constant.HEIGHT / 4) / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body bottomBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / PIXEL_PER_METER, 50 / PIXEL_PER_METER);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.filter.categoryBits = 2;
        bottomBody.createFixture(fdef);

        shape.dispose();
    }
}
