/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxsprite.court;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.disc.jammers.boxsprite.BoxSprite;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;

/**
 *
 * @author daniel
 */
public class Court extends BoxSprite{

    protected Body upperBound;
    protected Body lowerBound;
    protected Body midBound;
    
    public Court(EventQueue queue, AssetManager manager) {
        super(queue, manager);
    }

    @Override
    public void handleEvents(EventMessage message) {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void init() {
    }
    
}
