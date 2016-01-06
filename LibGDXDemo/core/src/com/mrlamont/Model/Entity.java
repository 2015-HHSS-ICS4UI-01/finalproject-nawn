/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.Model;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author lamonta
 */
public abstract class Entity {
    private Rectangle bounds;
    
    public Entity(float x, float y, float width, float height){
        bounds = new Rectangle(x,y,width,height);
    }
    
    public float getX(){
        return bounds.x;
    }
    
    public float getY(){
        return bounds.y;
    }
    
    public float getWidth(){
        return bounds.width;
    }
    
    public float getHeight(){
        return bounds.height;
    }
}
