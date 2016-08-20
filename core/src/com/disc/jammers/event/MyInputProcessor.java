/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author daniel
 */
public class MyInputProcessor implements InputProcessor {

    private EventQueue eventQueue;
    private EventObject inputObject;
    
    public MyInputProcessor(EventQueue queue) {
        eventQueue = queue;
        inputObject = new EventObject();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int[] mouse = {screenX, screenY, pointer, button};
        inputObject.setObject(mouse);
        eventQueue.addEvent(new EventMessage(EventType.PLAYER_A_TOUCH_DOWN, inputObject));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int[] mouse = {screenX, screenY, pointer, button};        
        inputObject.setObject(mouse);
        eventQueue.addEvent(new EventMessage(EventType.PLAYER_A_TOUCH_UP, inputObject));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int[] mouse = {screenX, screenY, pointer};        
        inputObject.setObject(mouse);
        eventQueue.addEvent(new EventMessage(EventType.PLAYER_A_TOUCH_DRAGGED, inputObject));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
}
