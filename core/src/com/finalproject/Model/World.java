/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import static com.badlogic.gdx.math.MathUtils.random;
import java.util.ArrayList;

/**
 *
 * @author NAWN
 */
public class World {
    //variable for the player
    private Player player;
    //array to store zombies
    private ArrayList<Zombie> zombie;
    //variable for the cursor
    private Cursor cursor;
    //array to store the bullets
    private ArrayList<Bullet> bullet;
    //variable of number of zombies left
    private int zombiesLeft;

    public World() {
        //initialize zombie
        zombie = new ArrayList<Zombie>();
        //initialize cursor
        cursor = new Cursor(0, 0);
        //initialize bullet
        bullet = new ArrayList<Bullet>();
        //go to the first level
        levelOne();
    }

    private void levelOne() {
        //initialize the starting position of the player
        player = new Player(480, 785, 16, 16);
        //for 3 sets of zombies
        for (int i = 0; i <= 3; i++) {
            //get a random number
            int rand = random.nextInt(3) + 1;
            //for the first set of zombies
            if (i == 0) {
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(390, 0, 16, 16);
                    Zombie b = new Zombie(1400, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 375, 16, 16);
                    Zombie d = new Zombie(1570, 1190, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(1420, 0, 16, 16);
                    Zombie b = new Zombie(900, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 550, 16, 16);
                    Zombie d = new Zombie(1570, 1420, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(390, 0, 16, 16);
                    Zombie b = new Zombie(200, 1590, 16, 16);
                    Zombie c = new Zombie(-20, 1400, 16, 16);
                    Zombie d = new Zombie(1570, 940, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                }
                //for the second set of zombies
            } else {
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie b = new Zombie(1400, 1590 * (i / 10), 16, 16);
                    Zombie c = new Zombie(-100 * (i / 10), 375, 16, 16);
                    Zombie d = new Zombie(1570 * (i / 10), 1190, 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(1420 * (i / 10), 0, 16, 16);
                    Zombie b = new Zombie(900, 1590 * (i / 10), 16, 16);
                    Zombie c = new Zombie(-100 * (i / 10), 550, 16, 16);
                    Zombie d = new Zombie(1570, 1420 * (i / 10), 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie a = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie b = new Zombie(200, 1590 * (i / 10), 16, 16);
                    Zombie c = new Zombie(-100 * (i / 10), 1400, 16, 16);
                    Zombie d = new Zombie(1570, 940 * (i / 10), 16, 16);
                    zombie.add(a);
                    zombie.add(b);
                    zombie.add(c);
                    zombie.add(d);
                }
            }
        }
        
        //for each bullet
        for (int i = 0; i < 10; i++) {
            //add the bullet to the array
            bullet.add(new Bullet(cursor.getx(), cursor.gety(), 25, 25));
        }
    }

    /**
     * get the player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * get each zombie
     * @return the zombie
     */
    public ArrayList<Zombie> getZombie() {
        return zombie;
    }

    /**
     * set the cursor x position
     * @param x is the new x position
     */
    public void setCursorX(float x) {
        cursor.setx(x);
    }

    /**
     * set the cursor y position
     * @param y is the new y position
     */
    public void setCursorY(float y) {
        cursor.sety(y);
    }

    /**
     * get the bullet
     * @return the bullet
     */
    public ArrayList<Bullet> getBullet() {
        return bullet;
    }

    /**
     * get the cursor
     * @return the cursor
     */
    public Cursor getCursor() {
        return cursor;
    }
    
    public void update(float delta) {

    }

}
