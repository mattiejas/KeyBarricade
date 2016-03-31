package gamestates;

import assets.ResourceLoader;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.Game;
import main.Map;

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
        if (handler.getPreviousState() instanceof MenuState) {
            MenuState ms = (MenuState) handler.getPreviousState();
            map = new Map(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, ms.getDifficulty());
            map.init();
        } else {
            map = new Map(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, Difficulty.EASY);
            map.init();
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            handler.setState(MENUSTATE);
        } else {
            map.keyPressed(k);
        }
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void keyTyped(int k) {
    }

}
