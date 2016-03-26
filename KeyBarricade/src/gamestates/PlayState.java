package gamestates;

import java.awt.Font;
import java.awt.Graphics2D;
import main.Map;

public class PlayState extends GameState {
    
    private Map map;

    public PlayState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics2D g) {
        Font font = new Font("Joystix Monospace", Font.PLAIN, 18);
        g.setFont(font);
        
        map.render(g);
    }

    @Override
    public void init() {
        //map = new Map(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        map = new Map(320, 320);
        map.init();
    }

    @Override
    public void update() {
        map.update();
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
