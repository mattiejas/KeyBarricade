package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.Map;

public class PlayState extends GameState {
    
    private Map map;

    public PlayState(GameStateHandler handler) {
        super(handler);
        
        map = new Map();
    }

    @Override
    public void render(Graphics2D g) {
        System.out.println("hoi");
        Font font = new Font("Joystix Monospace", Font.PLAIN, 18);
        g.setFont(font);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 480);
        g.setColor(Color.MAGENTA);
        g.drawString("PLAY", 200, 200);
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
