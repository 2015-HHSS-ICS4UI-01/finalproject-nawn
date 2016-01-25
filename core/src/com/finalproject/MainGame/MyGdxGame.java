package com.finalproject.MainGame;

import com.badlogic.gdx.Game;
import com.finalproject.Screens.AssetManager;

public class MyGdxGame extends Game {
    

	@Override
	public void create () {
        // loads in the images
        AssetManager.load();
        // Set the Screen to the menu screen when you run the game
        setScreen(new MenuScreen(this));
               
                
	}

}
