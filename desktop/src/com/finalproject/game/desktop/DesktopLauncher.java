package com.finalproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.finalproject.MainGame.MainGame;
import com.finalproject.MainGame.MyGdxGame;

/**
 *
 * @author NAWN
 */
public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MyGdxGame(), config);

    }
}
