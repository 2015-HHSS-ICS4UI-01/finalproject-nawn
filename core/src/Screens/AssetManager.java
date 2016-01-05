/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author alimu
 */
public class AssetManager {
    
    private static TextureAtlas atlas;
    public static TextureRegion block;
    public static TextureRegion marioStand;
    public static TextureRegion marioStandL;
    public static TextureRegion marioJump;
    public static Animation marioRun;
    public static Animation marioRunL;
    
    public static void load(){
        atlas = new TextureAtlas("mario.pack");
        block = atlas.findRegion("stoneBlock");
        marioStand = atlas.findRegion("stand");
        marioJump = atlas.findRegion("jump");
        marioStandL = new TextureRegion(marioStand);
        marioStandL.flip(true, false);
        
        Array<AtlasRegion> run = atlas.findRegions("run");
        marioRun = new Animation(0.1f, run);
        
        run = atlas.findRegions("run");
        marioRunL = new Animation(0.1f,run);
        for(TextureRegion r: marioRunL.getKeyFrames()){
            r.flip(true, false);
        }
        
    }
    
}
