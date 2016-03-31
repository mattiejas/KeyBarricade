package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
    
    private static BufferedImage icon;
    
    public static void main(String[] args) throws IOException {
        icon = ImageIO.read(new File("./assets/icon.png"));
        
        JFrame frame = new JFrame();

        frame.setTitle("KeyBarricade");
        frame.setResizable(false);
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Game());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
