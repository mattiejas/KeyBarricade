package main;

import assets.ResourceLoader;
import gamestates.GameStateHandler;
import java.awt.Color;
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

    public static final int BLOCKSIZE = 16;
    public static final int SCALE = 4;
    public static final int WINDOW_WIDTH = 10 * BLOCKSIZE * SCALE, WINDOW_HEIGHT = 10 * BLOCKSIZE * SCALE;

    private GameStateHandler handler;

    private Graphics2D g;
    private Timer t;

    public Game() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);
        requestFocus();

        this.startGame();
    }

    private void init() {
        ResourceLoader.init();

        handler = new GameStateHandler();
        handler.init();
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
        this.render(this.g);
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
        repaint();
    }

    private void startGame() {
        this.init();
        this.addKeyListener(this);
        

        t = new Timer(100, this);
        t.start();
    }
}
