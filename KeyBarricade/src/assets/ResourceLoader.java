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

    /**
     * This method initializes the images and the font
     * this games uses by calling loadSpiteSheet and LoadFont
     * 
     * loadSprite:
     * This initializes the image the game uses. The image
     * contains a spritesheet.
     * 
     * loadFont:
     * This initializes the font the game uses.
     */
    public static void init() {
        loadSpriteSheet();
        loadFont();
    }

    private static void loadSpriteSheet() {
        try {
            image = ImageIO.read(new File("./assets/sprites.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void loadFont() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns an image of a specific item.
     * The parameter receives a Sprite. With the sprite it determines
     * what part of the spritesheet it has to return.
     * 
     * @param s     give a certain sprite
     * @return      returns a certain part of the spritesheet.
     */
    public static BufferedImage getSprite(Sprite s) {
        switch (s) {
            default:
                return null;
            case GROUND:
                return image.getSubimage(0, 32, 32, 32);
            case KEY:
                return image.getSubimage(64, 0, 32, 32);
            case WALL:
                return image.getSubimage(0, 0, 32, 32);
            case BARRICADE:
                return image.getSubimage(32, 0, 32, 32);
            case BARRICADE_UNLOCKED:
                return image.getSubimage(32, 32, 32, 32);
            case START:
                return image.getSubimage(0, 64, 32, 32);
            case FINISH:
                return image.getSubimage(32, 64, 32, 32);
            case PLAYER_DOWN:
                return image.getSubimage(0, 96, 32, 32);
            case PLAYER_UP:
                return image.getSubimage(32, 96, 32, 32);
            case PLAYER_LEFT:
                return image.getSubimage(64, 96, 32, 32);
            case PLAYER_RIGHT:
                return image.getSubimage(96, 96, 32, 32);
            case PLAYER_ITEM_DOWN:
                return image.getSubimage(0, 128, 32, 32);
            case PLAYER_ITEM_UP:
                return image.getSubimage(32, 128, 32, 32);
            case PLAYER_ITEM_LEFT:
                return image.getSubimage(64, 128, 32, 32);
            case PLAYER_ITEM_RIGHT:
                return image.getSubimage(96, 128, 32, 32);
            case ITEM_KEY:
                return image.getSubimage(64, 32, 16, 16);
        }
    }
}
