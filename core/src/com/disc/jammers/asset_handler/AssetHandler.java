/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.asset_handler;

import com.badlogic.gdx.assets.AssetManager;

/**
 *
 * @author daniel
 */
public class AssetHandler {
    
    private final AssetManager assetManager;

    public AssetHandler() {
        assetManager = new AssetManager();
    }
    
    public AssetManager getAssetManager(){
        return assetManager;
    }
    
    
}
