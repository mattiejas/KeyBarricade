package blocks;

import java.awt.Graphics2D;

public class Tile {

    private int width, height;
    private int x, y;
    private BlockType block;

    public Tile(int x, int y, int width, int height, BlockType block) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.block = block;
    }

    public void render(Graphics2D g) {
        g.drawImage(block.getSprite(), x, y, width, height, null);
    }

    public int getPositionX() {
        return this.x;
    }

    public int getPositionY() {
        return this.y;
    }

    public BlockType getBlockType() {
        return block;
    }
    
    public boolean getSolid() {
        return block.getSolid();
    }
}
