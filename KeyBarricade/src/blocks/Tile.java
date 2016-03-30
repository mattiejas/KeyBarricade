package blocks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.Player;

public class Tile extends Rectangle {

    private int width, height;
    private int x, y;
    private BlockType block;

    public Tile(int x, int y, int width, int height, BlockType block) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.block = block;
        
        setBounds(x, y, width, height);
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
    
    public boolean getIntersects(Player player) {
        if (block.getSolid()) {
            this.intersects(player);
            return true;
        } else
            return false;
    }
}
