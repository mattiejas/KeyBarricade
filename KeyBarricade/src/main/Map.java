package main;

import blocks.Barricade;
import blocks.Ground;
import blocks.Key;
import blocks.Tile;
import blocks.Wall;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Map {

    private int width, height;
    private Tile[][] tiles;
    private char[][] generatedLevel;

    private Player player;
    private Level level;

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
                if (generatedLevel[y][x] == 'g') {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground());
                }
                if (generatedLevel[y][x] == 'k') {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Key(100));
                }
                if (generatedLevel[y][x] == 'w') {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Wall());
                }
                if (generatedLevel[y][x] == 'b') {
                    tiles[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Barricade(100));
                }
            }
        }

        player = new Player();
        player.init();
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
        player.move(k);
    }
}
