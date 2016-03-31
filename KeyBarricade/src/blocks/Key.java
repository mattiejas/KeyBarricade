package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Key extends BlockType {

    public Key(int points) {
        this.image = ResourceLoader.getSprite(Sprite.KEY);
        this.points = points;
        this.isSolid = false;
    }

}
