/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.MathUtils;
import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<Bullet> bullet;
    private World w;
    private boolean levelOneDone = false;

    public World() {
        blocks = new Array<Block>();
        zombie = new ArrayList<Zombie>();
        cursor = new Cursor(0, 0);
        bullet = new ArrayList<Bullet>();
        Random random = new Random();
        demoLevel();

    }

    private void demoLevel() {

        player = new Player(480, 785, 16, 16);
        for (int i = 0; i <= 3; i++) {
            int rand = random.nextInt(3) + 1;
            if (i == 0) {
                if (rand == 1) {
                    Zombie a = new Zombie(390, 0, 16, 16);
                    Zombie b = new Zombie(1400, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 375, 16, 16);
                    Zombie d = new Zombie(1570, 1190, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 2) {
                    Zombie a = new Zombie(1420, 0, 16, 16);
                    Zombie b = new Zombie(900, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 550, 16, 16);
                    Zombie d = new Zombie(1570, 1420, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 3) {
                    Zombie a = new Zombie(390, 0, 16, 16);
                    Zombie b = new Zombie(200, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 1400, 16, 16);
                    Zombie d = new Zombie(1570, 940, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                }

            } else {
                if (rand == 1) {
                    Zombie a = new Zombie(390*i, 0, 16, 16);
                    Zombie b = new Zombie(1400, 1590*i, 16, 16);
                    Zombie c = new Zombie(-100*i, 375, 16, 16);
                    Zombie d = new Zombie(1570*i, 1190, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 2) {
                    Zombie a = new Zombie(1420*i, 0, 16, 16);
                    Zombie b = new Zombie(900, 1590*i, 16, 16);
                    Zombie c = new Zombie(-100*i, 550, 16, 16);
                    Zombie d = new Zombie(1570, 1420*i, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 3) {
                    Zombie a = new Zombie(390*i, 0, 16, 16);
                    Zombie b = new Zombie(200, 1590*i, 16, 16);
                    Zombie c = new Zombie(-100*i, 1400, 16, 16);
                    Zombie d = new Zombie(1570, 940*i, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                }
            //if all zombies dead
                // if(levelonedone = true){
//            levelTwo();
            }

        }
        
        for (int i = 0; i < 10; i++) {
         bullet.add(new Bullet(cursor.getx(), cursor.gety(), 25, 25));
        }
    }

//    public void levelTwo(){
    //arrange differently and add more zombies
//        for (int i = 0; i <= 2; i++) {
//            if(i == 0){
//            Zombie a = new Zombie(300, 640, 16, 16);
//            Zombie b = new Zombie(-20, 70, 16, 16);
//            Zombie c = new Zombie(800, 100, 16, 16);
//            Zombie d = new Zombie(400, -20, 16, 16);
//            zombie.add(a);
//            zombie.add(b);
//            zombie.add(c);
//            zombie.add(d);
//            }else{
//            Zombie a = new Zombie(300, 640 * (i * 10), 16, 16);
//            Zombie b = new Zombie(-20 * (i * 10), 70, 16, 16);
//            Zombie c = new Zombie(800 * (i * 10), 100, 16, 16);
//            Zombie d = new Zombie(400, -20 * (i * 10), 16, 16);
//            zombie.add(a);
//            zombie.add(b);
//            zombie.add(c);
//            zombie.add(d);
//        } 
//    }
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

    public void setCursorX(float x) {
        cursor.setx(x);
    }

    public void setCursorY(float y) {
        cursor.sety(y);
    }

    public ArrayList<Bullet> getBullet() {
        return bullet;
    }

    public Cursor getCursor() {
        return cursor;
    }
}
