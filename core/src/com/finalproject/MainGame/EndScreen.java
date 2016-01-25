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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 *
 * @author Walter
 */
public class EndScreen implements Screen {

    Skin skin;
    Stage stage;
    SpriteBatch batch;
    private BitmapFont font;
    int startButtonX = 275;
    Game g;

    public EndScreen(Game g) {
        create();
        this.g = g;
    }

    public EndScreen() {
        create();
    }

    public void create() {
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
        // Import the fonts for the Loose label
        skin.add("default", textButtonStyle);
        skin.add("default", textLabelStyle);

        // Add the end screen label
        final Label title = new Label("  Y o u  L o s e !  ", textLabelStyle);
        title.setPosition(265, 300);
        stage.addActor(title);
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
