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
import main.Difficulty;
import main.Game;
import main.HUD;
import main.Player;

public class Map {

    private int[][] generatedLevel;
    private HashMap<Coordinate, Tile> map;
    private HashMap<Coordinate, Tile> restartMap;
    private ArrayList<Coordinate> coordinates;

    private Player player;
    private Level level;
    private final HUD HUD;

    protected static final int GROUND = 0;
    protected static final int WALL = 1;
    protected static final int BARRICADE = 2;
    protected static final int KEY = 3;

    public Map(Difficulty difficulty, HUD hud) {
        this.level = new Level(difficulty);
        this.map = new HashMap();
        this.restartMap = new HashMap();
        this.HUD = hud;
    }

    public void init() {
        level.init();
        coordinates = level.getCoordinates();
        generatedLevel = level.getLevel();
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

            if (x == 0 && y == 0) {
                map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(true, false)));
            } else if (x == Game.HORIZONTAL_AMOUNT - 1 && y == Game.VERTICAL_AMOUNT - 1) {
                map.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(false, true)));
            }
        }
    }

    public void reloadLevel() {
        loadLevel();
        player = new Player(this, HUD);
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void render(Graphics2D g) {
        for (Tile tile : map.values()) {
            tile.render(g);
        }

        player.render(g);
    }

    public Tile getTile(int x, int y) {
        return map.get(new Coordinate(x, y));
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public boolean playerAllowedToMoveUp() {
        int x = player.getArrayX();
        int y = player.getArrayY();

        if ((x >= 0 && x <= Game.HORIZONTAL_AMOUNT - 1) && (y > 0 && y <= Game.VERTICAL_AMOUNT - 1)) {
            return !map.get(new Coordinate(x, y - 1)).getSolid();
        } else {
            return false;
        }
    }

    public boolean playerAllowedToMoveDown() {
        int x = player.getArrayX();
        int y = player.getArrayY();

        if ((x >= 0 && x <= Game.HORIZONTAL_AMOUNT - 1) && (y >= 0 && y < Game.VERTICAL_AMOUNT - 1)) {
            return !map.get(new Coordinate(x, y + 1)).getSolid();
        } else {
            return false;
        }
    }

    public boolean playerAllowedToMoveLeft() {
        int x = player.getArrayX();
        int y = player.getArrayY();

        if ((x > 0 && x <= Game.HORIZONTAL_AMOUNT - 1) && (y >= 0 && y <= Game.VERTICAL_AMOUNT - 1)) {
            return !map.get(new Coordinate(x - 1, y)).getSolid();
        } else {
            return false;
        }
    }

    public boolean playerAllowedToMoveRight() {
        int x = player.getArrayX();
        int y = player.getArrayY();

        if ((x >= 0 && x < Game.HORIZONTAL_AMOUNT - 1) && (y >= 0 && y <= Game.VERTICAL_AMOUNT - 1)) {
            return !map.get(new Coordinate(x + 1, y)).getSolid();
        } else {
            return false;
        }
    }

    public void replaceTile(int x, int y, BlockType block) {
        map.put(new Coordinate(x, y), new Tile(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, block));
    }
}
