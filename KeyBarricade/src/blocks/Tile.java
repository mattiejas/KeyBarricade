package blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.Game;

public class Tile {

    private final int WIDTH, HEIGHT;
    private final int X, Y;
    private final BlockType BLOCK;

    /**
     * This constructor receives multiple ints and one 
     * BlockType as parameters. This sets the location, 
     * size and type of BlockType.
     * 
     * @param x         Sets the location x
     * @param y         Sets the location y
     * @param width     Sets the width of the tile
     * @param height    Sets the height of the tile
     * @param block     Sets the BlockType of the tile
     */
    public Tile(int x, int y, int width, int height, BlockType block) {
        this.X = x;
        this.Y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BLOCK = block;
    }

    /**
     * This method draws the Tile on the screen. It 
     * receives a Graphics2D parameter to draw.
     * 
     * @param g     draws by the given instructions.
     */
    public void render(Graphics2D g) {
        g.drawImage(BLOCK.getSprite(), X, Y, WIDTH, HEIGHT, null);
        
        if (BLOCK.getPoints() > 0) {
            Font font = new Font("Joystix Monospace", Font.PLAIN, 12);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(BLOCK.getPoints() + "", X + 2, Y + Game.BLOCK_SIZE - 2);
        }
    }

    /**
     * This method returns the value of x of this Tile.
     * x is a certain location on the Map.
     * 
     * @return      returns value of X
     */
    public int getPositionX() {
        return this.X;
    }

    /**
     * This method returns the value of y of this Tile.
     * y is a certain location on the Map.
     * 
     * @return      returns value of Y
     */
    public int getPositionY() {
        return this.Y;
    }
    
    /**
     * This method returns the value of the BLOCK.
     * 
     * @return      returns value of BLOCK. 
     */
    public BlockType getBlockType() {
        return BLOCK;
    }

    /**
     * This method tells you whether the Block of this
     * Tile is solid or not.
     * 
     * @return      returns the solid state of BLOCK 
     */
    public boolean getSolid() {
        return BLOCK.getSolid();
    }
}
