package blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.Game;

public class Tile {

    private final int WIDTH, HEIGHT;
    private final int X, Y;
    private final BlockType BLOCK;
    private final int MARGIN;

    public Tile(int x, int y, int width, int height, BlockType block) {
        this.X = x;
        this.Y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BLOCK = block;
        this.MARGIN = 2;
    }

    public void render(Graphics2D g) {
        g.drawImage(BLOCK.getSprite(), X, Y, WIDTH, HEIGHT, null);
        
        if (BLOCK.getPoints() > 0) {
            Font font = new Font("Joystix Monospace", Font.PLAIN, 12);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(BLOCK.getPoints() + "", X + MARGIN, Y + Game.BLOCK_SIZE - MARGIN);
        }
    }

    public int getPositionX() {
        return this.X;
    }

    public int getPositionY() {
        return this.Y;
    }

    public BlockType getBlockType() {
        return BLOCK;
    }

    public boolean getSolid() {
        return BLOCK.getSolid();
    }
}
