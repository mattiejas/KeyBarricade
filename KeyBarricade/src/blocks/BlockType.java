package blocks;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class BlockType {

    protected boolean isSolid;
    protected BufferedImage image;
    
    protected int points;

    public void setSprite(Sprite s) {
        this.image = ResourceLoader.getSprite(s);
    }

    public BufferedImage getSprite() {
        return this.image;
    }

    public boolean getSolid() {
        return isSolid;
    }

    public int getPoints() {
        return points;
    }
}
