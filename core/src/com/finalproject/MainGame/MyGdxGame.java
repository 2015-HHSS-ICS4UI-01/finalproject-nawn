package com.finalproject.MainGame;

import com.badlogic.gdx.Game;
import com.finalproject.Screens.AssetManager;

public class MyGdxGame extends Game {
    

	@Override
	public void create () {
        // loads in the images
        AssetManager.load();
        setScreen(new MenuScreen(this));
               
                
	}

}
