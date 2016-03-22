package blocks;

public class Tile {

    int width, height;
    int x, y;

    public Tile(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Tile(int x, int y, int width, int height, BlockType block) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getPositionX() {
        return this.x;
    }

    public int getPositionY() {
        return this.y;
    }
}
