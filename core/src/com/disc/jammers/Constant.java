/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers;

/**
 *
 * @author daniel
 */
public class Constant {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    public static final String TITLE = "Disc Jammers";
    public static final float GRAVITY_FORCE = -9.81f;
    
    //Box @D Const
    public static final float PIXEL_PER_METER = 100;
    
    public static final short BIT_GROUND = 2;
    public static final short BIT_BALL = 4;
    public static final short BIT_PLAYERS = 8;
    public static final short BIT_OUTER_BOUNDS = 12;
    public static final short BIT_NET = 16;
    
    //Fixture ID
    public static final String GROUND = "ground";
    public static final String DISC = "disc";
    public static final String PLAYER_A = "playerA";
    public static final String PLAYER_B = "playerB";
}

