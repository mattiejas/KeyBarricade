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

        // Checks if player has an item in order to set a different sprite.
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

        // Draw the player and its shadow.
        Color c = new Color(0, 0, 0, (float) 0.4);
        g.setColor(c);
        g.fillOval(x + 3, y + 48, Game.BLOCK_SIZE - 6, 16);
        g.drawImage(image, x - 10, y - 24, (int) (Game.BLOCK_SIZE * 1.3), (int) (Game.BLOCK_SIZE * 1.3), null);

        // Draw the key when the player has an item.
        if (hasItem) {
            g.drawImage(ResourceLoader.getSprite(Sprite.ITEM_KEY),
                    x + 10, y - (int) (Game.BLOCK_SIZE * 0.6), (int) (Game.BLOCK_SIZE * 0.7), (int) (Game.BLOCK_SIZE * 0.7), null);
        }

        // Draws congratulation screen
        if ((getCoordinateX() == Game.HORIZONTAL_AMOUNT - 1) && (getCoordinateY() == Game.VERTICAL_AMOUNT - 1)) {
            HUD.winGame();
        }
    }

    /**
     * Moves the player according to the pressed key.
     *
     * @param k is the chosen keyCode in Game
     */
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

    /**
     * The player moves up and remember its last direction.
     */
    private void moveUp() {
        if ((getCoordinateX() >= 0 && getCoordinateX() <= Game.HORIZONTAL_AMOUNT - 1) && (getCoordinateY() > 0 && getCoordinateY() <= Game.VERTICAL_AMOUNT - 1)) {
            if (!MAP.getTile(getCoordinateX(), getCoordinateY() - 1).getSolid()) {
                y -= BLOCK_SIZE;
            }
        }
        lastMove = UP;
    }

    /**
     * The player moves down and remember its last direction.
     */
    private void moveDown() {
        if ((getCoordinateX() >= 0 && getCoordinateX() <= Game.HORIZONTAL_AMOUNT - 1) && (getCoordinateY() >= 0 && getCoordinateY() < Game.VERTICAL_AMOUNT - 1)) {
            if (!MAP.getTile(getCoordinateX(), getCoordinateY() + 1).getSolid()) {
                y += BLOCK_SIZE;
            }
        }
        lastMove = DOWN;
    }

    /**
     * The player moves left and remember its last direction.
     */
    private void moveLeft() {
        if ((getCoordinateX() > 0 && getCoordinateX() <= Game.HORIZONTAL_AMOUNT - 1) && (getCoordinateY() >= 0 && getCoordinateY() <= Game.VERTICAL_AMOUNT - 1)) {
            if (!MAP.getTile(getCoordinateX() - 1, getCoordinateY()).getSolid()) {
                x -= BLOCK_SIZE;
            }
        }
        lastMove = LEFT;
    }

    /**
     * The player moves right and remember its last direction.
     */
    private void moveRight() {
        if ((getCoordinateX() >= 0 && getCoordinateX() < Game.HORIZONTAL_AMOUNT - 1) && (getCoordinateY() >= 0 && getCoordinateY() <= Game.VERTICAL_AMOUNT - 1)) {
            if (!MAP.getTile(getCoordinateX() + 1, getCoordinateY()).getSolid()) {
                x += BLOCK_SIZE;
            }
        }
        lastMove = RIGHT;
    }

    /**
     * The player uses the key, MAP replaces the tile with a new BlockType
     * object and HUD sets a new message.
     */
    public void useKey() {
        BlockType block;
        switch (lastMove) {
            case UP:
                if (!((getCoordinateY() - 1) < 0)) { // Prevents ArrayIndexOutOfBoundsException
                    block = MAP.getTile(getCoordinateX(), getCoordinateY() - 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getCoordinateX(), getCoordinateY() - 1, new Barricade(0, true));
                                HUD.setNewMotivationMessage();
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                }
                break;
            case DOWN:
                if (!(getCoordinateY() + 1 > Game.VERTICAL_AMOUNT - 1)) { // Prevents ArrayIndexOutOfBoundsException
                    block = MAP.getTile(getCoordinateX(), getCoordinateY() + 1).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getCoordinateX(), getCoordinateY() + 1, new Barricade(0, true));
                                HUD.setNewMotivationMessage();
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                }
                break;
            case LEFT:
                if (!((getCoordinateX() - 1) < 0)) { // Prevents ArrayIndexOutOfBoundsException
                    block = MAP.getTile(getCoordinateX() - 1, getCoordinateY()).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getCoordinateX() - 1, getCoordinateY(), new Barricade(0, true));
                                HUD.setNewMotivationMessage();
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                if (!(getCoordinateX() + 1 > Game.HORIZONTAL_AMOUNT - 1)) { // Prevents ArrayIndexOutOfBoundsException
                    block = MAP.getTile(getCoordinateX() + 1, getCoordinateY()).getBlockType();
                    if (block instanceof Barricade) {
                        Barricade b = (Barricade) block;
                        if (!b.isUnlocked()) {
                            if (block.getPoints() == inventory.getPoints()) {
                                MAP.replaceTile(getCoordinateX() + 1, getCoordinateY(), new Barricade(0, true));
                                HUD.setNewMotivationMessage();
                            } else {
                                HUD.setNewMessage("That key doesn't fit.");
                            }
                        }
                    }
                }
                break;
        }
    }

    /**
     * The player grabs the key, HUD sets a new message and MAP replaces the
     * tile.
     */
    public void grabKey() {
        BlockType block = MAP.getTile(getCoordinateX(), getCoordinateY()).getBlockType();
        if (block instanceof Key) {
            Key key = (Key) block;
            this.inventory = key;
            this.hasItem = true;
            HUD.setNewMessage("Grabbed a key!");
            HUD.setItem("Key", key.getPoints());
            MAP.replaceTile(getCoordinateX(), getCoordinateY(), new Ground());
        }
    }

    /**
     * Get the position of the player.
     *
     * @return x
     */
    public int getPositionX() {
        return x;
    }

    /**
     * Get the position of the player
     *
     * @return y
     */
    public int getPositionY() {
        return y;
    }

    /**
     * Calculate the x position of the array.
     *
     * @return x
     */
    public int getCoordinateX() {
        return getPositionX() / Game.BLOCK_SIZE;
    }

    /**
     * Calculate the y position of the array.
     *
     * @return y
     */
    public int getCoordinateY() {
        return getPositionY() / Game.BLOCK_SIZE;
    }
    
    public int getLastMove(){
        return this.lastMove;
    }
}
