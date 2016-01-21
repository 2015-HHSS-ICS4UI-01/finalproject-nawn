/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;

/**
 *
 * @author vonhn
 */
public class World {

    private Array<Block> blocks;
    //private Bullet bullet;
    private Player player;
    private ArrayList<Zombie> zombie;
    private Cursor cursor;

    private World w;

    public World() {
        blocks = new Array<Block>();
        zombie = new ArrayList<Zombie>();
        cursor = new Cursor(0,0);
        demoLevel();

    }

    private void demoLevel() {

        player = new Player(400, 300, 16, 16);
        for (int i = 0; i <= 2; i++) {
            if(i == 0){
            Zombie a = new Zombie(300, 640, 16, 16);
            Zombie b = new Zombie(-20, 70, 16, 16);
            Zombie c = new Zombie(800, 100, 16, 16);
            Zombie d = new Zombie(400, -20, 16, 16);
            zombie.add(a);
            zombie.add(b);
            zombie.add(c);
            zombie.add(d);
            }else{
            Zombie a = new Zombie(300, 640 * (i * 10), 16, 16);
            Zombie b = new Zombie(-20 * (i * 10), 70, 16, 16);
            Zombie c = new Zombie(800 * (i * 10), 100, 16, 16);
            Zombie d = new Zombie(400, -20 * (i * 10), 16, 16);
            zombie.add(a);
            zombie.add(b);
            zombie.add(c);
            zombie.add(d);
        } 
            
        }
    }

    public void update(float delta) {

    }

    public Player getPlayer() {
        return player;
    }

    public Array<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Zombie> getZombie() {
        return zombie;

    }
    public void setCursorX(float x){
      cursor.setx(x);
    }
    public void setCursorY(float y){
      cursor.sety(y);
    }
}
