/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import com.disc.jammers.boxsprite.BoxSprite;

/**
 *
 * @author daniel
 */
public class EventObject {

    private Object myObject;
    private BoxSprite boxSprite;
    
    public EventObject() {
        myObject = new Object();
    }

    public Object getObject() {
        return myObject;
    }

    public void setObject(Object gameObeject) {
        this.myObject = gameObeject;
    }

    public void setBoxSprite(BoxSprite boxSprite) {
        this.boxSprite = boxSprite;
    }

    public BoxSprite getBoxSprite() {
        return boxSprite;
    }
    
}
