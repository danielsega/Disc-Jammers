/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.assets.AssetHolder;
import com.disc.jammers.boxdisplay.BoxDisplay;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.states.StateID;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class GameManager {

    private final AssetManager assetManager;

    private EventQueue eventQueue;
    private ArrayList<BoxDisplay> box2dSprites;
    private World world;
    private AssetHolder assetHolder;

    public GameManager(World world, EventQueue queue) {
        assetManager = new AssetManager();
        box2dSprites = new ArrayList<BoxDisplay>();
        assetHolder = new AssetHolder(world, queue, assetManager);

        this.eventQueue = queue;
        this.world = world;

        setBox2dSprite();

        initAssetManager();
    }

    public void handleEvents() {
        while (!eventQueue.getQueue().isEmpty()) {
            for (BoxDisplay sprites : box2dSprites) {
                sprites.handleEvents(eventQueue.sendEvent());
            }
            eventQueue.removeEvent();
        }
    }

    public void update(float dt) {
        //--Update Every Box2dSprite
        for (BoxDisplay sprites : box2dSprites) {
            sprites.update(dt);
        }
    }

    public void render(SpriteBatch sb) {
        for (BoxDisplay sprites : box2dSprites) {
            sprites.render(sb);
        }
    }

    public void dispose() {
        for (BoxDisplay sprites : box2dSprites) {
            sprites.dispose();
        }
    }

    private void setBox2dSprite() {
        box2dSprites = assetHolder.getBoxDisplyByState(StateID.PLAY);
    }

    private void initAssetManager() {
        //Sprites
        for (BoxDisplay sprite : box2dSprites) {
            if (sprite.getAsset() != null) {
                assetManager.load(sprite.getAsset());
            }
        }

        assetManager.finishLoading();
        
        //--TODO: Add Texture Atlas, Add Sound, Add Music
        for (BoxDisplay sprite : box2dSprites) {
            sprite.init();
        }
        
    }
}
