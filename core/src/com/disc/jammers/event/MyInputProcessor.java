/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author daniel
 */
public class MyInputProcessor implements InputProcessor {

    private EventQueue eventQueue;
    
    public MyInputProcessor(EventQueue queue) {
        eventQueue = queue;
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
        eventQueue.addEvent(new EventMessage(EventType.TOUCH_DOWN, mouse));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int[] mouse = {screenX, screenY, pointer, button};        
        eventQueue.addEvent(new EventMessage(EventType.TOUCH_UP, mouse));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int[] mouse = {screenX, screenY, pointer};        
        eventQueue.addEvent(new EventMessage(EventType.TOUCH_DRAGGED, mouse));
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
