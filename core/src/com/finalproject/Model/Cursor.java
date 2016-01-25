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
        xpos = x;
        ypos = y;
    }

    public void setx(float x) {
        xpos = x;
    }

    public void sety(float y) {
        ypos = y;
    }

    public float getx() {
        return xpos;
    }

    public float gety() {
        return ypos;
    }
}
