/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.finalproject.Model.Block;
import com.finalproject.Model.Bullet;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import static com.finalproject.Screens.AssetManager.cross;
import java.util.ArrayList;


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
    private ArrayList<Zombie> zombie;
   // private Bullet bullet;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int mouseX;
    private int mouseY;
    private int health;
    
    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();
        zombie= world.getZombie();
        
        health = world.getPlayer().getHealth();
        
      //  bullet = world.getBullet();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        // move the x position of the camera
        
        camera.position.x =  V_WIDTH / 2f;
        // move the y position of the camera
        camera.position.y =  V_HEIGHT/ 2f;
        
        
        // update the camera
        camera.update();

        // loads in the images
        AssetManager.load();
        
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("Cursor.png")), 0,0));
    }

    public void render(float delta) {
        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera
        camera.position.x = Math.max(world.getPlayer().getX(), V_WIDTH / 2);
        camera.position.y = Math.max(world.getPlayer().getY(), V_HEIGHT / 2);
        camera.update();
        
        shapeRenderer.setProjectionMatrix(camera.combined);
        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);
        
        // tells the renderer this is the list to draw
        batch.begin();
         
        //initialize the starting state of the player
        player.setState(Player.State.STANDING);
        
        //crosshair
        
        
        mouseX = (int) this.getMousePosInGameWorldx();
        mouseY = (int) this.getMousePosInGameWorldy();
        batch.draw(cross, mouseX, mouseY);
        
      //  bullet.setX(player.getX());
      //  bullet.setY(player.getY());
       
        
      //  batch.draw(AssetManager.cross,mouseX-16,mouseY-15);
      //  if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
      //  batch.draw(AssetManager.bullet, bullet.getX(), bullet.getY());
      //  bullet.setVelocityX(2f);
       // bullet.setVelocityY(2f);
        
      //  }
        //health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 580, 800, 20);
        shapeRenderer.setColor(Color.GREEN);
        if(player.getHealth() != 0){
        shapeRenderer.rect(0, 580, player.getHealth(), 20);
        }
//        }else{ 
//            //end game
//        }
                
        
     
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
        for (int i = 0; i < world.getZombie().size() -1; i++) {
            
        
        if(zombie.get(i).isFacingNorth()){
            //draw the zombie N picture
            batch.draw(AssetManager.zombieN, zombie.get(i).getX(), zombie.get(i).getY());
        }//if zombie is facing S
        else if(zombie.get(i).isFacingSouth()){
            
            //draw the zombie S picture
            batch.draw(AssetManager.zombieS, zombie.get(i).getX(),zombie.get(i).getY());
        }//if zombie is facing E
        else if(zombie.get(i).isFacingEast()){
            //draw the zombie E picture
            batch.draw(AssetManager.zombieE, zombie.get(i).getX(), zombie.get(i).getY());
        }//if zombie facing W
        else if(zombie.get(i).isFacingWest()){
            //draw the zombie W picture
            batch.draw(AssetManager.zombieW, zombie.get(i).getX(), zombie.get(i).getY());
        }//if zombie facing NE
        else if(zombie.get(i).isFacingNE()){
            //draw the zombie NE picture
            batch.draw(AssetManager.zombieNE, zombie.get(i).getX(), zombie.get(i).getY());
        }//if zombie is facing NW
        else if(zombie.get(i).isFacingNW()){
            //draw the zombie NW picture
            batch.draw(AssetManager.zombieNW, zombie.get(i).getX(), zombie.get(i).getY());
        }//if zombie is facing SE
        else if(zombie.get(i).isFacingSE()){
            //draw the zombie SE picture
            batch.draw(AssetManager.zombieSE, zombie.get(i).getX(),zombie.get(i).getY());
        }//if zombie facing SW
        else if(zombie.get(i).isFacingSW()){
            //draw the zombie SW picture
            batch.draw(AssetManager.zombieSW, zombie.get(i).getX(), zombie.get(i).getY());
        }
        }
        // finished listing things to draw
        batch.end();
        shapeRenderer.end();
    
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
    float getMousePosInGameWorldx() {
 Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
 return n.x;
}
    float getMousePosInGameWorldy() {
 Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
 return n.y;
}
    
  
}
