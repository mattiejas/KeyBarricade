package main;

import blocks.Barricade;
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

    private final int GROUND = 0;
    private final int WALL = 1;
    private final int BARRICADE = 2;
    private final int KEY = 3;

    public Map(int width, int height) {
        level = new Level(Difficulty.EASY);

        //tiles = new ArrayList<>();
        tiles = new Tile[10][10];
        this.width = width;
        this.height = height;
    }

    public void init() {
        level.init();
        generatedLevel = level.getLevel();
//        for (int x = 0; x < width / 32; x++) {
//            for (int y = 0; y < height / 32; y++) {
//                tiles.add(new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground()));
//            }
//        }
        int i = 0;
        for (int x = 0; x < generatedLevel.length; x++) {
            for (int y = 0; y < generatedLevel[x].length; y++) {
                if (generatedLevel[y][x] == GROUND) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground());
                }
                if (generatedLevel[y][x] == KEY) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Key(100));
                }
                if (generatedLevel[y][x] == WALL) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Wall());
                }
                if (generatedLevel[y][x] == BARRICADE) {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Barricade(100));
                }
                tiles[0][0] = new Tile(0, 0, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground(true, false));
                tiles[9][9] = new Tile(9 * Game.BLOCKSIZE * Game.SCALE, 9 * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground(false, true));
            }
        }

        player = new Player();
        player.init();
        player.setMap(this);
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].render(g);
            }
        }

        player.render(g);
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public boolean playerAllowedToMoveUp() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE * Game.SCALE)][player.getPositionY() / (Game.BLOCKSIZE * Game.SCALE) - 1].getIntersects(player)
                    && player.getPositionY() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveDown() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE * Game.SCALE)][player.getPositionY() / (Game.BLOCKSIZE * Game.SCALE) + 1].getIntersects(player)
                    && player.getPositionY() < Game.WINDOW_HEIGHT - Game.BLOCKSIZE * Game.SCALE;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveLeft() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE * Game.SCALE) - 1][player.getPositionY() / (Game.BLOCKSIZE * Game.SCALE)].getIntersects(player)
                    && player.getPositionX() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playerAllowedToMoveRight() {
        try {
            return !tiles[player.getPositionX() / (Game.BLOCKSIZE * Game.SCALE) + 1][player.getPositionY() / (Game.BLOCKSIZE * Game.SCALE)].getIntersects(player)
                    && player.getPositionX() < Game.WINDOW_HEIGHT - Game.BLOCKSIZE * Game.SCALE;
        } catch (Exception e) {
            return false;
        }
    }

    public Player getPlayer() {
        return player;
    }
}
