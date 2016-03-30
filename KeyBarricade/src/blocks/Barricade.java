package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Barricade extends BlockType {
    
    public Barricade(int points) {
        this.isSolid = true;
        this.image = ResourceLoader.getSprite(Sprite.BARRICADE);
        this.points = points;
    }
    
    public void unlock() {
        this.isSolid = false;
        this.setSprite(Sprite.BARRICADE_UNLOCKED);
    }
}
