package main;

import assets.ResourceLoader;
import gamestates.GameState;
import gamestates.GameStateHandler;
import gamestates.MenuState;
import gamestates.PlayState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    private final Dimension WINDOW_SIZE;
    private final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 480;

    private GameStateHandler handler;
    
    private Graphics2D g;
    private Timer t;
    
    public Game() {
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(WINDOW_SIZE);

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        
        startGame();
    }

    private void init() {
        ResourceLoader.init();
        
        handler = new GameStateHandler();
        handler.init();
    }
    
    private void update() {
        handler.update();
    }

    private void render(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, 800, 480);
        
        handler.render(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = (Graphics2D) g;
        render(this.g);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        handler.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handler.keyReleased(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        handler.keyTyped(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        this.paintComponent(g);
    }

    private void startGame() {
        init();
        
        t = new Timer(1000, this);
        t.start();
    }
}
