/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay.players;

import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.boxdisplay.Disc;
import com.disc.jammers.boxdisplay.Player;
import com.disc.jammers.entity.Country;

/**
 *
 * @author daniel
 */
public class PlayerScott extends Player{
    
    public PlayerScott(World world, Disc disc) {
        super(world, disc);
        
        firstName = "Scott";
        lastName  = "Walker";
        
        country = Country.USA;
        
        throwingSpeed = 8;
        curvePower = 4;
        slidePower = 3;        
        dexterity = 3;
        
        overallPower = 8;
        overallPower = 3;
    }
    
}
