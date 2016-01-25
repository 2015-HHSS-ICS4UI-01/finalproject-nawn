/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author NAWN
 */
public abstract class Entity {

    private Rectangle bounds;

    /**
     * assigns an entity properties and stores as a rectangle
     * @param x the x value of an entity in the world
     * @param y the y value of entity in the world
     * @param width the width of the entity
     * @param height the height of the entity
     */
    public Entity(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * adds to position of the entity
     * @param x adds to x value
     * @param y adds to y value
     */
    public void addToPosition(float x, float y) {
        bounds.x += x;
        bounds.y += y;
    }

    /**
     * 
     * @return returns x value of the entity
     */
    public float getX() {
        return bounds.x;
    }

    /**
     * 
     * @return returns the y value of the entity 
     */
    public float getY() {
        return bounds.y;
    }

    /**
     * sets the x value of the entity
     * @param x the x value of the entity to change to
     */
    public void setX(float x) {
        bounds.x = x;
    }

    /**
     * sets the y value of the entity
     * @param y the y value of the entity to change to
     */
    public void setY(float y) {
        bounds.y = y;
    }

    /**
     * 
     * @return returns width of the entity
     */
    public float getWidth() {
        return bounds.width;
    }

    /**
     * 
     * @return returns height of the entity
     */
    public float getHeight() {
        return bounds.height;
    }

    /**
     * checks if two entities are colliding
     * @param other the entity to compare to for collision
     * @return 
     */
    public boolean isColliding(Entity other) {
        return bounds.overlaps(other.bounds);
    }

    /**
     * gets the overlap for x axis
     * @param other
     * @return 
     */
    public float getOverlapX(Entity other) {
        float overlap = Math.min(this.bounds.x + this.bounds.width, other.bounds.x + other.bounds.width) - Math.max(this.bounds.x, other.bounds.x);
        return overlap;
    }

    /**
     * gets the overlap  for y value
     * @param other
     * @return 
     */
    public float getOverlapY(Entity other) {
        float overlap = Math.min(this.bounds.y + this.bounds.height, other.bounds.y + other.bounds.height) - Math.max(this.bounds.y, other.bounds.y);
        return overlap;
    }

    /**
     * 
     * @return returns the properties of the entity 
     */
    public Rectangle getBounds() {
        return this.bounds;
    }
}
