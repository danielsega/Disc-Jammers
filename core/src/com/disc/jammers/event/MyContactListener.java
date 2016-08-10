/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.disc.jammers.Constant;

/**
 *
 * @author daniel
 */
public class MyContactListener implements ContactListener {

    private EventQueue eventQueue;
    
    public MyContactListener(EventQueue queue) {
        eventQueue = queue;
    }

    @Override
    public void beginContact(Contact contact) {
        if(discHitPlayer(contact)){
            if(playerAHasDisc(contact)){
                eventQueue.addEvent(new EventMessage(EventType.PLAYER_A_HAS_DISC, true));
                eventQueue.addEvent(new EventMessage(EventType.STOP_DISC, null));
            }
            
            if(playerBHasDisc(contact)){
                eventQueue.addEvent(new EventMessage(EventType.PLAYER_B_HAS_DISC, true));
                eventQueue.addEvent(new EventMessage(EventType.STOP_DISC, null));
            }
        }
        
        
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
    
    private boolean playerAHasDisc(Contact contact){
        return contact.getFixtureA().getUserData().equals(Constant.PLAYER_A) || contact.getFixtureB().getUserData().equals(Constant.PLAYER_A);
    }
    
    private boolean playerBHasDisc(Contact contact){
        return contact.getFixtureA().getUserData().equals(Constant.PLAYER_B) || contact.getFixtureB().getUserData().equals(Constant.PLAYER_B);
    }
    
    private boolean discHitPlayer(Contact contact){
        System.err.println(contact.getFixtureA().getUserData());
        System.err.println(contact.getFixtureB().getUserData());
        return     contact.getFixtureA().getUserData().equals(Constant.PLAYER_A) 
                || contact.getFixtureA().getUserData().equals(Constant.PLAYER_B)
                && contact.getFixtureB().getUserData().equals(Constant.DISC)
                
                ||
                
                   contact.getFixtureB().getUserData().equals(Constant.PLAYER_A)
                || contact.getFixtureB().getUserData().equals(Constant.PLAYER_B)
                && contact.getFixtureA().getUserData().equals(Constant.DISC)
                ;
    }
    
}
