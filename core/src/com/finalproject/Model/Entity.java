/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author vonhn
 */
public abstract class Entity {

    private Rectangle bounds;

    public Entity(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void addToPosition(float x, float y) {
        bounds.x += x;
        bounds.y += y;
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public void setX(float x) {
        bounds.x = x;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    public boolean isColliding(Entity other) {
        return bounds.overlaps(other.bounds);
    }

    public float getOverlapX(Entity other) {
        float overlap = Math.min(this.bounds.x + this.bounds.width, other.bounds.x + other.bounds.width) - Math.max(this.bounds.x, other.bounds.x);
        return overlap;
    }

    public float getOverlapY(Entity other) {
        float overlap = Math.min(this.bounds.y + this.bounds.height, other.bounds.y + other.bounds.height) - Math.max(this.bounds.y, other.bounds.y);
        return overlap;
    }
}
