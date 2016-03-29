package main;

import blocks.Ground;
import blocks.Tile;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Map {

    private int width, height;
    private Player player;
    private ArrayList<Tile> tiles;
    //private Tile[][] tiles2D;

    public Map(int width, int height) {
        tiles = new ArrayList<>();
        //tiles2D = new Tile[10][10];
        this.width = width;
        this.height = height;
    }

    public void init() {
        for (int x = 0; x < width / 32; x++) {
            for (int y = 0; y < height / 32; y++) {
                tiles.add(new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground()));
            }
        }
    }

    public void update() {
        
    }

    public void render(Graphics2D g) {
        for (Tile tile : tiles) {
            tile.render(g);
        }
    }
}
