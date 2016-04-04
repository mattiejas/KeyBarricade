package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Ground extends BlockType {

    /**
     * This constructor of Ground sets the sprite to ground
     * and sets the isSolid 
     */
    public Ground() {
        this.image = ResourceLoader.getSprite(Sprite.GROUND);
        this.isSolid = false;
    }

    /**
     * This constructor receives 2 booleans as parameters.
     * One to set the ground as the start of the player. One
     * to set the finish for the player.
     * 
     * @param start     sets the ground as start
     * @param finish    sets the ground as finish
     */
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
