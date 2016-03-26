package main;

import blocks.Ground;
import blocks.Tile;
import java.awt.Graphics2D;

public class Map {

    private int width, height;
    private Player player;
    // private ArrayList<Tile> tiles;
    private Tile[][] tiles2D;

    public Map(int width, int height) {
        // tiles = new ArrayList<>();
        tiles2D = new Tile[10][10];
        this.width = width;
        this.height = height;
    }

    public void init() {
        for (int x = 0; x < tiles2D.length; x++) {
            for (int y = 0; y < tiles2D[0].length; y++) {
                tiles2D[x][y] = new Tile(x * Game.BLOCKSIZE * Game.SCALE, y * Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, new Ground());
            }
        }
        System.out.println("Grootte van map");
        System.out.println(width + ", " + height);
    }

    public void update() {
        System.out.println("Update map");
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < tiles2D.length; x++) {
            for (int y = 0; y < tiles2D[0].length; y++) {
                tiles2D[x][y].render(g);
            }
        }        
    }
}
