/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Screens;

import com.badlogic.gdx.Gdx;
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
import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.finalproject.Model.Bullet;
import com.finalproject.Model.Player;
import com.finalproject.Model.World;
import com.finalproject.Model.Zombie;
import static com.finalproject.Screens.AssetManager.bullet;
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

    //variable for the world
    private World world;
    //variable for the player 
    private Player player;
    //an array to store zombies
    private ArrayList<Zombie> zombie;

    //camera variables and game variables
    private Viewport viewport;
    private OrthographicCamera camera;
    //variable to import images
    private SpriteBatch batch;
    //variable for the renderer for shapes
    private ShapeRenderer shapeRenderer;

    //mouse variables
    private int mouseX;
    private int mouseY;
    //health variables
    private int health;
    //map variables
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionBlocks;
    private int levelWidth;
    //integer for the amount of zombies left
    private int zombiesLeft;
    //variable for in-game text
    private BitmapFont text;
    //array of bullets
    private ArrayList<Bullet> bullet1;
    private int playercomp;
    //game starts at level 1
    private int level = 1;

    public WorldRenderer(World w) {
        playercomp = -8;
        //initialize the world
        world = w;
        //initialize the player
        player = world.getPlayer();
        //initialize the zombie
        zombie = world.getZombie();
        //initialize the health variable
        health = world.getPlayer().getHealth();
        //initialize the amount of zombies left in the game
        zombiesLeft = world.getZombie().size();
        //initialize the bullet
        bullet1 = world.getBullet();
        //initialize the camera variables 
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        //initialize the text and shapes
        text = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        //initialize the cameras x position
        camera.position.x = V_WIDTH / 2f;
        // initialize the cameras y position
        camera.position.y = V_HEIGHT / 2f;
        // update the camera
        camera.update();

        //import the image of the cursor 
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

    public void render(float delta) throws InterruptedException {

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);

        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stop following player when he reaches the left side of the screen
        if (player.getX() >= V_WIDTH / 2) {
            if (player.getX() < 1600 - (V_WIDTH / 2)) {
                camera.position.x = Math.max(world.getPlayer().getX(), mapWidth / 2);
            }
        }

        //stop following player when he reaches the top of the screen
        if (player.getY() >= V_HEIGHT / 2) {
            if (player.getY() < 1620 - (V_HEIGHT / 2)) {
                camera.position.y = Math.max(world.getPlayer().getY(), mapHeight / 2);
            }

        }

        //update the camera
        camera.update();

        //links the shaperenderer to the camera
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
        batch.draw(cross, mouseX - 16, mouseY - 15);

        //draw the bullets in the array
        for (int i = 0; i < world.getBullet().size(); i++) {
            batch.draw(bullet, world.getBullet().get(i).getx(), world.getBullet().get(i).gety());
        }

        //if there are no more zombies appearing
        if (zombie.size() == 1) {
            //increase the level
            level++;
            //if level one is done go to level two
            if (level == 2) {
                levelTwo();
            }
            //if level two is done go to level three
            if (level == 3) {
                levelThree();
            }
        }

        //draw the health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        //draw the bar accoring to the position of the player
        shapeRenderer.rect(camera.position.x - V_WIDTH / 2, camera.position.y + 280, 800, 20);
        shapeRenderer.setColor(Color.GREEN);

        //if player is done draw the red health bar(overlaying)
        if (player.getHealth() != 0) {
            shapeRenderer.rect(camera.position.x - V_WIDTH / 2, camera.position.y + 280, player.getHealth(), 20);
        }

        //set the color of the text to white
        text.setColor(Color.WHITE);
        //draw the amount of zombies left and the level the user is at
        text.draw(batch, "Z o m b i e s  L e f t : " + zombiesLeft, camera.position.x - 370, camera.position.y + 250);
        text.draw(batch, "L e v e l : " + level, camera.position.x + 250, camera.position.y + 250);

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

        //if the player is standing
        if (player.getState() == Player.State.STANDING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //output his standing south picture
                batch.draw(AssetManager.playerStandS, player.getX() + playercomp, player.getY() + playercomp);
            }//check if hes facing north
            else if (player.isFacingNorth()) {
                //output his standing north picture
                batch.draw(AssetManager.playerStandN, player.getX() + playercomp, player.getY() + playercomp);
            }//check if hes facing east
            else if (player.isFacingEast()) {
                //output his standing east picture
                batch.draw(AssetManager.playerStandE, player.getX() + playercomp, player.getY() + playercomp);
            } else {
                //output his standing west picture
                batch.draw(AssetManager.playerStandW, player.getX() + playercomp, player.getY() + playercomp);
            }

        } //check if hes running
        else if (player.getState() == Player.State.RUNNING) {
            //check if hes facing south
            if (player.isFacingSouth()) {
                //play his walking south animation
                batch.draw(AssetManager.playerWalkS.getKeyFrame(player.getStateTime(), true), player.getX() + playercomp, player.getY() + playercomp);
            } //check if hes facing north
            else if (player.isFacingNorth()) {
                //play his walking north animation
                batch.draw(AssetManager.playerWalkN.getKeyFrame(player.getStateTime(), true), player.getX() + playercomp, player.getY() + playercomp);
            } //check if hes facing east
            else if (player.isFacingEast()) {
                //play his walking east animation
                batch.draw(AssetManager.playerWalkE.getKeyFrame(player.getStateTime(), true), player.getX() + playercomp, player.getY() + playercomp);
            }//check if hes facing west
            else if (player.isFacingWest()) {
                //play his walking west animation
                batch.draw(AssetManager.playerWalkW.getKeyFrame(player.getStateTime(), true), player.getX() + playercomp, player.getY() + playercomp);
            }
        }

        //draw zombie
        int zcomp = 8;

        for (int i = 0; i < world.getZombie().size(); i++) {
            if (zombie.get(i).isAlive()) {
                // zombie facing NE
                if (zombie.get(i).isFacingNE()) {
                    //draw the zombie NE picture
                    batch.draw(AssetManager.zombieNE, zombie.get(i).getX() - zcomp, zombie.get(i).getY() - zcomp);
                }//if zombie is facing NW
                else if (zombie.get(i).isFacingNW()) {
                    //draw the zombie NW picture
                    batch.draw(AssetManager.zombieNW, zombie.get(i).getX() - zcomp, zombie.get(i).getY() - zcomp);
                }//if zombie is facing SE
                else if (zombie.get(i).isFacingSE()) {
                    //draw the zombie SE picture
                    batch.draw(AssetManager.zombieSE, zombie.get(i).getX() - zcomp, zombie.get(i).getY() - zcomp);
                }//if zombie facing SW
                else if (zombie.get(i).isFacingSW()) {
                    //draw the zombie SW picture
                    batch.draw(AssetManager.zombieSW, zombie.get(i).getX() - zcomp, zombie.get(i).getY() - zcomp);
                }
            }
        }
        //create a new variable for zombies
        int z = 0;
        //loops through all the zombies
        for (int i = 0; i < zombie.size(); i++) {
            //if they are zombie is alive
            if (zombie.get(i).isAlive()) {
                //increase the variable 
                z++;
            }
        }
        //zombies left is equal to the variable - 1 because of the way the array is set up
        zombiesLeft = z - 1;

        // finished listing things to draw
        batch.end();
        shapeRenderer.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    float getMousePosInGameWorldx() {
        Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        world.setCursorX(n.x);
        return n.x;
    }

    float getMousePosInGameWorldy() {
        Vector3 n = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        world.setCursorY(n.y);
        return n.y;
    }

    private void levelTwo() {
        //add new and more zombies to the array for the second level
        for (int i = 0; i <= 6; i++) {
            //create a random number between 1 and 3
            int rand = random.nextInt(3) + 1;
            if (i == 0) {
                //for the first set of zombies
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(390, 0, 16, 16);
                    Zombie f = new Zombie(1400, 1590, 16, 16);
                    Zombie g = new Zombie(-20, 375, 16, 16);
                    Zombie h = new Zombie(1570, 1190, 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(1420, 0, 16, 16);
                    Zombie f = new Zombie(900, 1590, 16, 16);
                    Zombie g = new Zombie(-20, 550, 16, 16);
                    Zombie h = new Zombie(1570, 1420, 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(390, 0, 16, 16);
                    Zombie f = new Zombie(200, 1590, 16, 16);
                    Zombie g = new Zombie(-20, 1400, 16, 16);
                    Zombie h = new Zombie(1570, 940, 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                }
                //if i is not 0
            } else {
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie f = new Zombie(1400, 1590 * (i / 10), 16, 16);
                    Zombie g = new Zombie(-100 * (i / 10), 375, 16, 16);
                    Zombie h = new Zombie(1570 * (i / 10), 1190, 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(1420 * (i / 10), 0, 16, 16);
                    Zombie f = new Zombie(900, 1590 * (i / 10), 16, 16);
                    Zombie g = new Zombie(-100 * (i / 10), 550, 16, 16);
                    Zombie h = new Zombie(1570, 1420 * (i / 10), 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie e = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie f = new Zombie(200, 1590 * (i / 10), 16, 16);
                    Zombie g = new Zombie(-100 * (i / 10), 1400, 16, 16);
                    Zombie h = new Zombie(1570, 940 * (i / 10), 16, 16);
                    zombie.add(e);
                    zombie.add(f);
                    zombie.add(g);
                    zombie.add(h);
                }
            }
        }
    }

    private void levelThree() {
        //add new and more zombies to the array for the second level
        for (int i = 0; i <= 9; i++) {
            //create a random number between 1 and 3
            int rand = random.nextInt(3) + 1;
            //for the first set of zombies
            if (i == 0) {
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(390, 0, 16, 16);
                    Zombie k = new Zombie(1400, 1590, 16, 16);
                    Zombie l = new Zombie(-20, 375, 16, 16);
                    Zombie m = new Zombie(1570, 1190, 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(1420, 0, 16, 16);
                    Zombie k = new Zombie(900, 1590, 16, 16);
                    Zombie l = new Zombie(-20, 550, 16, 16);
                    Zombie m = new Zombie(1570, 1420, 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(390, 0, 16, 16);
                    Zombie k = new Zombie(200, 1590, 16, 16);
                    Zombie l = new Zombie(-20, 1400, 16, 16);
                    Zombie m = new Zombie(1570, 940, 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                }

            } else {
                //if the random number is 1
                if (rand == 1) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie k = new Zombie(1400, 1590 * (i / 10), 16, 16);
                    Zombie l = new Zombie(-100 * (i / 10), 375, 16, 16);
                    Zombie m = new Zombie(1570 * (i / 10), 1190, 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                    //if the random number is 2
                } else if (rand == 2) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(1420 * (i / 10), 0, 16, 16);
                    Zombie k = new Zombie(900, 1590 * (i / 10), 16, 16);
                    Zombie l = new Zombie(-100 * (i / 10), 550, 16, 16);
                    Zombie m = new Zombie(1570, 1420 * (i / 10), 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                    //if the random number is 3
                } else if (rand == 3) {
                    //add this set of zombies to the array
                    Zombie j = new Zombie(390 * (i / 10), 0, 16, 16);
                    Zombie k = new Zombie(200, 1590 * (i / 10), 16, 16);
                    Zombie l = new Zombie(-100 * (i / 10), 1400, 16, 16);
                    Zombie m = new Zombie(1570, 940 * (i / 10), 16, 16);
                    zombie.add(j);
                    zombie.add(k);
                    zombie.add(l);
                    zombie.add(m);
                }
            }
        }
    }
}
