/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import com.disc.jammers.boxsprite.BoxSprite;
import com.disc.jammers.boxsprite.Disc;
import com.disc.jammers.boxsprite.players.PlayerScott;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.states.StateID;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class AssetDirector {

    private World world;
    private EventQueue queue;

    private ArrayList<BoxSprite> boxesDisplay;
    private AssetManager assetManager;

    public AssetDirector(World world, EventQueue queue, AssetManager manager) {
        boxesDisplay = new ArrayList<BoxSprite>();
        this.world = world;
        this.queue = queue;
        this.assetManager = manager;
    }

    public ArrayList<BoxSprite> getBoxDisplyByState(StateID id) {
        initArrayList(id);
        return boxesDisplay;
    }

    private void initArrayList(StateID id) {
        
        int firstPlayer = MathUtils.random(0,1);
        int secondPlayer;
        
        if(firstPlayer == 0){
            secondPlayer = 1;
        }else{
            secondPlayer = 0;
        }
        
        switch (id) {
            case PLAY:
                //boxesDisplay.add(new Disc(world, queue, assetManager));
                //boxesDisplay.get(0).setUserDate(Constant.DISC);
                
                boxesDisplay.add(new PlayerScott(world, queue, assetManager, firstPlayer));
                boxesDisplay.get(0).setUserDate(Constant.PLAYER_A);                
                
                boxesDisplay.add(new PlayerScott(world, queue, assetManager, secondPlayer));
                boxesDisplay.get(1).setUserDate(Constant.PLAYER_B);
                break;
        }
    }

    private void clearArrayList() {
        boxesDisplay.clear();
    }
}
