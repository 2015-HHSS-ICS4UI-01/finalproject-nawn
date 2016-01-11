/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.finalproject.Model.Block;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;

/**
 *
 * @author vonhn
 */
public class WorldRenderer {

    // my games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;

    private World world;
    private Player player;
    private Zombie zombie;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();
        zombie = world.getZombie();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();

        // move the x position of the camera
        camera.position.x = V_WIDTH / 2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT / 2f;
        // update the camera
        camera.update();

        // loads in the images
        AssetManager.load();
    }

    public void render(float delta) {
        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera
        camera.position.x = Math.max(player.getX(), V_WIDTH / 2);
        camera.position.y = Math.max(player.getY(), V_HEIGHT / 2);
        camera.update();

        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);

        // tells the renderer this is the list to draw
        batch.begin();

        //initialize the starting state of the player
        player.setState(Player.State.STANDING);

        //if the player is standing
        if (player.getState() == Player.State.STANDING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //output his standing south picture
                batch.draw(AssetManager.playerStandS, player.getX(), player.getY());
            }//check if hes facing north
            else if (player.isFacingNorth()) {
                //output his standing north picture
                batch.draw(AssetManager.playerStandN, player.getX(), player.getY());
            }//check if hes facing east
            else if (player.isFacingEast()) {
                //output his standing east picture
                batch.draw(AssetManager.playerStandE, player.getX(), player.getY());
            } else {
                //output his standing west picture
                batch.draw(AssetManager.playerStandW, player.getX(), player.getY());
            }
            
            //commit
        } //check if hes running
        else if (player.getState() == Player.State.RUNNING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //play his walking south animation
                batch.draw(AssetManager.playerWalkS.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            } //check if hes facing north
            else if (player.isFacingNorth()) {
                //play his walking north animation
                batch.draw(AssetManager.playerWalkN.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            } //check if hes facing east
            else if (player.isFacingEast()) {
                //play his walking east animation
                batch.draw(AssetManager.playerWalkE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing west
            else if (player.isFacingWest()) {
                //play his walking west animation
                batch.draw(AssetManager.playerWalkW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing NE
            else if (player.isFacingNE()) {
                //play his walking NE animation
                batch.draw(AssetManager.playerWalkNE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing NWw
            else if (player.isFacingNW()) {
                //play his walking NW animation
                batch.draw(AssetManager.playerWalkNW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing SE
            else if (player.isFacingSE()) {
                //play his walking SE animation
                batch.draw(AssetManager.playerWalkSE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing SW
            else {
                //play his walking SW animation
                batch.draw(AssetManager.playerWalkSW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }
        }
        
        
        
        //draw the zombie
        //if zombie facing N
//        if(zombie.isFacingNorth()){
//            //draw the zombie N picture
//            batch.draw(AssetManager.zombieN.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie is facing S
//        else if(zombie.isFacingSouth()){
//            //draw the zombie S picture
//            batch.draw(AssetManager.zombieS.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie is facing E
//        else if(zombie.isFacingEast()){
//            //draw the zombie E picture
//            batch.draw(AssetManager.zombieE.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie facing W
//        else if(zombie.isFacingWest()){
//            //draw the zombie W picture
//            batch.draw(AssetManager.zombieW.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie facing NE
//        else if(zombie.isFacingNE()){
//            //draw the zombie NE picture
//            batch.draw(AssetManager.zombieNE.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie is facing NW
//        else if(zombie.isFacingNW()){
//            //draw the zombie NW picture
//            batch.draw(AssetManager.zombieNW.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie is facing SE
//        else if(zombie.isFacingSE()){
//            //draw the zombie SE picture
//            batch.draw(AssetManager.zombieSE.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }//if zombie facing SW
//        else if(zombie.isFacingSW()){
//            //draw the zombie SW picture
//            batch.draw(AssetManager.zombieSW.getKeyFrame(zombie.getStateTime(), true), zombie.getX(), zombie.getY());
//        }
        if(zombie.isFacingNorth()){
            //draw the zombie N picture
            batch.draw(AssetManager.zombieN, zombie.getX(), zombie.getY());
        }//if zombie is facing S
        else if(zombie.isFacingSouth()){
            //draw the zombie S picture
            batch.draw(AssetManager.zombieS, zombie.getX(), zombie.getY());
        }//if zombie is facing E
        else if(zombie.isFacingEast()){
            //draw the zombie E picture
            batch.draw(AssetManager.zombieE, zombie.getX(), zombie.getY());
        }//if zombie facing W
        else if(zombie.isFacingWest()){
            //draw the zombie W picture
            batch.draw(AssetManager.zombieW, zombie.getX(), zombie.getY());
        }//if zombie facing NE
        else if(zombie.isFacingNE()){
            //draw the zombie NE picture
            batch.draw(AssetManager.zombieNE, zombie.getX(), zombie.getY());
        }//if zombie is facing NW
        else if(zombie.isFacingNW()){
            //draw the zombie NW picture
            batch.draw(AssetManager.zombieNW, zombie.getX(), zombie.getY());
        }//if zombie is facing SE
        else if(zombie.isFacingSE()){
            //draw the zombie SE picture
            batch.draw(AssetManager.zombieSE, zombie.getX(), zombie.getY());
        }//if zombie facing SW
        else if(zombie.isFacingSW()){
            //draw the zombie SW picture
            batch.draw(AssetManager.zombieSW, zombie.getX(), zombie.getY());
        }
   
        // finished listing things to draw
        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
