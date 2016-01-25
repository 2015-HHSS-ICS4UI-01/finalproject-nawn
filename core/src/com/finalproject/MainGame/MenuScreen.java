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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.finalproject.Screens.AssetManager;

/**
 *
 * @author Walter
 */
public class MenuScreen implements Screen {

    Skin skin;
    Stage stage;
    SpriteBatch batch;
    int startButtonX = 275;
    Game g;

    public MenuScreen(Game g) {
        create();
        this.g = g;
    }

    public MenuScreen() {
        create();
    }

    public void create() {
        // play the start screen sound
        AssetManager.splash.play();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "black".
        Pixmap pixmap = new Pixmap(100, 75, Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        skin.add("black", new Texture(pixmap));

        BitmapFont bfont = new BitmapFont();
        BitmapFont titleFont = new BitmapFont();
        titleFont.getData().setScale(1);
        bfont.getData().setScale(1);
        skin.add("default", bfont);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("black", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("black", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("black", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("black", Color.LIGHT_GRAY);
        LabelStyle textLabelStyle = new LabelStyle();
        textLabelStyle.font = skin.getFont("default");

        textButtonStyle.font = skin.getFont("default");
        // Import the fonts for the start button and the instructions
        skin.add("default", textButtonStyle);
        skin.add("default", textLabelStyle);

        // Add the start button and instructions labels
        final TextButton textButton = new TextButton("Start", textButtonStyle);
        final Label title = new Label("Call of Duty Ghosts - NAWN Edition", textLabelStyle);
        final Label Instructions = new Label(" W, A, S, D keys to move the player around   ", textLabelStyle);
        final Label Instructions2 = new Label(" Left click to shoot   ", textLabelStyle);

        //set the position of the start button and the instructions labels
        title.setPosition(210, 400);
        Instructions.setPosition(190, 150);
        Instructions2.setPosition(280, 100);
        textButton.setPosition(275, 300);

        //import the buttons and labels into the gameScreen
        stage.addActor(title);
        stage.addActor(Instructions);
        stage.addActor(Instructions2);
        stage.addActor(textButton);

        //add a listener to determine when the start button has been Clicked
        textButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                //When the start button has been clicked
                // remove the start button
                // start the game
                // turn of the start screen music    
                textButton.remove();
                g.setScreen(new MainGame(g));
                AssetManager.splash.dispose();
            }
        });
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        stage.setDebugAll(true);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
