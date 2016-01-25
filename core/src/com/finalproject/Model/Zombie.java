/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author alimu
 */
public class Zombie extends Entity {

    //variables for the maximum speed of the zombie
    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 2.0f;
    private final float DAMP = 0.8f;

    // movement variables
    private Vector2 velocity;
    private Vector2 acceleration;
    // initialize the directions that the zombie is facing
    private boolean isFacingNW;
    private boolean isFacingNE;
    private boolean isFacingSW;
    private boolean isFacingSE;
    private boolean isAlive = true;
    // animation state counter
    private float stateTime;
    private int health = 100;

    public Zombie(float x, float y, float width, float height) {
        super(x, y, width, height);

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);

        //initialize the booleans of the directions the zombie is facing
        isFacingNE = false;
        isFacingNW = false;
        isFacingSE = false;
        isFacingSW = false;
        //initialize the state time
        stateTime = 0;
    }

    public void update(float delta) {
        //if the zombie is alive
        if (isAlive) {
            //apply the damp effect
            velocity.mulAdd(acceleration, delta);
            velocity.x = velocity.x * DAMP;
            if (velocity.x < 0.01f && velocity.x > -0.01f) {
                velocity.x = 0;
            }
            velocity.y = velocity.y * DAMP;
            if (velocity.y < 0.01f && velocity.y > -0.01f) {
                velocity.y = 0;
            }
            addToPosition(velocity.x, velocity.y);

            //is running ne direction
            if (velocity.y > 0 && velocity.x > 0) {
                //make the ne boolean true and everything else false
                isFacingNE = true;
                isFacingNW = false;
                isFacingSE = false;
                isFacingSW = false;

                //if running in nw direction
            } else if (velocity.y > 0 && velocity.x < 0) {
                //make the nw boolean true and everything else false
                isFacingNW = true;
                isFacingNE = false;
                isFacingSE = false;
                isFacingSW = false;

                //if running se
            } else if (velocity.y < 0 && velocity.x > 0) {
                //make the se boolean true and everything else false
                isFacingSE = true;
                isFacingNE = false;
                isFacingNW = false;
                isFacingSW = false;

                //if running sw
            } else if (velocity.y < 0 && velocity.x < 0) {
                //make the sw boolean true and everything else false
                isFacingSW = true;
                isFacingNE = false;
                isFacingNW = false;
                isFacingSE = false;
            }
        }
    }

    /**
     * set the velocity of the x
     *
     * @param x is the new velocity
     */
    public void setVelocityX(float x) {
        velocity.x = x;
    }

    /**
     * set the velocity of the y
     *
     * @param y is the new velocity
     */
    public void setVelocityY(float y) {
        velocity.y = y;
    }

    /**
     * get the velocity of the x
     *
     * @return the x velocity
     */
    public float getVelocityX() {
        return velocity.x;
    }

    /**
     * get the velocity of the y
     *
     * @return the y velocity
     */
    public float getVelocityY() {
        return velocity.y;
    }

    /**
     * get the stateTime
     *
     * @return the stateTime
     */
    public float getStateTime() {
        return stateTime;
    }

    /**
     * if the zombie is facing NW
     *
     * @return whether the zombie is facing NW or not
     */
    public boolean isFacingNW() {
        return isFacingNW;
    }

    /**
     * if the zombie is facing NE
     *
     * @return whether the zombie is facing NE or not
     */
    public boolean isFacingNE() {
        return isFacingNE;
    }

    /**
     * if the zombie is facing SE
     *
     * @return whether the zombie is facing SE or not
     */
    public boolean isFacingSE() {
        return isFacingSE;
    }

    /**
     * if the zombie is facing SW
     *
     * @return whether the zombie is facing SW or not
     */
    public boolean isFacingSW() {
        return isFacingSW;
    }

    /**
     * if zombie is alive or not
     *
     * @param a the new boolean
     */
    public void Alive(boolean a) {
        isAlive = a;
    }

    /**
     * if the zombie is alive
     *
     * @return whether the zombie is alive or not
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * set the health of the zombie
     *
     * @param h the health of the player
     */
    public void sethealth(int h) {
        health = h;
    }

    /**
     * get the health of the zombie
     *
     * @return the health
     */
    public int getHeath() {
        return health;
    }

}
