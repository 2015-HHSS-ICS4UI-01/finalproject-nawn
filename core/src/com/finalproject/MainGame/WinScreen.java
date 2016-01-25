/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.MainGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import com.finalproject.Screens.AssetManager;
import sun.font.TextLabel;
import static java.awt.SystemColor.text;
import java.util.ArrayList;

/**
 *
 * @author Walter
 */

public class WinScreen implements Screen {
	Skin skin;
	Stage stage;
	SpriteBatch batch;
        private BitmapFont font;
        int startButtonX = 275;
      
        private World world;
        private Player player;
      private ArrayList<Zombie> zombie;
        
	Game g;
	public WinScreen(Game g){
		create();
		this.g=g; 
                
                
	}

	public WinScreen(){
            world = new World();
            player = world.getPlayer();
            zombie = world.getZombie();
		create();
	}
	public void create(){
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(100, 75, Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fill();

		skin.add("black", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont bfont =new BitmapFont();
                BitmapFont titleFont = new BitmapFont();
                titleFont.getData().setScale(1);
		bfont.getData().setScale(1);
		skin.add("default",bfont);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("black", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("black", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("black", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("black", Color.LIGHT_GRAY);
                LabelStyle textLabelStyle = new LabelStyle();
                textLabelStyle.font = skin.getFont("default");
                
		textButtonStyle.font = skin.getFont("default");

		skin.add("default", textButtonStyle);
                skin.add("default", textLabelStyle);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		
                
               
               
                
                
                final Label title = new Label("  Y o u  W i n!  ", textLabelStyle ); 
                title.setPosition(265, 300);
                stage.addActor(title);
                
                       
//               stage
                
                
		
		//stage.addActor(textButton);
                
              
                
           
		
                
                                   
                


	}

	public void render (float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
                stage.setDebugAll(true);
                
                
               
		//Table.drawDebug(stage);
	}

	@Override
	public void resize (int width, int height) {
		//stage.setViewport(width, height);
           // stage.setViewport(new StretchViewport(width, height));
            
	}

	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
         //   Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}




                                