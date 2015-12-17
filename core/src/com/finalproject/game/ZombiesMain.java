package com.finalproject.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

public class ZombiesMain extends ApplicationAdapter implements Screen{
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
	// default render
	}

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
