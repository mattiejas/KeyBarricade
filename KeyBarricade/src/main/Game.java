package main;

import assets.ResourceLoader;
import gamestates.GameState;
import gamestates.MenuState;
import gamestates.PlayState;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

    private final Dimension WINDOW_SIZE;
    private final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 480;

    private GameState currentState;
    private ArrayList<GameState> states;

    private Graphics2D g;
    private BufferedImage img;

    public Game() {
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(WINDOW_SIZE);

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    private void init() {
        ResourceLoader.init();

        states = new ArrayList<>();
        states.add(new MenuState());
        states.add(new PlayState());
        currentState = states.get(0);

        //img = new BufferedImage(0, 0, 0, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) img.getGraphics();
    }

    private void render() {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
