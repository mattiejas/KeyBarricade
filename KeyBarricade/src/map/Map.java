package map;

import blocks.Barricade;
import blocks.BlockType;
import blocks.Ground;
import blocks.Key;
import blocks.Tile;
import blocks.Wall;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import main.Difficulty;
import main.Game;
import main.HUD;
import main.Player;

public class Map {

    private int[][] generatedLevel;
    private HashMap<Coordinate, Tile> map;
    private ArrayList<Coordinate> coordinates;

    private Player player;
    private final Level LEVEL;
    private final HUD HUD;

    private Graphics2D g;

    protected static final int GROUND = 0;
    protected static final int WALL = 1;
    protected static final int BARRICADE = 2;
    protected static final int KEY = 3;

    public Map(Difficulty difficulty, HUD hud) {
        this.LEVEL = new Level(difficulty);
        this.map = new HashMap();
        this.HUD = hud;
    }

    public void init() {
        LEVEL.init();
        coordinates = LEVEL.getCoordinates();
        generatedLevel = LEVEL.getLevel();
        loadLevel();
        player = new Player(this, HUD);
    }

    public void loadLevel() {
        System.out.println("Level grootte: " + generatedLevel.length);

        for (Coordinate coordinate : coordinates) {
            int pixelsX = coordinate.getX() * Game.BLOCK_SIZE;
            int pixelsY = coordinate.getY() * Game.BLOCK_SIZE;
            int x = coordinate.getX();
            int y = coordinate.getY();

            switch (generatedLevel[x][y]) {
                default:
                case GROUND:
                    map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground()));
                    if (x == 0 && y == 0) {
                        map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(true, false)));
                    } else if (x == Game.HORIZONTAL_AMOUNT - 1 && y == Game.VERTICAL_AMOUNT - 1) {
                        map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(false, true)));
                    }
                    break;
                case WALL:
                    map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Wall()));
                    break;
                case BARRICADE:
                    map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Barricade(100, false)));
                    break;
                case KEY:
                    map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Key(100)));
                    break;
            }
        }

    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void render(Graphics2D g) {
        this.g = g;
        for (Tile tile : map.values()) {
            tile.render(g);
        }
        player.render(g);
    }

    public Tile getTile(Coordinate c) {
        return map.get(c);
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public boolean playerAllowedToMoveUp() {
        try {
            return false;
//            return 
//                    && player.getPositionY() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveDown() {
        try {
            return false;
            // return !TILE[player.getArrayX()][player.getArrayY() + 1].getSolid()
            //       && player.getPositionY() < Game.WINDOW_HEIGHT - Game.BLOCK_SIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveLeft() {
        try {
            return false;
            //return !TILE[player.getArrayX() - 1][player.getArrayY()].getSolid()
            //      && player.getPositionX() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveRight() {
        try {
            return false;
            //return !TILE[player.getArrayX() + 1][player.getArrayY()].getSolid()
            //      && player.getPositionX() < Game.WINDOW_HEIGHT - Game.BLOCK_SIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public void replaceTile(int x, int y, BlockType block) {
        //TILE[x][y] = new Tile(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, block);
        this.render(g);
    }
}
