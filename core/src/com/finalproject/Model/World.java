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
    private Bullet bullet;
    private World w;
    private boolean levelOneDone = false;
    private int zombiesLeft;
    
    public World() {
        blocks = new Array<Block>();
        zombie = new ArrayList<Zombie>();
        cursor = new Cursor(0, 0);
        bullet = new Bullet(cursor.getx(), cursor.gety(), 25, 25);
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
                    Zombie a = new Zombie(390*(i/10), 0, 16, 16);
                    Zombie b = new Zombie(1400, 1590*(i/10), 16, 16);
                    Zombie c = new Zombie(-100*(i/10), 375, 16, 16);
                    Zombie d = new Zombie(1570*(i/10), 1190, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 2) {
                    Zombie a = new Zombie(1420*(i/10), 0, 16, 16);
                    Zombie b = new Zombie(900, 1590*(i/10), 16, 16);
                    Zombie c = new Zombie(-100*(i/10), 550, 16, 16);
                    Zombie d = new Zombie(1570, 1420*(i/10), 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                } else if (rand == 3) {
                    Zombie a = new Zombie(390*(i/10), 0, 16, 16);
                    Zombie b = new Zombie(200, 1590*(i/10), 16, 16);
                    Zombie c = new Zombie(-100*(i/10), 1400, 16, 16);
                    Zombie d = new Zombie(1570, 940*(i/10), 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                }
            //if all zombies dead
                // if(levelonedone = true){
            
            }

        }
     if(zombiesLeft == 1){
         levelTwo();
     }
    }

    
    public void levelTwo(){
    
        for (int i = 0; i <= 4; i++) {
            if(i == 0){
            Zombie e = new Zombie(300, 640, 16, 16);
            Zombie f = new Zombie(-20, 70, 16, 16);
            Zombie g = new Zombie(800, 100, 16, 16);
            Zombie h = new Zombie(400, -20, 16, 16);
            zombie.add(e);
            zombie.add(f);
            zombie.add(g);
            zombie.add(h);
            }else{
            Zombie e = new Zombie(300, 640 * (i/10), 16, 16);
            Zombie f = new Zombie(-20 * (i * 10), 70, 16, 16);
            Zombie g = new Zombie(800 * (i/10), 100, 16, 16);
            Zombie h = new Zombie(400, -20 * (i * 10), 16, 16);
            zombie.add(e);
            zombie.add(f);
            zombie.add(g);
            zombie.add(h);
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

    public void setCursorX(float x) {
        cursor.setx(x);
    }

    public void setCursorY(float y) {
        cursor.sety(y);
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Cursor getCursor() {
        return cursor;
    }
    public void setZombiesLeft(int z){
        zombiesLeft = z;
    }
    public int getZombiesLeft(){
        return zombiesLeft -1;
    }
}
