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
public class Bullet extends Entity {

    /**
     *
     * @param x x position of bullet
     * @param y y position of bullet
     * @param width width of bullet
     * @param height height of bullet
     */
    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update(float delta) {

    }

    /**
     *
     * @return x position of bullet
     */
    public float getx() {
        return super.getX();
    }

    /**
     *
     * @return y position of bullet
     */
    public float gety() {
        return super.getY();
    }

}
