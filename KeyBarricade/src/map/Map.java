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
import main.Game;
import main.HUD;
import main.Player;

public class Map {

    private int[][] generatedLevel;
    private int[][] generatedPoints;
    private final HashMap<Coordinate, Tile> MAP;
    private ArrayList<Coordinate> coordinates;

    private Player player;
    private final Level LEVEL;
    private final HUD HUD;

    protected static final int GROUND = 0;
    protected static final int WALL = 1;
    protected static final int BARRICADE = 2;
    protected static final int KEY = 3;

    /**
     * Initialize a new Map with a difficulty and a HUD.
     *
     * @param difficulty the difficulty of this Map
     * @param hud HUD object that outputs to this Map
     */
    public Map(Difficulty difficulty, HUD hud) {
        this.LEVEL = new Level(difficulty);
        this.MAP = new HashMap();
        this.HUD = hud;
        this.player = new Player(this, HUD);
    }

    /**
     * Initializes everything that Map needs to start fresh.
     */
    public void init() {
        LEVEL.init();
        
        coordinates = LEVEL.getCoordinates();
        generatedLevel = LEVEL.getLevel();
        generatedPoints = LEVEL.getPoints();

        this.loadLevel();
    }

    /**
     * Renders every Tile that is put into the Map
     * 
     * @param g     Graphics2D object
     */
    public void render(Graphics2D g) {
        // Render each tile in MAP
        for (Tile tile : MAP.values()) {
            tile.render(g);
        }
        
        // Render the player
        player.render(g);
    }

    /**
     * Load the level in that Map draws.
     */
    public void loadLevel() {
        for (Coordinate coordinate : coordinates) {
            int pixelsX = coordinate.getX() * Game.BLOCK_SIZE;
            int pixelsY = coordinate.getY() * Game.BLOCK_SIZE;
            int x = coordinate.getX();
            int y = coordinate.getY();

            switch (generatedLevel[x][y]) {
                default:
                case GROUND:
                    MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground()));
                    break;
                case WALL:
                    MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Wall()));
                    break;
                case BARRICADE:
                    MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Barricade(generatedPoints[x][y], false)));
                    break;
                case KEY:
                    MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Key(generatedPoints[x][y])));
                    break;
            }

            if (x == 0 && y == 0) {
                MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(true, false)));
            } else if (x == Game.HORIZONTAL_AMOUNT - 1 && y == Game.VERTICAL_AMOUNT - 1) {
                MAP.put(coordinate, new Tile(pixelsX, pixelsY, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(false, true)));
            }
        }
    }

    /**
     * Reload the current level that Map draws 
     * and create a new instance of the player
     */
    public void reloadLevel() {
        this.loadLevel();
        player = new Player(this, HUD);
    }

    /**
     * Returns an ArrayList with coordinates.
     *
     * @return coordinates of this Map
     */
    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    /**
     * Return a Tile at a specified location
     *
     * @param x the location of the x-coordinate
     * @param y the location of the y-coordinate
     * @return a Tile at the specified location.
     */
    public Tile getTile(int x, int y) {
        return MAP.get(new Coordinate(x, y));
    }
    
    public Player getPlayer(){
        return this.player;
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    /**
     * Replace tile at the specified location.
     *
     * @param x location of the x-coordinate
     * @param y location of the y-coordinate
     * @param block Tile that replaces the Tile at (x, y)
     */
    public void replaceTile(int x, int y, BlockType block) {
        MAP.put(new Coordinate(x, y), new Tile(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, block));
    }
}
