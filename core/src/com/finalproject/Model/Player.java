/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Vonhn
 */
public class Player extends Entity {

    //variables for the maximum speeds of the player
    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 2.0f;
    private final float DAMP = 0.8f;

    // states for player
    public enum State {

        //variables of the states of the player
        STANDING, RUNNING
    }

    // the actual state player is in
    private State state;
    // movement variables
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    //variables of the direction of the player
    private boolean isFacingWest;
    private boolean isFacingSouth;
    private boolean isFacingEast;
    private boolean isFacingNorth;
    // animation state counter
    private float stateTime;
    private Rectangle bounds;
    //initialize the variable for the health
    private int health = 800;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        state = State.STANDING;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        //make all the booleans false
        isFacingNorth = false;
        isFacingWest = false;
        isFacingSouth = false;
        isFacingEast = false;
        //initialize the bounds and position
        bounds = new Rectangle(x, y, width, height);
        position = new Vector2(x, y);

        stateTime = 0;
    }

    public void update(float delta) {
        //apply the damp effect to the player
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

        position.add(velocity);
        bounds.x = position.x;
        bounds.y = position.y;

        // moving to the rught
        if (velocity.x < 0) {
            isFacingEast = true;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
            //moving right
        } else if (velocity.x > 0) {
            isFacingWest = false;
            isFacingSouth = false;
            isFacingNorth = false;
            isFacingEast = true;
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
            //moving south
        } else if (velocity.y < 0) {
            isFacingSouth = true;
            isFacingWest = false;
            isFacingEast = false;
            isFacingNorth = false;
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
            //moving up
        } else if (velocity.y > 0) {
            isFacingNorth = true;
            isFacingWest = false;
            isFacingSouth = false;
            isFacingEast = false;
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
        }
        stateTime += delta;
    }

    /**
     * set the velocity of the x
     *
     * @param x is the velocity passed in
     */
    public void setVelocityX(float x) {
        velocity.x = x;
    }

    /**
     * set the velocity of the y
     *
     * @param y is the velocity passed in
     */
    public void setVelocityY(float y) {
        velocity.y = y;
    }

    /**
     * sets the state of the player
     *
     * @param s is the state
     */
    public void setState(State s) {
        //if the state is not the state passed in
        if (state != s) {
            //make the statetime 0
            stateTime = 0;
        }
        //the state is the state passed in
        state = s;
    }

    /**
     * get the velocity of the x
     *
     */
    public float getVelocityX() {
        return velocity.x;
    }

    /**
     *
     * @return the velocity of the y
     */
    public float getVelocityY() {
        return velocity.y;
    }

    /**
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     *
     * @return the stateTime
     */
    public float getStateTime() {
        return stateTime;
    }

    /**
     *
     * @return whether the player is facing east or not
     */
    public boolean isFacingEast() {
        return isFacingEast;
    }

    /**
     *
     * @return whether the player is facing south or not
     */
    public boolean isFacingSouth() {
        return isFacingSouth;
    }

    /**
     *
     * @return whether the player is facing west or not
     */
    public boolean isFacingWest() {
        return isFacingWest;
    }

    /**
     *
     * @return whether the player is facing north or not
     */
    public boolean isFacingNorth() {
        return isFacingNorth;
    }

    /**
     * set the health of the player
     *
     * @param x the health left
     */
    public void setHealth(int x) {
        health = x;
    }

    /**
     *
     * @return the health of the player
     */
    public int getHealth() {
        //if the health is greater than 0, player is alive
        if (health > 0) {
            //return health left
            return health;
        } else {
            //return no health
            return 0;
        }
    }

    /**
     *
     * @param x add x amount to the x position
     * @param y add y amount to the y position
     */
    public void add(float x, float y) {
        position.x += x;
        position.y += y;
        bounds.x = position.x;
        bounds.y = position.y;
    }

    /**
     * get the x velocity
     *
     * @return the velocity of the x
     */
    public float getXVelocity() {
        return velocity.x;
    }

    /**
     * get the bounds
     *
     * @return return the bounds
     */
    public Rectangle getBounds() {
        return this.bounds;
    }

    /**
     * get the width of the bounds
     *
     * @return return the width of the bounds
     */
    public float getWidth() {
        return bounds.getWidth();
    }

    /**
     * get the height of the bounds
     *
     * @return return the height of the bounds
     */
    public float getHeight() {
        return bounds.getHeight();
    }

    /**
     * get the x of the player
     *
     * @return the x position of the player
     */
    public float getX() {
        return position.x;
    }

    /**
     * get the y of the player
     *
     * @return the y position of the player
     */
    public float getY() {
        return position.y;
    }

    /**
     * the state of the player
     */
    public void land() {
        //the state is standing
        state = State.STANDING;
    }

}
