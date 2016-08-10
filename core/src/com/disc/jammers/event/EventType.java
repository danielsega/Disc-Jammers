/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.event;

/**
 *
 * @author daniel
 */
public enum EventType {
    //--Inputs Events
    TOUCH_UP,
    TOUCH_DOWN,
    TOUCH_DRAGGED,
    KEY_UP,
    KEY_DOWN,
    
    //--Game Events
    THROW_DISC,
    STOP_DISC,
    PLAYER_A_HAS_DISC,
    PLAYER_B_HAS_DISC,
}
