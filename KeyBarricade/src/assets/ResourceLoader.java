package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ResourceLoader {
    
    private static BufferedImage image;
    
    public static void init() {
        try {
            image  = ImageIO.read(new File("./assets/spritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static BufferedImage getSprite(Sprite s) {
        switch (s) {
            default:
                return null;
            case WALL:
                return image.getSubimage(0, 0, 16, 16);
        }
    }
}
