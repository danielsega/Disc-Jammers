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
    //Player A
    PLAYER_A_TOUCH_UP,
    PLAYER_A_TOUCH_DOWN,
    PLAYER_A_TOUCH_DRAGGED,
    PLAYER_A_KEY_UP,
    PLAYER_A_KEY_DOWN,
    //Player A
    PLAYER_B_TOUCH_UP,
    PLAYER_B_TOUCH_DOWN,
    PLAYER_B_TOUCH_DRAGGED,
    PLAYER_B_KEY_UP,
    PLAYER_B_KEY_DOWN,
    //--Setup Events
    UPPER_BOUNDARY_POSITION,
    LOWER_BOUNDARY_POSITION,
    MID_BOUNDARY_POSITION,
    UPPER_BOUNDARY_SIZE,
    LOWER_BOUNDARY_SIZE,
    MID_BOUNDARY_SIZE,
    //--Game Events
    THROW_DISC,
    STOP_DISC,
    STOP_PLAYER_A,
    STOP_PLAYER_B,
    PLAYER_A_HAS_DISC,
    PLAYER_B_HAS_DISC,
}
