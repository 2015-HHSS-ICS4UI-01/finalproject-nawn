/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Walter
 */
public class Bullet extends Entity {

    private float velocityx;
    private float velocityy;

    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update(float delta) {

    }

    public float getx() {
        return super.getX();
    }

    public float gety() {
        return super.getY();
    }

}
