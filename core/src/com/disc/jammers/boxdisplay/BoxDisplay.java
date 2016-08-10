/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;

/**
 *
 * @author daniel
 */
public abstract class BoxDisplay {

    protected AssetManager assetManager;
    protected EventQueue eventQueue;
    protected AssetDescriptor assetDesc;
    protected Fixture fixture;
    
    public BoxDisplay(EventQueue queue, AssetManager manager) {
        eventQueue = queue;
        assetManager = manager;
    }

    
    public abstract void handleEvents(EventMessage message);

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
    
    public abstract void init();
    
    public AssetDescriptor getAsset(){
        if(assetDesc != null){
            return assetDesc;
        }
        return null;
    }
    
    public void setUserDate(String userData){
        if(fixture != null){
            fixture.setUserData(userData);
        }
    }
}
