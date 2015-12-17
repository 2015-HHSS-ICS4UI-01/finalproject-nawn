/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author admin
 */
public class MapRenderer {
    //virtual height and width
    public final int V_WIDTH = 0;
    public final int V_HEIGHT = 0;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    public MapRenderer(){
        //initialize camera
        camera = new OrthographicCamera();
        //initialize view
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        //intitialize sprites
        batch = new SpriteBatch();
    
    // move the x position of the camera
        camera.position.x = V_WIDTH/2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        // update the camera
        camera.update();
    }
}
