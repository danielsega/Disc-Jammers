/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxsprite.players;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import com.disc.jammers.entity.Country;
import com.disc.jammers.event.EventQueue;

/**
 *
 * @author daniel
 */
public class PlayerScott extends Player {

    public PlayerScott(World world, EventQueue queue, AssetManager manager, int player) {
        super(world, queue, manager, player);

        initData();
    }

    private void initData() {
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
}
