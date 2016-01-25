/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author vonhn
 */
public class AssetManager {

    private static TextureAtlas atlas;

    //initialize all the animations for all directions for the player
    public static Animation playerWalkN;
    public static Animation playerWalkE;
    public static Animation playerWalkS;
    public static Animation playerWalkW;
    public static Animation playerWalkNW;
    public static Animation playerWalkNE;
    public static Animation playerWalkSE;
    public static Animation playerWalkSW;

    //initialize all the standing positions for the player
    public static TextureRegion playerStandS;
    public static TextureRegion playerStandN;
    public static TextureRegion playerStandE;
    public static TextureRegion playerStandW;

    public static TextureRegion zombieW;
    public static TextureRegion zombieE;
    public static TextureRegion zombieN;
    public static TextureRegion zombieS;
    public static TextureRegion zombieNW;
    public static TextureRegion zombieNE;
    public static TextureRegion zombieSE;
    public static TextureRegion zombieSW;
    public static TextureRegion bullet;
    public static TextureRegion cross;

    public static Music splash;
    public static Music game;
    public static Music gunShot;

    public static void load() {
        //find the file to look at
        atlas = new TextureAtlas("zombie.atlas");

        splash = Gdx.audio.newMusic(Gdx.files.internal("splash.mp3"));
        game = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
        gunShot = Gdx.audio.newMusic(Gdx.files.internal("gunShot.mp3"));

        //get the images of soldier in all his standing positions
        playerStandS = atlas.findRegion("soldier_standing_south");
        playerStandN = atlas.findRegion("soldier_standing_north");
        playerStandE = atlas.findRegion("soldier_standing_right");
        playerStandW = atlas.findRegion("soldier_standing_left");

        //get the images of the zombie in all walking positions
        zombieW = atlas.findRegion("zombie_right");
        zombieE = atlas.findRegion("zombie_left");
        zombieN = atlas.findRegion("zombie_north");
        zombieS = atlas.findRegion("zombie_south");
        zombieNW = atlas.findRegion("zombie_ne");
        zombieNE = atlas.findRegion("zombie_nw");
        zombieSW = atlas.findRegion("zombie_sw");
        zombieSE = atlas.findRegion("zombie_se");
        //bullet
        bullet = atlas.findRegion("bullet");

        //crosshair 
        cross = atlas.findRegion("cross");

        //make the animation of soldier walking north
        Array<AtlasRegion> runNorth = atlas.findRegions("soldier_walking_north");
        playerWalkN = new Animation(0.1f, runNorth);
        runNorth = atlas.findRegions("soldier_walking_north");

        //make the animation of the soldier walking south
        Array<AtlasRegion> runSouth = atlas.findRegions("soldier_walking_south");
        playerWalkS = new Animation(0.1f, runSouth);
        runSouth = atlas.findRegions("soldier_walking_south");

        //make the animation of the soldier walking West
        Array<AtlasRegion> runWest = atlas.findRegions("soldier_walking_left");
        playerWalkW = new Animation(0.1f, runWest);
        runWest = atlas.findRegions("soldier_walking_left");

        //make the animation of the soldier walking right
        Array<AtlasRegion> runEast = atlas.findRegions("soldier_walking_right");
        playerWalkE = new Animation(0.1f, runEast);
        runEast = atlas.findRegions("soldier_walking_right");

        //make the animation of the soldier walking NE
        Array<AtlasRegion> runNE = atlas.findRegions("soldier_walking_ne");
        playerWalkNE = new Animation(0.1f, runNE);
        runNE = atlas.findRegions("soldier_walking_ne");

        //make the animation of the soldier walking NW
        Array<AtlasRegion> runNW = atlas.findRegions("soldier_walking_nw");
        playerWalkNW = new Animation(0.1f, runNW);
        runNW = atlas.findRegions("soldier_walking_nw");

        //make the animation of the soldier walking SE
        Array<AtlasRegion> runSE = atlas.findRegions("soldier_walking_se");
        playerWalkSE = new Animation(0.1f, runSE);
        runSE = atlas.findRegions("soldier_walking_se");

        //make the animation of the soldier walking SW
        Array<AtlasRegion> runSW = atlas.findRegions("soldier_walking_sw");
        playerWalkSW = new Animation(0.1f, runSW);
        runSW = atlas.findRegions("soldier_walking_sw");
    }
}
