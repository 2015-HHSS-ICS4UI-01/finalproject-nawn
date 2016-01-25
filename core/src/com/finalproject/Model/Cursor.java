/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

/**
 *
 * @author NAWN
 */
public class Cursor {

    private float xpos;
    private float ypos;

    public Cursor(float x, float y) {
        // get the cursors x and y positions
        xpos = x;
        ypos = y;
    }

    public void setx(float x) {
        //set the x position found to the cursors x position
        xpos = x;
    }

    public void sety(float y) {
        //set the x position found to the cursors y position
        ypos = y;
    }

    public float getx() {
        //return the cursors x position
        return xpos;
    }

    public float gety() {
        //return the cursors y position
        return ypos;
    }
}
