/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author daniel
 */
public class EventQueue {

    private Queue<EventMessage> eventQueue;
    
    public EventQueue() {
        eventQueue = new LinkedList<EventMessage>();
    }
    
    public Queue getQueue(){
        return eventQueue;
    }
    
    public void addEvent(EventMessage message){
        if(!eventQueue.contains(message) || eventQueue.contains(EventType.PLAYER_A_TOUCH_DRAGGED)){
            eventQueue.add(message);
        }
    }
    
    public EventMessage sendEvent(){
        return eventQueue.peek();
    }
    
    public void removeEvent(){
        eventQueue.remove();
    }
    
    public void cleaerEvents(){
        eventQueue.clear();
    }
}
