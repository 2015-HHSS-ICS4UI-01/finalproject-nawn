/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.finalproject.Model.Block;
import com.finalproject.Model.Bullet;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import com.finalproject.Screens.WorldRenderer;
import java.util.ArrayList;

/**
 *
 * @author lamonta
 */
public class MainGame implements Screen {

    
    private World theWorld;
    private Player player;
    private WorldRenderer renderer;
    private ArrayList<Zombie> zombie;
    private Bullet bullet;
    
   
    
    public MainGame() {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
        zombie = theWorld.getZombie();
     //   bullet = theWorld.getBullet();
       
    }

    @Override
    public void show() {

    }

    @Override
    // game loop
    public void render(float deltaTime) {
//        if(Gdx.input.isKeyPressed(Keys.SPACE)){
//           Bullet bullet = new Bullet(player.getX(),player.getY(),16,16);
//           bullet.setVelocityX(2);
//           bullet.setVelocityY(2);
//           bullet.update(deltaTime);
//           
//        }
        //movement up down left right with keys
        if (Gdx.input.isKeyPressed(Keys.D)) {
            player.setVelocityX(2f);
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            player.setVelocityX(-2f);
        } else if (Gdx.input.isKeyPressed(Keys.W)) {
            player.setVelocityY(2f);
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            //diagonal movement
        }
        if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)) {
            player.setVelocityY(2f);
            player.setVelocityX(-2f);
        } else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)) {
            player.setVelocityY(2f);
            player.setVelocityX(2f);
        } else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setVelocityX(2f);
        } else if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setVelocityX(-2f);
        }
        
        
        //if colliding with left part of screen
        if(player.getX() <= 0){
            player.setVelocityX(0);
            player.setState(Player.State.STANDING);
            if(Gdx.input.isKeyPressed(Keys.D)){
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyJustPressed(Keys.W)){
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            }else if (Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setState(Player.State.RUNNING);
        }
            //colliding with right 
        }else if(player.getX() >= 770){
             player.setVelocityX(0);
            player.setState(Player.State.STANDING);
             if(Gdx.input.isKeyPressed(Keys.A)){
                player.setVelocityX(-2f);
                player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyJustPressed(Keys.W)){
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            }else if (Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setState(Player.State.RUNNING);
        }
             //colliding with bottom
        }else if(player.getY() <= 0){
            player.setVelocityY(0);
            player.setState(Player.State.STANDING);
             if(Gdx.input.isKeyPressed(Keys.A)){
                player.setVelocityX(-2f);
                player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyJustPressed(Keys.W)){
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyPressed(Keys.D)){
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
        }
             //collides with top
        }else if(player.getY() >= 550){
            System.out.println("t");
            player.setVelocityY(0);
            player.setState(Player.State.STANDING);
              if (Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyJustPressed(Keys.W)){
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            }else if(Gdx.input.isKeyPressed(Keys.D)){
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
        }
              //top left
        }else if(player.getY() >= 550 && player.getX() == 0){
            player.setVelocityY(0);
            player.setState(Player.State.STANDING);
              if (Gdx.input.isKeyPressed(Keys.S)) {
            player.setVelocityY(-2f);
            player.setState(Player.State.RUNNING);
            
            }else if(Gdx.input.isKeyPressed(Keys.D)){
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
        }
        }
        
        
        
        
        for (int i = 0; i < theWorld.getZombie().size()-1; i++) {
            
        //if player more right than zombie move right
        if(player.getX() > zombie.get(i).getX()){
            zombie.get(i).setVelocityX(0.5f);
        }else if(player.getX() <zombie.get(i).getX()) {
            //move left
            zombie.get(i).setVelocityX(-0.5f);
        }else{
            zombie.get(i).setVelocityX(0);
        }
        //if player in ne position
        if(player.getY() > zombie.get(i).getY() && player.getX() > zombie.get(i).getX()){
            zombie.get(i).setVelocityX(0.5f);
            zombie.get(i).setVelocityY(0.5f);
        }
        if(player.getY() < zombie.get(i).getY() && player.getX() > zombie.get(i).getX()){
            zombie.get(i).setVelocityX(0.5f);
            zombie.get(i).setVelocityY(-0.5f);
        }
        if(player.getY() > zombie.get(i).getY() && player.getX() <zombie.get(i).getX()){
            zombie.get(i).setVelocityX(-0.5f);
            zombie.get(i).setVelocityY(0.5f);
        }
        if(player.getY() < zombie.get(i).getY() && player.getX() < zombie.get(i).getX()){
            zombie.get(i).setVelocityX(-0.5f);
          zombie.get(i).setVelocityY(-0.5f);
        }
        
        //if player is higher than zombie move up
        
        if(player.getY() > zombie.get(i).getY()){
            zombie.get(i).setVelocityY(0.5f);
        }else if(player.getVelocityY()<zombie.get(i).getY()){
            //move down
           // zombie.setVelocityY(-0.5f);
        }else{
            zombie.get(i).setVelocityY(0);
        }
        
        zombie.get(i).update(deltaTime);
        }
        player.update(deltaTime);    
        
        collisions();
        
        //collisions with blocks 
        // go through each block
