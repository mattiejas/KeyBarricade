package assets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ResourceLoader {

    private static BufferedImage image;
    private static final Font FONT = new Font("Joystix Monospace", Font.PLAIN, 18);

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
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getSprite(Sprite s) {
        switch (s) {
            default:
                return null;
            case GROUND:
                return getGround();
            case KEY:
                return image.getSubimage(64, 0, 16, 16);
            case WALL:
                return image.getSubimage(80, 0, 16, 16);
            case BARRICADE:
                return image.getSubimage(96, 0, 16, 16);
            case START:
                return image.getSubimage(16, 16, 16, 16);
            case FINISH:
                return image.getSubimage(0, 16, 16, 16);
            case PLAYER_DOWN:
                return image.getSubimage(32, 16, 16, 16);
        }
    }

    public static Font getFont() {
        return FONT;
    }
    
    private static BufferedImage getGround() {
        Random r = new Random();
        int rand = r.nextInt(4);
        
        switch(rand) {
            default: 
            case 0:
                return image.getSubimage(0, 0, 16, 16);
            case 1:
                return image.getSubimage(16, 0, 16, 16);
            case 2:
                return image.getSubimage(32, 0, 16, 16);
            case 3:
                return image.getSubimage(48, 0, 16, 16);
        }
    }
}
