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

    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 2.0f;
    private final float DAMP = 0.8f;

    //make variables to communicate with other class
    private World world;
    private Player player;

    // movement variables
    private Vector2 velocity;
    private Vector2 acceleration;
    // initialize the directions that the zombie is facing
    private boolean isFacingWest;
    private boolean isFacingSouth;
    private boolean isFacingEast;
    private boolean isFacingNorth;
    private boolean isFacingNW;
    private boolean isFacingNE;
    private boolean isFacingSW;
    private boolean isFacingSE;

    private boolean isAlive;
    // animation state counter
    private float stateTime;

    public Zombie(float x, float y, float width, float height) {
        super(x, y, width, height);

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);

        isFacingWest = false;
        isFacingSouth = false;
        isFacingEast = false;
        isFacingNorth = false;
        isFacingNE = false;
        isFacingNW = false;
        isFacingSE = false;
        isFacingSW = false;

        stateTime = 0;
    }

    public void update(float delta) {
        //acceleration.y = -9.8f;
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

        // moving to the left
        if (velocity.x < 0 && velocity.y == 0) {
            isFacingWest = true;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;

            //moving right
        } else if (velocity.x > 0 && velocity.y == 0) {
            isFacingWest = false;
            isFacingSouth = false;
            isFacingNorth = false;
            isFacingEast = true;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;

            //moving south
        } else if (velocity.y < 0 && velocity.x == 0) {
            isFacingSouth = true;
            isFacingWest = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;

            //moving up
        } else if (velocity.y > 0 && velocity.x == 0) {
            isFacingNorth = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;

            //is running ne direction
        } else if (velocity.y > 0 && velocity.x > 0) {
            isFacingNE = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;

            //if running in nw direction
        } else if (velocity.y > 0 && velocity.x < 0) {
            isFacingNW = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingSE = false;
            isFacingSW = false;

            //if running se
        } else if (velocity.y < 0 && velocity.x > 0) {
            isFacingSE = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSW = false;

            //if running sw
        } else if (velocity.y < 0 && velocity.x < 0) {
            isFacingSW = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;

        }

    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isFacingEast() {
        return isFacingEast;
    }

    public boolean isFacingSouth() {
        return isFacingSouth;
    }

    public boolean isFacingWest() {
        return isFacingWest;
    }

    public boolean isFacingNorth() {
        return isFacingNorth;
    }

    public boolean isFacingNW() {
        return isFacingNW;
    }

    public boolean isFacingNE() {
        return isFacingNE;
    }

    public boolean isFacingSE() {
        return isFacingSE;
    }

    public boolean isFacingSW() {
        return isFacingSW;
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    public int getNumberZombies(){
        return world.getZombie().size();
    }

}
