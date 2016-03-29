package gamestates;

import assets.ResourceLoader;
import java.awt.Font;
import java.awt.Graphics2D;
import main.Game;
import main.Map;
import main.Player;

public class PlayState extends GameState {

    private Map map;

    public PlayState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(ResourceLoader.getFont());
        map.render(g);
    }

    @Override
    public void init() {
        map = new Map(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        map.init();
    }

    @Override
    public void keyPressed(int k) {
        map.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {}
    @Override
    public void keyTyped(int k) {}

}
