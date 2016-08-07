/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.boxdisplay.BoxDisplay;
import com.disc.jammers.boxdisplay.Disc;
import com.disc.jammers.boxdisplay.Player;
import com.disc.jammers.boxdisplay.players.PlayerScott;
import com.disc.jammers.event.EventQueue;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author daniel
 */
public class GameManager {

    private EventQueue eventQueue;
    private LinkedHashMap<String, BoxDisplay> box2dSprite;
    private World world;

    public GameManager(World world, EventQueue queue) {
        box2dSprite = new LinkedHashMap<String, BoxDisplay>();
        eventQueue = queue;
        this.world = world;

        setBox2dSprite();
    }

    public BoxDisplay getBox2dSprite(String id) {
        return box2dSprite.get(id);
    }

    public void handleEvents() {
        while (!eventQueue.getQueue().isEmpty()) {
            for (Map.Entry<String, BoxDisplay> entry : box2dSprite.entrySet()) {
                BoxDisplay value = entry.getValue();
                value.handleEvents(eventQueue.sendEvent());
            }
            eventQueue.removeEvent();
        }
    }

    public void update(float dt) {
        //--Update Every Box2dSprite
        for (Map.Entry<String, BoxDisplay> entry : box2dSprite.entrySet()) {
            BoxDisplay value = entry.getValue();
            value.update(dt);
        }
    }

    public void render(SpriteBatch sb) {
        for (Map.Entry<String, BoxDisplay> entry : box2dSprite.entrySet()) {
            BoxDisplay value = entry.getValue();
            value.render(sb);
        }
    }

    public void dispose() {
        for (Map.Entry<String, BoxDisplay> entry : box2dSprite.entrySet()) {
            BoxDisplay value = entry.getValue();
            value.dispose();
        }
    }

    private void setBox2dSprite() {
        box2dSprite.put(Constant.DISC, new Disc(world, eventQueue));
        box2dSprite.put(Constant.PLAYER_A, new PlayerScott(world, eventQueue));
    }
}
