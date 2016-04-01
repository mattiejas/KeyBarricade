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
    private boolean hasItem;

    private BufferedImage image;
    private Map map;
    private HUD hud;

    private int lastMove;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public Player(Map map, HUD hud) {
        this.image = ResourceLoader.getSprite(Sprite.PLAYER_DOWN);
        this.map = map;
        this.x = 0;
        this.y = 0;
        this.lastMove = 1;
        this.hasItem = false;
        this.hud = hud;
    }

    public void init() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.MAGENTA);

        if (hasItem) {
            switch (lastMove) {
                case UP:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_ITEM_UP);
                    break;
                default:
                case DOWN:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_ITEM_DOWN);
                    break;
                case LEFT:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_ITEM_LEFT);
                    break;
                case RIGHT:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_ITEM_RIGHT);
                    break;
            }
        } else {
            switch (lastMove) {
                case UP:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_UP);
                    break;
                default:
                case DOWN:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_DOWN);
                    break;
                case LEFT:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_LEFT);
                    break;
                case RIGHT:
                    this.image = ResourceLoader.getSprite(Sprite.PLAYER_RIGHT);
                    break;
            }
        }
        Color c = new Color(0, 0, 0, (float) 0.4);
        g.setColor(c);
        g.fillOval(x + 3, y + 48, Game.BLOCKSIZE - 6, 16);
        g.drawImage(image, x - 10, y - 24, (int) (Game.BLOCKSIZE * 1.3), (int) (Game.BLOCKSIZE * 1.3), null);
        if (hasItem) {
            g.drawImage(ResourceLoader.getSprite(Sprite.ITEM_KEY), x + 10, y - (int) (Game.BLOCKSIZE * 0.6), (int) (Game.BLOCKSIZE * 0.7), (int) (Game.BLOCKSIZE * 0.7), null);
        }
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
        }
        lastMove = UP;

    }

    private void moveDown() {
        if (map.playerAllowedToMoveDown()) {
            y += BLOCKSIZE;
        }
        lastMove = DOWN;

    }

    private void moveLeft() {
        if (map.playerAllowedToMoveLeft()) {
            x -= BLOCKSIZE;
        }
        lastMove = LEFT;

    }

    private void moveRight() {
        if (map.playerAllowedToMoveRight()) {
            x += BLOCKSIZE;
        }
        lastMove = RIGHT;

    }

    public void useKey() {
        BlockType block;
        try {
            switch (lastMove) {
                case UP:
                    block = map.getTile(x / Game.BLOCKSIZE, y / Game.BLOCKSIZE - 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                map.replaceTile(x, y - Game.BLOCKSIZE, new Barricade(0, true));
                                hud.setNewMessage(true);
                            } else {
                                hud.setNewMessage("That key doesn't fit.", false);
                            }
                        }
                    }
                    break;
                case DOWN:
                    block = map.getTile(x / Game.BLOCKSIZE, y / Game.BLOCKSIZE + 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                map.replaceTile(x, y + Game.BLOCKSIZE, new Barricade(0, true));
                                hud.setNewMessage(true);
                            } else {
                                hud.setNewMessage("That key doesn't fit.", false);
                            }
                        }
                    }
                    break;
                case LEFT:
                    block = map.getTile(x / Game.BLOCKSIZE - 1, y / Game.BLOCKSIZE).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                map.replaceTile(x - Game.BLOCKSIZE, y, new Barricade(0, true));
                                hud.setNewMessage(true);
                            } else {
                                hud.setNewMessage("That key doesn't fit.", false);
                            }
                        }
                    }
                    break;
                case RIGHT:
                    block = map.getTile(x / Game.BLOCKSIZE + 1, y / Game.BLOCKSIZE).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                map.replaceTile(x + Game.BLOCKSIZE, y, new Barricade(0, true));
                                hud.setNewMessage(true);
                            } else {
                                hud.setNewMessage("That key doesn't fit.", false);
                            }
                        }
                    }
                    break;
                default:
                    // chill the fuck down
                    break;
            }
        } catch (Exception e) {

        }
    }

    public void grabKey() {
        BlockType block = map.getTile(getPositionX() / (Game.BLOCKSIZE), getPositionY() / (Game.BLOCKSIZE)).getBlockType();
        if (block instanceof Key) {
            Key key = (Key) block;
            this.inventory = key;
            this.hasItem = true;
            hud.setNewMessage("Grabbed a key!", false);
            hud.setItem(hasItem, "Key", key.getPoints());
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
