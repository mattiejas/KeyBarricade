package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Key extends BlockType {

    /**
     * This constructor receives an int as a parameter
     * and sets the amount of points for this key.
     * 
     * @param points        sets the amount of points for this key
     */
    public Key(int points) {
        this.image = ResourceLoader.getSprite(Sprite.KEY);
        this.points = points;
        this.isSolid = false;
    }

}
