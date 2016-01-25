/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.finalproject.Model.Bullet;
import com.finalproject.Model.Cursor;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import com.finalproject.Screens.AssetManager;
import com.finalproject.Screens.WorldRenderer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamonta
 */
public class MainGame implements Screen {

    private World theWorld;
    private Player player;
    private WorldRenderer renderer;
    private ArrayList<Zombie> zombie;
    private ArrayList<Bullet> bullet;
    private Cursor cursor;
    private float cursorfinalx;
    private float cursorfinaly;
    private float playerfinalx;
    private float playerfinaly;
    private boolean isShoot;
    private int shoot = 0;
    private int clip = 0;
    private TiledMap map;
    Game g;

    public MainGame(Game g) {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
        zombie = theWorld.getZombie();
        bullet = theWorld.getBullet();
        cursor = theWorld.getCursor();
        isShoot = false;

        create();
        this.g = g;
    }

    public MainGame() {
        create();
    }

    public void create() {
        if (player.getHealth() == 0) {
            g.setScreen(new EndScreen());
        }
        if (zombie.size() == 1) {
            g.setScreen(new WinScreen());
        }
    }

    @Override
    public void show() {

    }

    @Override
    // game loop
    public void render(float deltaTime) {
        AssetManager.game.play();
        AssetManager.game.setLooping(true);
        
        //if player dies
        if (player.getHealth() == 0) {
            create();
            AssetManager.game.setLooping(false);
            AssetManager.game.dispose();
        }
        if (zombie.size() == 1) {
            create();
            AssetManager.game.setLooping(false);
            AssetManager.game.dispose();
        }

        //shoot button
        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {

            isShoot = true;
            for (int i = 0; i < bullet.size(); i++) {
                //play gun sound
                AssetManager.gunShot.play();
            }
        }
        //clip for bullets
        if (clip == 9) {

            clip = 0;
        }

        //shooting
        if (!isShoot) {
            //keep bullet with player until shot
            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).setX(player.getX());
                bullet.get(i).setY(player.getY());
            }
        } else {
            //fire 1 bullet
            fire(bullet.get(clip));
            //keep the rest of the bullets with the player
            for (int i = 1; i < 10; i++) {
                bullet.get(i).setX(player.getX());
                bullet.get(i).setY(player.getY());
            }
            shoot++;
        }
        //after the bullet is shot, get the x and y coordinates for the trajectory(so is dosent change)
        if (shoot == 1) {
            cursorfinalx = cursor.getx();
            cursorfinaly = cursor.gety();
            playerfinalx = player.getX();
            playerfinaly = player.getY();
        }
        //zombie health
        for (int i = 0; i < theWorld.getZombie().size() - 1; i++) {
            if (zombie.get(i).getHeath() < 1) {
                zombie.get(i).Alive(false);
                zombie.remove(i);
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

        //loads the map  
        map = new TmxMapLoader().load("zombieMap.tmx");
        //loads collision layer  
        MapLayer collisionObjectLayer = map.getLayers().get("collision");
        //finds all objects in the collision layer  
        MapObjects objects = collisionObjectLayer.getObjects();
        //gets all the rectangle objects on the map   
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

            //declares all rectangle objects as rectangles   
            Rectangle rectangle = rectangleObject.getRectangle();

            //if the rectangle objects overlaps the player the speeds is decreased  
            if (Intersector.overlaps(rectangle, player.getBounds())) {

                //find which side it intersected from and based on that slow the speed or initiate the collison  
                //if hit wall from bottom up  
                if (player.getY() < rectangle.y) {
                    player.setVelocityY(-0.5f);

                    //if hit wall from top down  
                } else if (player.getY() > rectangle.y) {
                    player.setVelocityY(0.5f);
                    //if hit wall from left right  
                }

                if (player.getX() > rectangle.x) {
                    player.setVelocityX(0.5f);

                    //if hit wall from right left  
                } else if (player.getX() < rectangle.x) {
                    player.setVelocityX(-0.5f);
                }
            }
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

        try {
            // draw the screen
            renderer.render(deltaTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resize(int width, int height
    ) {
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
                if (zombie.get(i).isAlive()) {
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

                } else // fix the smallest overlap
                {
                    if (overX < overY) {

                        // left of the block
                        if (player.getX() > (zombie.get(i).getX())) {
                            player.addToPosition((overX), 0);
                        } else if (player.getX() < zombie.get(i).getX()) {
                            player.addToPosition(-overX, 0);
                        }

                    } else {
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

            for (int j = 0; j < i; j++) {

                if (player.isColliding(zombie.get(j))) {
                    if (zombie.get(j).isAlive()) {
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

                    } else // fix the smallest overlap
                    {
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

                    } else // fix the smallest overlap
                    {
                        if (overX < overY) {
                            if (zombie.get(j).getX() > (zombie.get(i).getX())) {
                                zombie.get(j).addToPosition((overX), 0);
                            } else if (zombie.get(j).getX() < zombie.get(i).getX()) {
                                zombie.get(j).addToPosition(-overX, 0);
                            }

                        } else {
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

    public void fire(Bullet bullet) {

        float cursorx;
        float cursory;
        float playerx;
        float playery;
        int speed = 3;
// floats for player and cursor positions 
        cursorx = cursorfinalx;
        cursory = cursorfinaly;
        playerx = playerfinalx;
        playery = playerfinaly;
        
        //creates an angle from the given positions
        double angle = Math.atan2(cursorx - playerx, cursory - playery);
        //find the x value of the triangle then multiply by speed
        double bulletdx = speed * Math.sin(angle);
        //find the y value
        double bulletdy = speed * Math.cos(angle);
        //add the components to the current bullet x and y
        bullet.setX((float) (bullet.getx() + bulletdx));
        bullet.setY((float) (bullet.gety() + bulletdy));
        //if the zombie is alive, and it collides with a zombie, reset the bullet and -50 health from the zombie
        for (int i = 0; i < theWorld.getZombie().size(); i++) {
            if (zombie.get(i).isAlive()) {

                if (bullet.isColliding(zombie.get(i))) {
                    zombie.get(i).sethealth(zombie.get(i).getHeath() - 50);
                    reset();
                }
            }
        }
        //if the bullet reaches a certain distance reset
        if (shoot == 100) {
            reset();
        }
        //collisions with walls
        map = new TmxMapLoader().load("zombieMap.tmx");
        //loads collision layer  
        MapLayer collisionObjectLayer = map.getLayers().get("collision");
        //finds all objects in the collision layer  
        MapObjects objects = collisionObjectLayer.getObjects();
        //gets all the rectangle objects on the map   
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

            //declares all rectangle objects as rectangles   
            Rectangle rectangle = rectangleObject.getRectangle();

            //if the rectangle objects overlaps the player the speeds is decreased  
            if (Intersector.overlaps(rectangle, bullet.getBounds())) {
                reset();
            }
        }

    }

    public void reset() {
        //to reset the bullet
        isShoot = false;
        shoot = 0;
    }

    public boolean shoot() {
        return isShoot;
    }

}
