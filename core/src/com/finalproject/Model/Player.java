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

    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 2.0f;
    private final float DAMP = 0.8f;

    // states for mario
    public enum State {
        STANDING, RUNNING
    }

    // the actual state mario is in
    private State state;
    // movement variables
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    // facing
    private boolean isFacingWest;
    private boolean isFacingSouth;
    private boolean isFacingEast;
    private boolean isFacingNorth;
    private boolean isFacingNW;
    private boolean isFacingNE;
    private boolean isFacingSW;
    private boolean isFacingSE;
    // animation state counter
    private float stateTime;
    private Rectangle bounds;

    private int health = 800;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        state = State.STANDING;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        //make north true so that the player will start by facing north
        isFacingNorth = false;
        isFacingWest = false;
        isFacingSouth = false;
        isFacingEast = false;
        isFacingNE = false;
        isFacingNW = false;
        isFacingSE = false;
        isFacingSW = false;

        bounds = new Rectangle(x, y, width, height);
        position = new Vector2(x, y);

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

        position.add(velocity);
        bounds.x = position.x;
        bounds.y = position.y;

        // moving to the rught
        if (velocity.x < 0) {
            isFacingEast = true;
            isFacingSouth = false;
            isFacingEast = false;
            isFacingNorth = false;
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;
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
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;
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
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;
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
            isFacingNE = false;
            isFacingNW = false;
            isFacingSE = false;
            isFacingSW = false;
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }

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
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
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
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
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
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
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
            if (state != State.RUNNING) {
                stateTime = 0;
                state = State.RUNNING;
            }
            //not moving
        } else {
            state = State.STANDING;
            stateTime = 0;
        }
        stateTime += delta;
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public void setState(State s) {
        if (state != s) {
            stateTime = 0;
        }
        state = s;
    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public State getState() {
        return state;
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

    public void setHealth(int x) {
        health = x;
    }

    public int getHealth() {
        if (health > 0) {
            return health;
        } else {
            return 0;
        }

    }

    public void add(float x, float y) {
        position.x += x;
        position.y += y;
        bounds.x = position.x;
        bounds.y = position.y;
    }

    public float getXVelocity() {
        return velocity.x;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public float getWidth() {
        return bounds.getWidth();
    }

    public float getHeight() {
        return bounds.getHeight();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void land() {
        state = State.STANDING;
    }
}
