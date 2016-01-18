/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

/**
 *
 * @author admin
 */
public class Fire {
    private Bullet bullet;
    private float targetx;
    private float targety;
    public Fire(Bullet b, float locationX, float locationY){
     bullet = b;   
     targetx = locationX;
     targety = locationY;
    }
}
