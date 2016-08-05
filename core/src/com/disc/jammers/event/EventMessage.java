/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daniel
 */


public class EventMessage {

    private final Map<EventType, Object> messageInput;

    public EventMessage(EventType type, Object obj) {
        messageInput = new HashMap<EventType, Object>();
        messageInput.put(type, obj);
    }
    
    public Map<EventType, Object> getMap(){
        return messageInput;
    }
    
    public int getInt(EventType type){
        return (Integer)messageInput.get(type);
    }
    
    public int[] getIntArray(EventType type){
        return (int[]) messageInput.get(type);
    }
}

