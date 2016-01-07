/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

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
    public static TextureRegion block;
    public static TextureRegion playerStand;
    public static TextureRegion playerStandL;
    public static TextureRegion playerJump;
    public static Animation playerRun;
    public static Animation playerRunL;
    
    public static void load(){
        atlas = new TextureAtlas("mario.pack");
        block = atlas.findRegion("stoneBlock");
        playerJump = atlas.findRegion("jump");
        playerStand = atlas.findRegion("stand");
        playerStandL = new TextureRegion(playerStand);
        playerStandL.flip(true, false);
       
        
        Array<AtlasRegion> run = atlas.findRegions("run");
        playerRun = new Animation(0.1f, run);
        
        run = atlas.findRegions("run");
        playerRunL = new Animation(0.1f, run);
        for(TextureRegion r: playerRunL.getKeyFrames()){
            r.flip(true,false);
        }
    }
}
