package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Ground extends BlockType {

    private final boolean IS_START, IS_FINISH;

    public Ground() {
        this.image = ResourceLoader.getSprite(Sprite.GROUND);
        this.isSolid = false;
        this.IS_START = false;
        this.IS_FINISH = false;
    }

    public Ground(boolean start, boolean finish) {
        if (start) {
            this.image = ResourceLoader.getSprite(Sprite.START);
        } else if (finish) {
            this.image = ResourceLoader.getSprite(Sprite.FINISH);
        }
        this.isSolid = false;
        this.IS_START = start;
        this.IS_FINISH = finish;
    }

}
