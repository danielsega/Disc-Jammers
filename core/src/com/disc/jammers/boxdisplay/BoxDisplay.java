/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.disc.jammers.event.EventMessage;

/**
 *
 * @author daniel
 */
public interface BoxDisplay {

    public void handleEvents(EventMessage message);

    public void update(float dt);

    public void render(SpriteBatch sb);

    public void dispose();
    
}