//        for (Block b : theWorld.getBlocks()) {
//            // if player is hitting a block
//            if (player.isColliding(b)) {
//                // get overlapping amount
//                float overX = player.getOverlapX(b);
//                float overY = player.getOverlapY(b);
//
//                //just fixing y if not moving
//                if (player.getVelocityX() == 0) {
//                    // player is above the block
//                    if (player.getY() > b.getY()) {
//                        player.addToPosition(0, overY);
//                        player.setState(Player.State.STANDING);
//                    } else {
//                        player.addToPosition(0, -overY);
//                    }
//                    player.setVelocityY(0);
//                } else {
//                    // fix the smallest overlap
//                    if (overX < overY) {
//                        // left of the block
//                        if (player.getX() < b.getX()) {
//                            player.addToPosition(-overX, 0);
//                        } else {
//                            player.addToPosition(overX, 0);
//                        }
//                    } else {
//                        // above the block
//                        if (player.getY() > b.getY()) {
//                            player.addToPosition(0, overY);
//
//                        } else {
//                            player.addToPosition(0, -overY);
//                        }
//                        player.setVelocityY(0);
//                    }
//                }
//            }
//        }
       
                        // above the block
               
        // draw the screen
        renderer.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    
    public void collisions(){
        
        for (int i = 0; i < theWorld.getZombie().size()-1; i++) {
            
        
        if (player.isColliding(zombie.get(i))) {
            player.setHealth((int) (player.getHealth()-0.00001));
            zombie.get(i).setVelocityX(0);
            zombie.get(i).setVelocityY(0);
                // get overlapping amount
                float overX = player.getOverlapX(zombie.get(i));
                float overY = player.getOverlapY(zombie.get(i));

                //just fixing y if not moving
                if (player.getVelocityX() == 0 && player.getVelocityY() == 0 ) {
                    // player is above the block
                    if (player.getY() > (zombie.get(i).getY())) {
                        player.addToPosition(0, overY);
                    } else if(player.getY() < zombie.get(i).getY()) {
                        
                        player.addToPosition(0, -overY );
                    }
                    player.setVelocityY(0);
                    zombie.get(i).setVelocityY(0);
                    
                } else {
//                    player.setHealth(player.getHealth()-10);
                    // fix the smallest overlap
                    if (overX < overY) {

                        
                        // left of the block
                        if (player.getX() > (zombie.get(i).getX())) {
                            player.addToPosition((overX), 0);
                        } else if(player.getX() < zombie.get(i).getX()) {
                            player.addToPosition(-overX, 0);
                        }  
                        
                    } else {
//                        player.setHealth(player.getHealth()-10);
                        
                        // above the block
                        if (player.getY() > (zombie.get(i).getY())) {
                            player.addToPosition(0, (overY));

                        } else if(player.getY() < zombie.get(i).getY()) {
                            player.addToPosition(0, -overY);
                        }
                        player.setVelocityY(0);
                        zombie.get(i).setVelocityY(0);
                    }
                }
    }
        }
    
}
}
