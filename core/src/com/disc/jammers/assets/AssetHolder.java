/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.assets;

import com.badlogic.gdx.physics.box2d.World;
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

    public AssetHolder(World world, EventQueue queue) {
        boxesDisplay = new ArrayList<BoxDisplay>();
        this.world = world;
        this.queue = queue;
    }

    public ArrayList<BoxDisplay> getBoxDisplyByState(StateID id) {
        initArrayList(id);
        return boxesDisplay;
    }

    private void initArrayList(StateID id) {
        switch (id) {
            case PLAY:
                boxesDisplay.add(new Disc(world, queue));
                boxesDisplay.add(new PlayerScott(world, queue));
                break;
        }
    }

    private void clearArrayList() {
        boxesDisplay.clear();
    }
}
