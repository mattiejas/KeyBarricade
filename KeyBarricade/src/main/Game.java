package main;

import assets.ResourceLoader;
import gamestates.GameStateHandler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    public static final int SCALE = 2;
    public static final int BLOCK_SIZE = 32 * SCALE;
    public static final int WINDOW_WIDTH = BLOCK_SIZE * 15;
    public static final int WINDOW_HEIGHT = BLOCK_SIZE * 10;
    public static final int HORIZONTAL_AMOUNT = WINDOW_WIDTH / BLOCK_SIZE;
    public static final int VERTICAL_AMOUNT = WINDOW_HEIGHT / BLOCK_SIZE;

    private GameStateHandler handler;
    private Graphics2D g;
    private Timer t;

    public Game() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);
        requestFocus();

        this.startGame();
    }
    
    private void startGame() {
        this.init();
        this.addKeyListener(this);
        
        t = new Timer(10, this);
        t.start();
    }    

    private void init() {
        ResourceLoader.init();

        handler = new GameStateHandler();
        handler.init();
    }

    private void render() {
        handler.render(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = (Graphics2D) g;
        this.render();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
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
}
