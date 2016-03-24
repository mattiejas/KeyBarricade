package main;

import blocks.BlockType;
import blocks.Ground;
import blocks.Tile;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Map {

    private int width, height;
    private Player player;
    private ArrayList<Tile> tiles;

    public Map(int width, int height) {
        tiles = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public void init() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground()));
            }
        }
        System.out.println(width + ", " + height);
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        for (Tile tile : tiles) {
            tile.render(g);
        }
    }
}
