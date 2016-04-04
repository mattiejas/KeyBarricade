package blocks;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.image.BufferedImage;

public abstract class BlockType {

    protected boolean isSolid;
    protected BufferedImage image;
    
    protected int points;

    /**
     * This method sets the Sprite of this BlockType.
     * 
     * @param s         sets the sprite the BlockType has to use
     */
    public void setSprite(Sprite s) {
        this.image = ResourceLoader.getSprite(s);
    }

    /**
     * This method returns the image this BlockType uses
     * 
     * @return      returns the image of this BlockType
     */
    public BufferedImage getSprite() {
        return this.image;
    }

    /**
     * This method returns whether this BlockType
     * is solid or not
     * 
     * @return      returns the value of isSolid 
     */
    public boolean getSolid() {
        return isSolid;
    }

    /**
     * This method returns the amount of points of
     * this BlockType if the BlockType has any points
     * 
     * @return      returns the amount of points 
     */
    public int getPoints() {
        return points;
    }
}
