package main;

import map.Map;
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
import static main.Game.BLOCK_SIZE;
import map.Coordinate;

public class Player {

    private int x, y;

    private Key inventory;
    private boolean hasItem;

    private BufferedImage image;
    private final Map MAP;
    private final HUD HUD;

    private int lastMove;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public Player(Map map, HUD hud) {
        this.image = ResourceLoader.getSprite(Sprite.PLAYER_DOWN);
        this.MAP = map;
        this.x = 0;
        this.y = 0;
        this.lastMove = 1;
        this.hasItem = false;
        this.HUD = hud;
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
        g.fillOval(x + 3, y + 48, Game.BLOCK_SIZE - 6, 16);
        g.drawImage(image, x - 10, y - 24, (int) (Game.BLOCK_SIZE * 1.3), (int) (Game.BLOCK_SIZE * 1.3), null);

        if (hasItem) {
            g.drawImage(ResourceLoader.getSprite(Sprite.ITEM_KEY),
                    x + 10, y - (int) (Game.BLOCK_SIZE * 0.6), (int) (Game.BLOCK_SIZE * 0.7), (int) (Game.BLOCK_SIZE * 0.7), null);
        }

        if ((getArrayX() == Game.VERTICAL_AMOUNT - 1) && (getArrayY() == Game.HORIZONTAL_AMOUNT - 1)) {
            HUD.winGame();
        }
    }

    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_SPACE:
                grabKey();
                break;
            case KeyEvent.VK_K:
                useKey();
                break;
        }
    }

    private void moveUp() {
        if (MAP.playerAllowedToMoveUp()) {
            y -= BLOCK_SIZE;
        }
        lastMove = UP;
    }

    private void moveDown() {
        if (MAP.playerAllowedToMoveDown()) {
            y += BLOCK_SIZE;
        }
        lastMove = DOWN;
    }

    private void moveLeft() {
        if (MAP.playerAllowedToMoveLeft()) {
            x -= BLOCK_SIZE;
        }
        lastMove = LEFT;
    }

    private void moveRight() {
        if (MAP.playerAllowedToMoveRight()) {
            x += BLOCK_SIZE;
        }
        lastMove = RIGHT;
    }

    public void useKey() {
        BlockType block;
        try {
            switch (lastMove) {
                case UP:
                    block = MAP.getTile(getArrayX(), getArrayY() - 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getArrayX(), getArrayY() - 1, new Barricade(0, true));
                                HUD.setNewMessage(true);
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                    break;
                case DOWN:
                    block = MAP.getTile(getArrayX(), getArrayY() + 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getArrayX(), getArrayY() + 1, new Barricade(0, true));
                                HUD.setNewMessage(true);
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                    break;
                case LEFT:
                    block = MAP.getTile(getArrayX() - 1, getArrayY()).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getArrayX() - 1, getArrayY(), new Barricade(0, true));
                                HUD.setNewMessage(true);
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                    break;
                case RIGHT:
                    block = MAP.getTile(getArrayX() + 1, getArrayY()).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getArrayX() + 1, getArrayY(), new Barricade(0, true));
                                HUD.setNewMessage(true);
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void grabKey() {
        BlockType block = MAP.getTile(getArrayX(), getArrayY()).getBlockType();
        if (block instanceof Key) {
            Key key = (Key) block;
            this.inventory = key;
            this.hasItem = true;
            HUD.setNewMessage("Grabbed a key!");
            HUD.setItem(hasItem, "Key", key.getPoints());
            MAP.replaceTile(getArrayX(), getArrayY(), new Ground());
        }
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public int getArrayX() {
        return getPositionX() / Game.BLOCK_SIZE;
    }

    public int getArrayY() {
        return getPositionY() / Game.BLOCK_SIZE;
    }

}
