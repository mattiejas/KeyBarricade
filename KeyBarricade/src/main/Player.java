package main;

import assets.ResourceLoader;
import assets.Sprite;
import blocks.Ground;
import blocks.Key;
import blocks.Tile;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static main.Game.BLOCKSIZE;
import static main.Game.SCALE;

public class Player {

    private final int WIDTH = Game.BLOCKSIZE * Game.SCALE, HEIGHT = Game.BLOCKSIZE * Game.SCALE;
    private int x, y;

    private Key inventory;

    private BufferedImage image;
    private Map map;

    public Player(Map map) {
        this.image = ResourceLoader.getSprite(Sprite.PLAYER_DOWN);
        this.map = map;
        this.x = 0;
        this.y = 0;
    }

    public void init() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.drawImage(image, x, y, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, null);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
            moveUp();
        }
        if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
            moveDown();
        }
        if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
            moveLeft();
        }
        if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
            moveRight();
        }
    }

    private void moveUp() {
        if (map.playerAllowedToMoveUp()) {
            y -= BLOCKSIZE * SCALE;
        }
    }

    private void moveDown() {
        if (map.playerAllowedToMoveDown()) {
            y += BLOCKSIZE * SCALE;
        }
    }

    private void moveLeft() {
        if (map.playerAllowedToMoveLeft()) {
            x -= BLOCKSIZE * SCALE;
        }
    }

    private void moveRight() {
        if (map.playerAllowedToMoveRight()) {
            x += BLOCKSIZE * SCALE;
        }
    }

    private void useKey() {
        
    }

    public void grabKey(Key key) {
            this.inventory = key;
            System.out.println("Grabbed a key!");
            map.replaceTile(x, y, new Ground());
            //map.loadLevel();
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

}
