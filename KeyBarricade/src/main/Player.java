package main;

import assets.ResourceLoader;
import assets.Sprite;
import blocks.Barricade;
import blocks.BlockType;
import blocks.Ground;
import blocks.Key;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static main.Game.BLOCKSIZE;

public class Player {

    private final int WIDTH = Game.BLOCKSIZE, HEIGHT = Game.BLOCKSIZE;
    private int x, y;
    
    private Key inventory;

    private BufferedImage image;
    private Map map;
    
    private int lastMove;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

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
        g.drawImage(image, x, y, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
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
        if (k == KeyEvent.VK_SPACE) {
            grabKey();
        }
        if (k == KeyEvent.VK_G) {
            useKey();
        }
    }

    private void moveUp() {
        if (map.playerAllowedToMoveUp()) {
            y -= BLOCKSIZE;
            System.out.println(getPositionX() / Game.BLOCKSIZE + " " + getPositionY() / Game.BLOCKSIZE);
        }
        lastMove = UP;
        
        
    }

    private void moveDown() {
        if (map.playerAllowedToMoveDown()) {
            y += BLOCKSIZE;
            System.out.println(getPositionX() / Game.BLOCKSIZE + " " + getPositionY() / Game.BLOCKSIZE);
        }
        lastMove = DOWN;
        
        
    }

    private void moveLeft() {
        if (map.playerAllowedToMoveLeft()) {
            x -= BLOCKSIZE;
            System.out.println(getPositionX() / Game.BLOCKSIZE + " " + getPositionY() / Game.BLOCKSIZE);
        }
        lastMove = LEFT;
        
        
    }

    private void moveRight() {
        if (map.playerAllowedToMoveRight()) {
            x += BLOCKSIZE;
            System.out.println(getPositionX() / Game.BLOCKSIZE + " " + getPositionY() / Game.BLOCKSIZE);
        }
        lastMove = RIGHT;
        
        
    }
    
    public void useKey(){
        BlockType block;
        switch (lastMove) {
            case UP:
                block = map.getTile(x / Game.BLOCKSIZE, y / Game.BLOCKSIZE - 1).getBlockType();
                if(block instanceof Barricade){
                    System.out.println("Instance of");
                    if(block.getPoints() == inventory.getPoints()){
                        map.replaceTile(x, y - 1, new Ground());
                        System.out.println("New ground");
                    }
                    
                }
                System.out.println(block.getPoints());
                System.out.println("Move up");
                break;
            case DOWN:
                block = map.getTile(x / Game.BLOCKSIZE, y / Game.BLOCKSIZE + 1).getBlockType();
                if(block instanceof Barricade){
                    System.out.println("Instance of");
                    if(block.getPoints() == inventory.getPoints()){
                        map.replaceTile(x, y + 1, new Ground());
                        System.out.println("New ground");
                    }
                    
                }
                System.out.println(block.getPoints());
                System.out.println("Move down");
                break;
            case LEFT:
                block = map.getTile(x / Game.BLOCKSIZE - 1, y / Game.BLOCKSIZE).getBlockType();
                if(block instanceof Barricade){
                    System.out.println("Instance of");
                    if(block.getPoints() == inventory.getPoints()){
                        map.replaceTile(x - 1, y, new Ground());
                        System.out.println("New ground");
                    }
                    
                }
                System.out.println(block.getPoints());
                System.out.println("Move left");
                break;
            case RIGHT:
                block = map.getTile(x / Game.BLOCKSIZE + 1, y / Game.BLOCKSIZE).getBlockType();
                if(block instanceof Barricade){
                    System.out.println("Instance of");
                    if(block.getPoints() == inventory.getPoints()){
                        map.replaceTile(x + 1, y, new Ground());
                        System.out.println("New ground");
                    }
                    
                }
                System.out.println(block.getPoints());
                System.out.println("Move right");
                break;
            default:
                // chill the fuck down
                break;
        }
    }

//    public void useKey() {
//        BlockType block;
//        switch (lastMove) {
//            case 0:
//                block = map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) - 1).getBlockType();
//                if(block instanceof Barricade){
//                    if(block.getPoints() == inventory.getPoints()){
//                        map.replaceTile(getPositionX() , getPositionY() - 1, new Ground());
//                        System.out.println(map.getTile(getPositionX() , getPositionY() + 1).getPositionX() + map.getTile(getPositionX() , getPositionY() + 1).getPositionY());
//                    }
//                }   break;
//            case 1:
//                block = map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getBlockType();
//                if(block instanceof Barricade){
//                    if(block.getPoints() == inventory.getPoints()){
//                        map.replaceTile(getPositionX() , getPositionY() + 1, new Ground());
//                        System.out.println(map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionX() + "Test " +map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionY());
//                    }
//                }   break;
//            case 2:
//                block = map.getTile(getPositionX() / (Game.BLOCKSIZE) - 1, getPositionY() / (Game.BLOCKSIZE)).getBlockType();
//                if(block instanceof Barricade){
//                    if(block.getPoints() == inventory.getPoints()){
//                        map.replaceTile(getPositionX() - 1, getPositionY(), new Ground());
//                        System.out.println(map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionX() + "Test " + map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionY());
//                    }
//                }   break;
//            case 3:
//                block = map.getTile(getPositionX() / (Game.BLOCKSIZE) + 1, getPositionY() / (Game.BLOCKSIZE)).getBlockType();
//                if(block instanceof Barricade){
//                    if(block.getPoints() == inventory.getPoints()){
//                        map.replaceTile(getPositionX() + 1, getPositionY(), new Ground());
//                        System.out.println(map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionX() + "Test " + map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE) + 1).getPositionY());
//                    }
//                }   break;
//            default:
//                break;
//        }
//        
//    }

    public void grabKey() {
        BlockType block = map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE)).getBlockType();
            System.out.println(block);
            if (block instanceof Key) {
                Key key = (Key) block;
                this.inventory = key;
                System.out.println(key);
                System.out.println("Grabbed a key!");
                map.replaceTile(x, y, new Ground());
            }
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

}
