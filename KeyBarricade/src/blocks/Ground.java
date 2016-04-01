package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Ground extends BlockType {

    public Ground() {
        this.image = ResourceLoader.getSprite(Sprite.GROUND);
        this.isSolid = false;
    }

    public Ground(boolean start, boolean finish) {
        if (start) {
            this.image = ResourceLoader.getSprite(Sprite.START);
        } else if (finish) {
            this.image = ResourceLoader.getSprite(Sprite.FINISH);
        } else {
            this.image = ResourceLoader.getSprite(Sprite.GROUND);
        }
        this.isSolid = false;

    }
}
