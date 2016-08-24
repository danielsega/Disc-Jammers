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
    private EventObject contactObject;

    public MyContactListener(EventQueue queue) {
        eventQueue = queue;
        contactObject = new EventObject();
    }

    @Override
    public void beginContact(Contact contact) {
        if (discHitPlayer(contact)) {
            if (playerHasDiscChecker(contact, Constant.PLAYER_A)) {
                playerHasDiscAction(EventType.PLAYER_A_HAS_DISC);
            }

            if (playerHasDiscChecker(contact, Constant.PLAYER_B)) {
                playerHasDiscAction(EventType.PLAYER_B_HAS_DISC);
            }
        }

        /*if (playerHitBoundary(contact, Constant.PLAYER_A)) {
            eventQueue.addEvent(new EventMessage(EventType.STOP_PLAYER_A, null));
        }

        if (playerHitBoundary(contact, Constant.PLAYER_B)) {
            eventQueue.addEvent(new EventMessage(EventType.STOP_PLAYER_B, null));
        }*/
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

    private boolean playerHasDiscChecker(Contact contact, final String player) {
        return contact.getFixtureA().getUserData().equals(player) || contact.getFixtureB().getUserData().equals(player);
    }

    private void playerHasDiscAction(EventType type) {
        contactObject.setObject(true);
        eventQueue.addEvent(new EventMessage(type, contactObject));
        eventQueue.addEvent(new EventMessage(EventType.STOP_DISC, null));
    }

    private boolean discHitPlayer(Contact contact) {

        if (contact.getFixtureA().getUserData().equals(Constant.PLAYER_A) || contact.getFixtureA().getUserData().equals(Constant.PLAYER_B))
            if (contact.getFixtureB().getUserData().equals(Constant.DISC)) return true;
        if (contact.getFixtureB().getUserData().equals(Constant.PLAYER_A) || contact.getFixtureB().getUserData().equals(Constant.PLAYER_B))
            if (contact.getFixtureA().getUserData().equals(Constant.DISC)) return true;
        return false;
    }

   /* private boolean playerHitBoundary(Contact contact, final String player) {

        if (contact.getFixtureA().getUserData().equals(player))
            if (contact.getFixtureB().getUserData().equals(Constant.BOUNDARY)) return true;
        if (contact.getFixtureB().getUserData().equals(player))
            if (contact.getFixtureA().getUserData().equals(Constant.BOUNDARY)) return true;
        return false;
    }*/

}
