package assets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ResourceLoader {

    private static BufferedImage image;

    public static void init() {
        loadSpriteSheet();
        loadFont();
    }

    private static void loadSpriteSheet() {
        try {
            image = ImageIO.read(new File("./assets/spritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void loadFont() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font.ttf")));
        } catch (FontFormatException | IOException e) { e.printStackTrace(); }
    }

    public static BufferedImage getSprite(Sprite s) {
        switch (s) {
            default:
                return null;
            case GROUND:
                return image.getSubimage(0, 0, 16, 16);
        }
    }
}
