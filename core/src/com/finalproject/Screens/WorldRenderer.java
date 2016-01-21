/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.finalproject.Model.Block;
import com.finalproject.Model.Bullet;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import static com.finalproject.Screens.AssetManager.cross;
import java.util.ArrayList;

/**
 *
 * @author vonhn
 */
public class WorldRenderer {

    // my games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;

    private World world;
    private Player player;
    private ArrayList<Zombie> zombie;
    // private Bullet bullet;

    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private int mouseX;
    private int mouseY;

    private int health;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionBlocks;
    private int levelWidth;
    private int zombiesLeft;
    private BitmapFont text;

    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();
        zombie = world.getZombie();

        health = world.getPlayer().getHealth();
        zombiesLeft = world.getZombie().size();
        //when they die decrease number zombie
        //  bullet = world.getBullet();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        text = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        // move the x position of the camera
        camera.position.x = V_WIDTH / 2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT / 2f;

        // update the camera
        camera.update();

        // loads in the images
        AssetManager.load();

        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("Cursor.png")), 0, 0));

        //map
        map = new TmxMapLoader().load("zombieMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f, batch);
        renderer.setView(camera);

        // create the collision rectangles for our map based on the map layer
        collisionBlocks = new Array<Rectangle>();
        // get collision layer
        TiledMapTileLayer solidBlocks = (TiledMapTileLayer) map.getLayers().get("walls");

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);

        levelWidth = mapWidth;

        // loop through all of the cells, find a tile and make a rectangle
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (solidBlocks.getCell(x, y) != null) {
                    Rectangle r = new Rectangle(x, y, tileWidth, tileHeight);
                    System.out.println("x: " + r.x + "   y: " + r.y + "   tw: " + tileWidth + "   th: " + tileHeight);
                    collisionBlocks.add(r);
                }
            }
        }

    }

    public void render(float delta) {

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);

        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera
        //camera.position.x = Math.max(world.getPlayer().getX(), V_WIDTH / 2);
        //camera.position.y = Math.max(world.getPlayer().getY(), V_HEIGHT / 2);
        camera.position.x = Math.max(world.getPlayer().getX(), mapWidth / 2);
        camera.position.y = Math.max(world.getPlayer().getY(), mapHeight / 2);

        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);

        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);

        // render the tile map
        renderer.setView(camera);
        renderer.render();

        //initialize the starting state of the player
        player.setState(Player.State.STANDING);

        // tells the renderer this is the list to draw
        batch.begin();

        //crosshair
        mouseX = (int) this.getMousePosInGameWorldx();
        mouseY = (int) this.getMousePosInGameWorldy();
        batch.draw(cross, mouseX, mouseY);

        //health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 580, 800, 20);
        shapeRenderer.setColor(Color.GREEN);

        if (player.getHealth() != 0) {
            shapeRenderer.rect(0, 580, player.getHealth(), 20);
        }

        text.setColor(Color.WHITE);
        text.draw(batch, "zombies Left: " + zombiesLeft, 20, 570);

        //wall collisions
        for (Rectangle r : collisionBlocks) {

            if (player.getBounds().overlaps(r)) {
                float overX = Math.min(r.getX() + r.getWidth(), player.getX() + player.getWidth()) - Math.max(r.getX(), player.getX());
                float overY = Math.min(r.getY() + r.getHeight(), player.getY() + player.getHeight()) - Math.max(r.getY(), player.getY());
                if (player.getXVelocity() == 0) {
                    if (player.getY() < r.getY()) {
                        player.add(0, -overY);
                    } else {
                        player.add(0, overY);
                        player.land();
                    }
                    player.setVelocityY(0);
                }

                if (overX < overY) {
                    if (player.getX() < r.getX()) {
                        player.add(-overX, 0);
                    } else {
                        player.add(overX, 0);
                    }
                } else {
                    if (player.getY() < r.getY()) {
                        player.add(0, -overY);
                    } else {
                        player.add(0, overY);
                        player.land();
                    }
                    player.setVelocityY(0);
                }
            }
        }
        
        // move the camera to the correct position
        camera.position.x = Math.max(camera.viewportWidth / 2, player.getX());
        camera.position.x = Math.min(camera.position.x, levelWidth - camera.viewportWidth / 2);

        camera.position.y = Math.max(camera.viewportHeight / 2, player.getY());

        camera.update();

        //if the player is standing
        if (player.getState() == Player.State.STANDING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //output his standing south picture
                batch.draw(AssetManager.playerStandS, player.getX(), player.getY());
            }//check if hes facing north
            else if (player.isFacingNorth()) {
                //output his standing north picture
                batch.draw(AssetManager.playerStandN, player.getX(), player.getY());
            }//check if hes facing east
            else if (player.isFacingEast()) {
                //output his standing east picture
                batch.draw(AssetManager.playerStandE, player.getX(), player.getY());
            } else {
                //output his standing west picture
                batch.draw(AssetManager.playerStandW, player.getX(), player.getY());
            }

        } //check if hes running
        else if (player.getState() == Player.State.RUNNING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //play his walking south animation
                batch.draw(AssetManager.playerWalkS.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            } //check if hes facing north
            else if (player.isFacingNorth()) {
                //play his walking north animation
                batch.draw(AssetManager.playerWalkN.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            } //check if hes facing east
            else if (player.isFacingEast()) {
                //play his walking east animation
                batch.draw(AssetManager.playerWalkE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing west
            else if (player.isFacingWest()) {
                //play his walking west animation
                batch.draw(AssetManager.playerWalkW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing NE
            else if (player.isFacingNE()) {
                //play his walking NE animation
                batch.draw(AssetManager.playerWalkNE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing NWw
            else if (player.isFacingNW()) {
                //play his walking NW animation
                batch.draw(AssetManager.playerWalkNW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing SE
            else if (player.isFacingSE()) {
                //play his walking SE animation
                batch.draw(AssetManager.playerWalkSE.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }//check if hes facing SW
            else {
                //play his walking SW animation
                batch.draw(AssetManager.playerWalkSW.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }
        }

        //draw zombies
        for (int i = 0; i < world.getZombie().size(); i++) {
            if (zombie.get(i).isFacingNorth()) {
                //draw the zombie N picture
                batch.draw(AssetManager.zombieN, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie is facing S
            else if (zombie.get(i).isFacingSouth()) {

                //draw the zombie S picture
                batch.draw(AssetManager.zombieS, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie is facing E
            else if (zombie.get(i).isFacingEast()) {
                //draw the zombie E picture
                batch.draw(AssetManager.zombieE, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie facing W
            else if (zombie.get(i).isFacingWest()) {
                //draw the zombie W picture
                batch.draw(AssetManager.zombieW, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie facing NE
            else if (zombie.get(i).isFacingNE()) {
                //draw the zombie NE picture
                batch.draw(AssetManager.zombieNE, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie is facing NW
            else if (zombie.get(i).isFacingNW()) {
                //draw the zombie NW picture
                batch.draw(AssetManager.zombieNW, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie is facing SE
            else if (zombie.get(i).isFacingSE()) {
                //draw the zombie SE picture
                batch.draw(AssetManager.zombieSE, zombie.get(i).getX(), zombie.get(i).getY());
            }//if zombie facing SW
            else if (zombie.get(i).isFacingSW()) {
                //draw the zombie SW picture
                batch.draw(AssetManager.zombieSW, zombie.get(i).getX(), zombie.get(i).getY());
            }
        }
        // finished listing things to draw
        batch.end();
        shapeRenderer.end();

    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    float getMousePosInGameWorldx() {
        Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return n.x;
    }

    float getMousePosInGameWorldy() {
        Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return n.y;
    }

}
