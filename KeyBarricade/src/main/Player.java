package main;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static main.Game.BLOCKSIZE;
import static main.Game.SCALE;

public class Player {

    private final int WIDTH = Game.BLOCKSIZE * Game.SCALE, HEIGHT = Game.BLOCKSIZE * Game.SCALE;
    private int x, y;

    private BufferedImage image;

    public Player() {
        this.image = ResourceLoader.getSprite(Sprite.PLAYER_DOWN);

        this.x = 0;
        this.y = 0;
    }

    public void init() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.drawImage(image, x, y, Game.BLOCKSIZE * Game.SCALE, Game.BLOCKSIZE * Game.SCALE, null);
    }
    
    public void keyPressed(int k){
        if (k == KeyEvent.VK_W) {
            moveUp();
        }
        if (k == KeyEvent.VK_S) {
            moveDown();
        }
        if (k == KeyEvent.VK_A) {
            moveLeft();
        }
        if (k == KeyEvent.VK_D) {
            moveRight();
        }
        
        if(k == KeyEvent.VK_SPACE){
            grabKey();
        }
    }
    
    public void moveUp(){
        y -= BLOCKSIZE * SCALE;
    }
    
    public void moveDown(){
        y += BLOCKSIZE * SCALE;
    }
    
    public void moveLeft(){
        x -= BLOCKSIZE * SCALE;
    }
    
    public void moveRight(){
        x += BLOCKSIZE * SCALE;
    }

    public void grabKey() {
        System.out.println("Grabbed a key!");
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

}
