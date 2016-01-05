/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alimurra.Model;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author alimu
 */
public class World {
    private Array<Block> blocks;
    private Mario player;
    
    public World(){
        
        blocks = new Array<Block>();
        demoLevel();
    }
    
    private void demoLevel(){
        player = new Mario(100,100,16,32);
        //blocks along the floor
        for(int i = 0; i < 50; i++){
            Block b = new Block(i*16,0,16,16);
            blocks.add(b);
        }
        
        blocks.add(new Block(48,16,16,16));
        blocks.add(new Block(96,32,16,16));
        blocks.add(new Block(112,32,16,16));
        blocks.add(new Block(128,96,16,16));
        blocks.add(new Block(112,96,16,16));
        
    }
    
    public void update(float delta){
        
    }
    
    public Mario getPlayer(){
        return player;
    }
    
    public Array<Block> getBlocks(){
        return blocks;
    }
    
}
