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
import sun.font.TextLabel;

public class InstructionScreen implements Screen {
	Skin skin;
	Stage stage;
	SpriteBatch batch;
        private BitmapFont font;
        
        

	Game g;
	public InstructionScreen(Game g){
		create();
		this.g=g;
	}

	public InstructionScreen(){
		create();
	}
	public void create(){
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(100, 75, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();

		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
                BitmapFont titleFont = new BitmapFont();
                titleFont.getData().setScale(1);
		bfont.getData().setScale(1);
		skin.add("default",bfont);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
                LabelStyle textLabelStyle = new LabelStyle();
                textLabelStyle.font = skin.getFont("default");
                
		textButtonStyle.font = skin.getFont("default");

		skin.add("default", textButtonStyle);
                skin.add("default", textLabelStyle);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton textButton=new TextButton("Start",textButtonStyle);
                
                
		textButton.setPosition(300, 100);
                
                final Label title = new Label(" A, W, S, D keys to move the player around   ", textLabelStyle );
                final Label title2 = new Label(" Left click to shoot   ", textLabelStyle );
                title.setPosition(225, 300);
                title2.setPosition(290, 250);
                stage.addActor(title);
                stage.addActor(title2);
		stage.addActor(textButton);
		
		//stage.addActor(textButton);
                
               
                
                                textButton.addListener(new ChangeListener() {
                    
			public void changed (ChangeListener.ChangeEvent event, Actor actor) {
				//System.out.println("Clicked! Is checked: " + textButton.isChecked());
				//textButton.setText("Starting new game");
				g.setScreen(new MenuScreen());
                                
                                
			}
		});
                

                


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