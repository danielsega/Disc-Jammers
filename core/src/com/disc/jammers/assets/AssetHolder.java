/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import com.disc.jammers.boxdisplay.BoxDisplay;
import com.disc.jammers.boxdisplay.Disc;
import com.disc.jammers.boxdisplay.players.PlayerScott;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.states.StateID;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class AssetHolder {

    private World world;
    private EventQueue queue;

    private ArrayList<BoxDisplay> boxesDisplay;
    private AssetManager assetManager;

    public AssetHolder(World world, EventQueue queue, AssetManager manager) {
        boxesDisplay = new ArrayList<BoxDisplay>();
        this.world = world;
        this.queue = queue;
        this.assetManager = manager;
    }

    public ArrayList<BoxDisplay> getBoxDisplyByState(StateID id) {
        initArrayList(id);
        return boxesDisplay;
    }

    private void initArrayList(StateID id) {
        switch (id) {
            case PLAY:
                boxesDisplay.add(new Disc(world, queue, assetManager));
                boxesDisplay.get(0).setUserDate(Constant.DISC);
                boxesDisplay.add(new PlayerScott(world, queue, assetManager));
                boxesDisplay.get(1).setUserDate(Constant.PLAYER_A);
                boxesDisplay.add(new PlayerScott(world, queue, assetManager));
                boxesDisplay.get(2).setUserDate(Constant.PLAYER_B);
                break;
        }
    }

    private void clearArrayList() {
        boxesDisplay.clear();
    }
}
