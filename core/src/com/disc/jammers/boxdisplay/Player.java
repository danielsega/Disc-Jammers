/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.entity.Country;
import com.disc.jammers.entity.Level;
import com.disc.jammers.entity.SpecialMoveType;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;

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
    }

    @Override
    public void handleEvents(EventMessage message) {
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

}
