package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Barricade extends BlockType {
    private final int POINTS;
    
    public Barricade(int points) {
        this.isSolid = true;
        this.image = ResourceLoader.getSprite(Sprite.BARRICADE);
        this.POINTS = points;
    }
    
    public void unlock() {
        this.isSolid = false;
        this.setSprite(Sprite.BARRICADE_UNLOCKED);
    }
    
    public int getPoints() {
        return POINTS;
    }

}
