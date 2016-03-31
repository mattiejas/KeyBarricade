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
        this.isSolid = false;
        if (unlocked) {
            this.image = ResourceLoader.getSprite(Sprite.BARRICADE_UNLOCKED);
        } else {
            this.image = ResourceLoader.getSprite(Sprite.BARRICADE);
        }
        this.points = points;
    }

    public void unlock() {
        this.isSolid = false;
        this.setSprite(Sprite.BARRICADE_UNLOCKED);
    }
}
