package com.finalproject.MainGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.finalproject.Screens.AssetManager;

public class MyGdxGame extends Game {
    

	@Override
	public void create () {
 // loads in the images
        AssetManager.load();
               setScreen(new MenuScreen(this));
            
                
	}

}
