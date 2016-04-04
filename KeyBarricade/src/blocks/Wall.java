package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public final class Wall extends BlockType {

    /**
     * This constructor of Wall sets the isSolid boolean
     * to true. Also the image for Wall wil be set.
     */
    public Wall() {
        this.isSolid = true;
        this.image = ResourceLoader.getSprite(Sprite.WALL);
    }
}
