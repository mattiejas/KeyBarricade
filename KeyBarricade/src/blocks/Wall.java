package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public final class Wall extends BlockType {

    public Wall() {
        this.isSolid = true;
        this.image = ResourceLoader.getSprite(Sprite.WALL);
    }
}
