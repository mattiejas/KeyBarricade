package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MenuState extends GameState {

    public MenuState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics2D g) {
        Font font = new Font("Joystix Monospace", Font.PLAIN, 18);
        g.setFont(font);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 480);
        g.setColor(Color.WHITE);
        g.drawString("hoi", 200, 200);
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void keyPressed(int k) {
        
    }

    @Override
    public void keyReleased(int k) {
        
    }

    @Override
    public void keyTyped(int k) {
        
    }

}
