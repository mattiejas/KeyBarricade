package blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.Game;

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
        
        if (block.getPoints() > 0) {
            Font font = new Font("Joystix Monospace", Font.PLAIN, 12);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(block.getPoints() + "", x + 2, y + Game.BLOCKSIZE - 2);
        }
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
