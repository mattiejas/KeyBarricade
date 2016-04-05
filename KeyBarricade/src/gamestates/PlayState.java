package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import map.Difficulty;
import main.HUD;
import map.Map;

public class PlayState extends GameState {
    private Map map;
    private HUD hud;
    private Difficulty diff;

    public PlayState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {
        hud = new HUD();
        map = new Map(diff, hud);
        map.init();
        hud.init();
    }

    @Override
    public void render(Graphics2D g) {
        map.render(g);
        hud.render(g);
    }

    public void setDifficulty(Difficulty d) {
        this.diff = d;
    }
    
    public void restart() {
        map.reloadLevel();
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            handler.setState(MENU_STATE);
        } else {
            map.keyPressed(k);
            hud.keyPressed(k);

            if (hud.isReady()) {
                MenuState ms = handler.getMenuState();
                ms.setFirstStart(true);
                handler.setState(MENU_STATE);
            }

        }
    }
}
