package main;

import blocks.Barricade;
import blocks.BlockType;
import blocks.Ground;
import blocks.Key;
import blocks.Tile;
import blocks.Wall;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Map {

    private int width, height;
    private Tile[][] tiles;
    private int[][] generatedLevel;

    private Player player;
    private Level level;

    private Graphics2D g;

    private final int GROUND = 0;
    private final int WALL = 1;
    private final int BARRICADE = 2;
    private final int KEY = 3;

    public Map(int width, int height) {
        level = new Level(Difficulty.EASY);
        tiles = new Tile[10][10];
        this.width = width;
        this.height = height;
    }

    public void init() {
        level.init();
        generatedLevel = level.getLevel();
        int i = 0;
        loadLevel();
        player = new Player(this);
        player.init();
    }

    public void loadLevel() {
        for (int x = 0; x < generatedLevel.length; x++) {
            for (int y = 0; y < generatedLevel[x].length; y++) {
                if (generatedLevel[y][x] == GROUND) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, new Ground());
                }
                if (generatedLevel[y][x] == KEY) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, new Key(100));
                }
                if (generatedLevel[y][x] == WALL) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, new Wall());
                }
                if (generatedLevel[y][x] == BARRICADE) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, new Barricade(100));
                }
                tiles[0][0] = new Tile(0, 0, Game.BLOCKSIZE, Game.BLOCKSIZE, new Ground(true, false));
                tiles[9][9] = new Tile(9 * Game.BLOCKSIZE, 9 * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, new Ground(false, true));
            }
        }
    }

    public void render(Graphics2D g) {
        this.g = g;
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].render(g);
            }
        }

        player.render(g);
    }
    
    public Tile getTile(int x, int y){
        System.out.println("x:" + x + " y:" + y);
        return tiles[x][y]; 
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public boolean playerAllowedToMoveUp() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE)][player.getPositionY() / (Game.BLOCKSIZE) - 1].getSolid()
                    && player.getPositionY() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveDown() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE)][player.getPositionY() / (Game.BLOCKSIZE) + 1].getSolid()
                    && player.getPositionY() < Game.WINDOW_HEIGHT - Game.BLOCKSIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveLeft() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE) - 1][player.getPositionY() / (Game.BLOCKSIZE)].getSolid()
                    && player.getPositionX() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveRight() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE) + 1][player.getPositionY() / (Game.BLOCKSIZE)].getSolid()
                    && player.getPositionX() < Game.WINDOW_HEIGHT - Game.BLOCKSIZE;
        } catch (Exception e) {
            return false;
        }
    }

    public void replaceTile(int x, int y, BlockType block) {
        x = x / (Game.BLOCKSIZE);
        y = y / (Game.BLOCKSIZE);
        tiles[x][y] = new Tile(x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, block);
        System.out.println(tiles[x][y].getClass());
        this.render(g);
    }
}
