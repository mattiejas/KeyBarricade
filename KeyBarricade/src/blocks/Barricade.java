package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Barricade extends BlockType {

    public Barricade(int points) {
        this.isSolid = true;
        this.image = ResourceLoader.getSprite(Sprite.BARRICADE);
        this.points = points;
    }

    public Barricade(int points, boolean unlocked) {
        if (unlocked) {
            this.isSolid = false;
            this.image = ResourceLoader.getSprite(Sprite.BARRICADE_UNLOCKED);
        } else {
            this.isSolid = true;
            this.image = ResourceLoader.getSprite(Sprite.BARRICADE);
        }
        this.points = points;
    }

    public boolean isUnlocked() {
        return !isSolid;
    }
}
