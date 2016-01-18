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
    
    
    
    private int velocityx;
    private int velocityy;
    
    
    
    

    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
        
        velocityx = 0;
        velocityy = 0;
        
         
    }
    
        public void update(float delta) {
        super.setX(super.getX()+velocityx);
        super.setY(super.getY()+velocityy);
        
        }
    

        
    public void setVelocityX(int x) {
        velocityx = x;
    }

    public void setVelocityY(int y) {
        velocityy = y;
    }
}
