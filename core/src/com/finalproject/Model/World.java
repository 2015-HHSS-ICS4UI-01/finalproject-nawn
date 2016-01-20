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

    private World w;

    public World() {
        blocks = new Array<Block>();
        zombie = new ArrayList<Zombie>();
        demoLevel();

    }

    private void demoLevel() {

        player = new Player(16, 16, 16, 16);
        //bullet = new Bullet(50,50,16,16);
//        zombie = new Zombie(300, 300, 300, 300);
        for (int i = 0; i <= 2; i++) {
            Zombie a = new Zombie(300, 640 * (i * 10), 16, 16);
            Zombie b = new Zombie(-20 * (i * 10), 70, 16, 16);
            Zombie c = new Zombie(800 * (i * 10), 100, 16, 16);
            Zombie d = new Zombie(400, -20 * (i * 10), 16, 16);
            zombie.add(a);
            zombie.add(b);
            zombie.add(c);
            zombie.add(d);
        }

        // blocks along the floor
//        for (int i = 0; i < 50; i++) {
//            Block a = new Block(0, i * 16, 16, 16);
//            Block b = new Block(i * 16, 0, 16, 16);
//            Block c = new Block(784, i * 16, 16, 16);
//            Block d = new Block(i * 16, 784, 16, 16);
//            blocks.add(a);
//            blocks.add(b);
//            blocks.add(c);
//            blocks.add(d);
//        }
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
}
