/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public abstract class BoxDisplay {

    
    protected EventQueue eventQueue;
    protected ArrayList<BoxDisplayResidence> residence;
    protected ArrayList<AssetDescriptor> assetList;
    
    public BoxDisplay(EventQueue queue) {
        eventQueue = queue;
        residence = new ArrayList<BoxDisplayResidence>();
    }

    
    public abstract void handleEvents(EventMessage message);

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
    
    protected ArrayList<AssetDescriptor> getAssetList(){
        if(!assetList.isEmpty()){
            return assetList;
        }
        return null;
    }
}
