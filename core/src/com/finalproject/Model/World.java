/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author vonhn
 */
public class World {

    private Array<Block> blocks;
    private Player player;
    private Zombie[] zombies;
    private Zombie zombie;
    private World w;
    public World() {
        blocks = new Array<Block>();
        demoLevel();
    }

    private void demoLevel() {
        player = new Player(16, 16, 16, 32);
//        zombie = new Zombie(300, 300, 300, 300);
        for (int i = 0; i < 10; i++) {
            zombie = new Zombie(300,300,300,300);
        }
        // blocks along the floor
        for (int i = 0; i < 50; i++) {
            Block a = new Block(0, i * 16, 16, 16);
            Block b = new Block(i * 16, 0, 16, 16);
            Block c = new Block(784, i * 16, 16, 16);
            Block d = new Block(i * 16, 784, 16, 16);
            blocks.add(a);
            blocks.add(b);
            blocks.add(c);
            blocks.add(d);
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


    public Zombie getZombie() {
       return zombie;
    }
    
    

   
}
