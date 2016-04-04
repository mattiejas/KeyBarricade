package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Barricade extends BlockType {

    /**
     * This Barricade receives 2 parameters. One to set the points
     * and one to make it solid or not.
     * 
     * @param points        set the points for the Barricade
     * @param unlocked      make a Barricade solid or not
     */
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

    /**
     * This method tells you whether the Barricade
     * is solid or not
     * 
     * @return      returns the value of isSolid
     */
    public boolean isUnlocked() {
        return !isSolid;
    }
}
