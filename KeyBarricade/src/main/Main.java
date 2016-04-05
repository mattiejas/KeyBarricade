package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * <h1> KeyBarricade </h1>
 * KeyBarricade is a game about unlocking barricades 
 * with keys. Keys and barricades could have different points.
 * That means that you cannot always unlock a barricade with the key
 * you are currently holding.
 * 
 * @author Matthias Aarnoutse
 * @author Jeffrey Dufour
 * @author Ewoud Vermeij
 * 
 * @version 1.0
 */

public class Main {
    
    private static BufferedImage icon;
    private static JFrame frame;
    
    public static void main(String[] args) throws IOException {
        icon = ImageIO.read(new File("./assets/icon.png"));
        frame = new JFrame();

        frame.setTitle("KeyBarricade: The Game");
        frame.setResizable(true);
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Game());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
