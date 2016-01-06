/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.libgdxdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author lamonta
 */
public class GameScreen implements Screen{

    // my games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture logo;
    
    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        logo = new Texture("badlogic.jpg");
        
        // move the x position of the camera
        camera.position.x = V_WIDTH/2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        // update the camera
        camera.update();
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float deltaTime) {
        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // move the camera with the keyboard
        if(Gdx.input.isKeyPressed(Keys.D)){
            camera.position.x += 2f;
        }else if(Gdx.input.isKeyPressed(Keys.A)){
            camera.position.x -= 2f;
        }
        // update the camera
        camera.update();
        
        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);
        
        // tells the renderer this is the list
        batch.begin();
        // list of things to draw
        batch.draw(logo, 300, 200);
        // finished listing things to draw
        batch.end();
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    
}
