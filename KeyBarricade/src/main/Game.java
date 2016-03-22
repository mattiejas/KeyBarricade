package main;

import assets.ResourceLoader;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Game extends JPanel {
    
    private final Dimension WINDOW_SIZE;
    
    public Game() {
        WINDOW_SIZE = new Dimension(800, 480);
        
        requestFocus();
        setPreferredSize(WINDOW_SIZE);
        
        ResourceLoader.init();
    }

}
