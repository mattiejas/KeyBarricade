package blocks;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.image.BufferedImage;

public abstract class BlockType {
    protected boolean isSolid;
    protected BufferedImage image;
    
    public void setSprite(Sprite s) {
        this.image = ResourceLoader.getSprite(s);
    }
}
