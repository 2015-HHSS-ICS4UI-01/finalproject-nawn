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
import com.finalproject.Model.Cursor;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import com.finalproject.Screens.AssetManager;
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
    private Cursor cursor;
    private float cursorfinalx;
    private float cursorfinaly;
    private boolean isShoot;
    private int shoot = 0;

    public MainGame() {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
        zombie = theWorld.getZombie();
        bullet = theWorld.getBullet();
        cursor = theWorld.getCursor();
        isShoot = false;
    }

    @Override
    public void show() {

    }

    @Override
    // game loop
    public void render(float deltaTime) {
        AssetManager.game.play();
        //shoot button
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            AssetManager.gunShot.play();
            isShoot = true;
           
        }
        
        //shooting
        if(!isShoot){
          bullet.setX(player.getX());
          bullet.setY(player.getY());
        }else{
           fire();
           shoot++;
           
        }
        if(shoot==1){
           
                
          cursorfinalx = cursor.getx();
          cursorfinaly = cursor.gety();
          
        }
        //zombie health
        for (int i = 0; i < theWorld.getZombie().size(); i++) {
         if(zombie.get(i).getheath()<1){
             zombie.get(i).Alive(false);
         }   
        }
        //zombie health
        for (int i = 0; i < theWorld.getZombie().size(); i++) {
         if(zombie.get(i).getheath()<1){
             zombie.get(i).Alive(false);
         }   
        }
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
        

