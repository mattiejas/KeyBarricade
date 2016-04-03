package main;

import blocks.Barricade;
import blocks.BlockType;
import blocks.Ground;
import blocks.Key;
import blocks.Tile;
import blocks.Wall;
import java.awt.Graphics2D;
import java.util.Random;

public class Map {
    private Tile[][] TILE; 
    private int[][] generatedLevel;
    public static Tile[][] previousGeneratedTile = new Tile[Game.VERTICAL_AMOUNT][Game.HORIZONTAL_AMOUNT];
    
    private Player player;
    private final Level LEVEL;
    private final HUD HUD;

    private Graphics2D g;

    protected static final int GROUND = 0;
    protected static final int WALL = 1;
    protected static final int BARRICADE = 2;
    protected static final int KEY = 3;

    public Map(Difficulty difficulty, HUD hud) {
        this.TILE = new Tile[Game.VERTICAL_AMOUNT][Game.HORIZONTAL_AMOUNT];
        this.LEVEL = new Level(difficulty);
        this.HUD = hud;              
    }
    
    public void init() {
        LEVEL.init();
        generatedLevel = LEVEL.getLevel();
        this.loadLevel();
        this.setPreviousTile();
        player = new Player(this, HUD);
    }
    
    public void restart() {
        TILE = previousGeneratedTile;
        player = new Player(this, HUD);        
    }

    public void loadLevel() {
        System.out.println("Maar 1x");
        Random r = new Random();
        System.out.println(generatedLevel.length);
        for (int y = 0; y < generatedLevel.length; y++) {
            for (int x = 0; x < generatedLevel[y].length; x++) {
                int points = (r.nextInt(3) + 1) * 100;
                if (generatedLevel[y][x] == GROUND) {
                    TILE[y][x] = new Tile(y * Game.BLOCK_SIZE, x * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground());
                }
                if (generatedLevel[y][x] == KEY) {
                    TILE[y][x] = new Tile(y * Game.BLOCK_SIZE, x * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Key(points));
                }
                if (generatedLevel[y][x] == WALL) {
                    TILE[y][x] = new Tile(y * Game.BLOCK_SIZE, x * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Wall());
                }
                if (generatedLevel[y][x] == BARRICADE) {
                    TILE[y][x] = new Tile(y * Game.BLOCK_SIZE, x * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Barricade(points, false));
                }
                TILE[0][0] = new Tile(0, 0, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(true, false));
                TILE[Game.VERTICAL_AMOUNT - 1][Game.HORIZONTAL_AMOUNT - 1] = new Tile((Game.VERTICAL_AMOUNT - 1) * Game.BLOCK_SIZE, (Game.HORIZONTAL_AMOUNT - 1) * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, new Ground(false, true));
            }
        }
    }
    
    public void render(Graphics2D g) {
        this.g = g;
        for (int y = 0; y < TILE.length; y++) {
            for (int x = 0; x < TILE[y].length; x++) {
                TILE[y][x].render(g);
            }
        }
        player.render(g);
    } 

    public Tile getTile(int x, int y) {
        return TILE[x][y];
    }
    
    public Tile[][] getTileArray() {
        return this.TILE;
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public boolean playerAllowedToMoveUp() {
        try {
            return !TILE[player.getArrayX()][player.getArrayY() - 1].getSolid()
                    && player.getPositionY() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveDown() {
        try {
            return !TILE[player.getArrayX()][player.getArrayY() + 1].getSolid()
                    && player.getPositionY() < Game.WINDOW_HEIGHT - Game.BLOCK_SIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveLeft() {
        try {
            return !TILE[player.getArrayX() - 1][player.getArrayY()].getSolid()
                    && player.getPositionX() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveRight() {
        try {
            return !TILE[player.getArrayX() + 1][player.getArrayY()].getSolid()
                    && player.getPositionX() < Game.WINDOW_HEIGHT - Game.BLOCK_SIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public void replaceTile(int x, int y, BlockType block) {
        TILE[x][y] = new Tile(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, block);
        this.render(g);
    }

    private void setPreviousTile() {
        for (int y = 0; y < TILE.length; y++) {
            for (int x = 0; x < TILE[y].length; x++) {
                previousGeneratedTile[y][x] = TILE[y][x];
            }
        }
    }
}
