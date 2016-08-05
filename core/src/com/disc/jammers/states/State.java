/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.states;

import com.disc.jammers.GameStateManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.disc.jammers.GameStateManager;

/**
 *
 * @author daniel
 */
public abstract class State {
    
    protected OrthographicCamera camera;
    protected Vector3 location;
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        location = new Vector3();
    }
    
    public abstract void handleEvents();
    
    public abstract void update(float dt);
    
    public abstract void render(SpriteBatch sb);
    
    public abstract void dispose();
}