//        //if colliding with left part of screen
        if (player.getX() <= 0) {
            player.setVelocityX(0);
            player.setState(Player.State.STANDING);
            if (Gdx.input.isKeyPressed(Keys.D)) {
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyJustPressed(Keys.W)) {
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyPressed(Keys.S)) {
                player.setVelocityY(-2f);
                player.setState(Player.State.RUNNING);
            }
//            //colliding with right 
        } else if (player.getX() >= 1570) {
            player.setVelocityX(0);
            player.setState(Player.State.STANDING);
            if (Gdx.input.isKeyPressed(Keys.A)) {
                player.setVelocityX(-2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyJustPressed(Keys.W)) {
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyPressed(Keys.S)) {
                player.setVelocityY(-2f);
                player.setState(Player.State.RUNNING);
            }
//            //colliding with bottom
        } else if (player.getY() <= 0) {
            player.setVelocityY(0);
            player.setState(Player.State.STANDING);
            if (Gdx.input.isKeyPressed(Keys.A)) {
                player.setVelocityX(-2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyJustPressed(Keys.W)) {
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
            }
//            //collides with top
        } else if (player.getY() >= 1570) {
            player.setVelocityY(0);
            player.setState(Player.State.STANDING);
            if (Gdx.input.isKeyPressed(Keys.S)) {
                player.setVelocityY(-2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyJustPressed(Keys.W)) {
                player.setVelocityY(2f);
                player.setState(Player.State.RUNNING);
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                player.setVelocityX(2f);
                player.setState(Player.State.RUNNING);
            }
        }
        
        
        

        for (int i = 0; i < theWorld.getZombie().size() - 1; i++) {

            //if player more right than zombie move right
            if (player.getX() > zombie.get(i).getX()) {
                zombie.get(i).setVelocityX(0.5f + i / 100);
            } else if (player.getX() < zombie.get(i).getX()) {
                //move left
                zombie.get(i).setVelocityX(-0.5f - i / 100);
            } else {
                zombie.get(i).setVelocityX(0);
            }
            //if player in ne position
            if (player.getY() > zombie.get(i).getY() && player.getX() > zombie.get(i).getX()) {
                zombie.get(i).setVelocityX(0.5f + i / 100);
                zombie.get(i).setVelocityY(0.5f + i / 100);
            }
            if (player.getY() < zombie.get(i).getY() && player.getX() > zombie.get(i).getX()) {
                zombie.get(i).setVelocityX(0.5f + i / 100);
                zombie.get(i).setVelocityY(-0.5f - i / 100);
            }
            if (player.getY() > zombie.get(i).getY() && player.getX() < zombie.get(i).getX()) {
                zombie.get(i).setVelocityX(-0.5f - i / 100);
                zombie.get(i).setVelocityY(0.5f + i / 100);
            }
            if (player.getY() < zombie.get(i).getY() && player.getX() < zombie.get(i).getX()) {
                zombie.get(i).setVelocityX(-0.5f - i / 100);
                zombie.get(i).setVelocityY(-0.5f - i / 100);
            }

        //if player is higher than zombie move up
            if (player.getY() > zombie.get(i).getY()) {
                zombie.get(i).setVelocityY(0.5f + i / 100);
            } else if (player.getVelocityY() < zombie.get(i).getY()) {
            //move down
                // zombie.setVelocityY(-0.5f);
            } else {
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

    public void collisions() {

        for (int i = 0; i < theWorld.getZombie().size() - 1; i++) {

            if (player.isColliding(zombie.get(i))) {
                if(zombie.get(i).isAlive()){
                player.setHealth((int) (player.getHealth() - 0.00001));
                }
                zombie.get(i).setVelocityX(0);
                zombie.get(i).setVelocityY(0);
                // get overlapping amount
                float overX = player.getOverlapX(zombie.get(i));
                float overY = player.getOverlapY(zombie.get(i));

                //just fixing y if not moving
                if (player.getVelocityX() == 0 && player.getVelocityY() == 0) {
                    // player is above the block
                    if (player.getY() > (zombie.get(i).getY())) {
                        player.addToPosition(0, overY);
                    } else if (player.getY() < zombie.get(i).getY()) {

                        player.addToPosition(0, -overY);
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
                        } else if (player.getX() < zombie.get(i).getX()) {
                            player.addToPosition(-overX, 0);
                        }

                    } else {
//                        player.setHealth(player.getHealth()-10);

                        // above the block
                        if (player.getY() > (zombie.get(i).getY())) {
                            player.addToPosition(0, (overY));

                        } else if (player.getY() < zombie.get(i).getY()) {
                            player.addToPosition(0, -overY);
                        }
                        player.setVelocityY(0);
                        zombie.get(i).setVelocityY(0);
                    }
                }
            }
            
            
            for (int j = 0; j < i ; j++) {
                
                if (player.isColliding(zombie.get(j))) {
                    if(zombie.get(j).isAlive()){
                player.setHealth((int) (player.getHealth() - 0.00001));
                    }
                zombie.get(j).setVelocityX(0);
                zombie.get(j).setVelocityY(0);
                // get overlapping amount
                float overX = player.getOverlapX(zombie.get(j));
                float overY = player.getOverlapY(zombie.get(j));

                //just fixing y if not moving
                if (player.getVelocityX() == 0 && player.getVelocityY() == 0) {
                    // player is above the block
                    if (player.getY() > (zombie.get(j).getY())) {
                        player.addToPosition(0, overY);
                    } else if (player.getY() < zombie.get(j).getY()) {

                        player.addToPosition(0, -overY);
                    }
                    player.setVelocityY(0);
                    zombie.get(j).setVelocityY(0);

                } else {
//                    player.setHealth(player.getHealth()-10);
                    // fix the smallest overlap
                    if (overX < overY) {

                        // left of the block
                        if (player.getX() > (zombie.get(j).getX())) {
                            player.addToPosition((overX), 0);
                        } else if (player.getX() < zombie.get(j).getX()) {
                            player.addToPosition(-overX, 0);
                        }

                    } else {
//                        player.setHealth(player.getHealth()-10);

                        // above the block
                        if (player.getY() > (zombie.get(j).getY())) {
                            player.addToPosition(0, (overY));

                        } else if (player.getY() < zombie.get(j).getY()) {
                            player.addToPosition(0, -overY);
                        }
                        player.setVelocityY(0);
                        zombie.get(j).setVelocityY(0);
                    }
                }
            }
                
            
            if (zombie.get(j).isColliding(zombie.get(i))) {
               
                zombie.get(i).setVelocityX(0);
                zombie.get(i).setVelocityY(0);
                // get overlapping amount
                float overX = zombie.get(j).getOverlapX(zombie.get(i));
                float overY = zombie.get(j).getOverlapY(zombie.get(i));

                //just fixing y if not moving
                if (zombie.get(j).getVelocityX() == 0 && zombie.get(j).getVelocityY() == 0) {
                    // player is above the block
                    if (zombie.get(j).getY() > (zombie.get(i).getY())) {
                        zombie.get(j).addToPosition(0, overY);
                    } else if (zombie.get(j).getY() < zombie.get(i).getY()) {

                        zombie.get(j).addToPosition(0, -overY);
                    }
                    zombie.get(j).setVelocityY(0);
                    zombie.get(i).setVelocityY(0);

                } else {
//                    player.setHealth(player.getHealth()-10);
                    // fix the smallest overlap
                    if (overX < overY) {

                        // left of the block
                        if (zombie.get(j).getX() > (zombie.get(i).getX())) {
                            zombie.get(j).addToPosition((overX), 0);
                        } else if (zombie.get(j).getX() < zombie.get(i).getX()) {
                            zombie.get(j).addToPosition(-overX, 0);
                        }

                    } else {
//                        player.setHealth(player.getHealth()-10);

                        // above the block
                        if (zombie.get(j).getY() > (zombie.get(i).getY())) {
                            zombie.get(j).addToPosition(0, (overY));

                        } else if (zombie.get(j).getY() < zombie.get(i).getY()) {
                            zombie.get(j).addToPosition(0, -overY);
                        }
                        zombie.get(j).setVelocityY(0);
                        zombie.get(i).setVelocityY(0);
                    }
                }
            }
            }
        }

    }
    
    
    public void fire(){
    float cursorx;
    float cursory;
    int speed = 3;
    
    cursorx = cursorfinalx;
    cursory = cursorfinaly;

    
    double angle = Math.atan2(cursorx-player.getX(),cursory-player.getY() );
    
    double bulletdx = speed*Math.sin(angle);
    double bulletdy = speed*Math.cos(angle);
    
    bullet.setX((float) (bullet.getx()+bulletdx));
    bullet.setY((float) (bullet.gety()+bulletdy));
        for (int i = 0; i < theWorld.getZombie().size(); i++) {
            if(zombie.get(i).isAlive()){
            if(bullet.isColliding(zombie.get(i))){
                zombie.get(i).sethealth(zombie.get(i).getheath()-50);
                reset();
            }
            }
        }
    if(shoot == 100){
        reset();
    }
        
    
    
    
}
    public void reset(){
    isShoot = false;
    shoot = 0;
    
}
    
    public boolean shoot(){
    return isShoot;
    }
    
}
