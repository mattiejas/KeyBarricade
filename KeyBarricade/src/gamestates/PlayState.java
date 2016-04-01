package gamestates;

import assets.ResourceLoader;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.Game;
import main.Map;

public class PlayState extends GameState {

    private Map map;
private Difficulty diff;

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
	map = new Map(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, diff);
        map.init();
    }

    public void setDifficulty(Difficulty d){
        this.diff = d;
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
