/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import Screens.AssetManager;
import com.alimurra.Model.Block;
import com.alimurra.Model.Mario;
import com.alimurra.Model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author alimu
 */
public class WorldRenderer {
    private World world;
    private Mario player;
    
    //games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    public WorldRenderer(World w){
        world = w;
        player = world.getPlayer();
        
        camera = new OrthographicCamera();
        viewport = new StretchViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        
        camera.position.x = V_WIDTH/2f;
        camera.position.y = V_HEIGHT/2f;
        camera.update();
        
        //loads in images
        AssetManager.load();
    }
    
    public void render(float delta){
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = Math.max(player.getX(), V_WIDTH/2);
        camera.update();
        //links renderer to camera
        batch.setProjectionMatrix(camera.combined);
        //tells the renderer this is the list
        batch.begin();
        //list of things to draw
        // Go through and draw the blocks
        for(Block b: world.getBlocks()){
            batch.draw(AssetManager.block, b.getX(), b.getY());
        }
        //draw mario
        if(player.getState() == Mario.State.STANDING){
            if(player.isFacingLeft()){
                batch.draw(AssetManager.marioStandL, player.getX(), player.getY());
            }else{
                batch.draw(AssetManager.marioStand, player.getX(), player.getY());
            }
            
        }else if(player.getState() == Mario.State.RUNNING){
            if(player.isFacingLeft()){
                batch.draw(AssetManager.marioRunL.getKeyFrame(player.getStateTime()),player.getX(), player.getY());
            }else{
                batch.draw(AssetManager.marioRun.getKeyFrame(player.getStateTime()),player.getX(), player.getY());
            }
        }else{
            batch.draw(AssetManager.marioJump, player.getX(), player.getY());
        }
        
        //finished listing things to draw
        batch.end();
    }
    
    public void resize(int width, int height){
         viewport.update(width, height);
    }
}
