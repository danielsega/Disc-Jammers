/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import com.disc.jammers.boxsprite.BoxSprite;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author daniel
 */


public class EventMessage {

    private final Map<EventType, EventObject> messageInput;

    public EventMessage(EventType type, EventObject obj) {
        messageInput = new EnumMap<EventType, EventObject>(EventType.class);
        messageInput.put(type, obj);
    }
    
    public Map<EventType, EventObject> getMap(){
        return messageInput;
    }
    
    public Object getObject(EventType type){
        return messageInput.get(type).getObject();
    }
    
    public int getInt(EventType type){
        return (Integer)messageInput.get(type).getObject();
    }
    
    public int[] getIntArray(EventType type){
        return (int[]) messageInput.get(type).getObject();
    }
    
    public float getFloat(EventType type){
        return (Float) messageInput.get(type).getObject();
    }
    
    public float[] getFloatArray(EventType type){
        return (float[]) messageInput.get(type).getObject();
    }
    
    public boolean getBoolean(EventType type){
        return (Boolean) messageInput.get(type).getObject();
    }
    
    public BoxSprite getSprite(EventType type){
        return messageInput.get(type).getBoxSprite();
    }
    
}

