/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.states;

import com.disc.jammers.GameStateManager;

/**
 *
 * @author daniel
 */
public class StateDirector {
    
    public static StateID currentID;
    private GameStateManager gsm;
    
    public StateDirector(GameStateManager gsm){
        this.gsm = gsm;
    }
    
    public State getState(StateID id){
        switch(id){
            case PLAY:
                currentID = StateID.PLAY;
                return new StatePlay(gsm);
        }
        return null;
    }
}
