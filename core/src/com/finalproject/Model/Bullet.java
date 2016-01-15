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
    
    private final float DAMP = 0.8f;
    
    private Vector2 velocity;
    private Vector2 acceleration;
    
    
    

    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
        
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
         
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
        
        }
    

        
    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }
}
