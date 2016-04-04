package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.HUD;
import map.Map;

public class PlayState extends GameState {

    private Map map;
    private HUD hud;
    private Difficulty diff;

    private boolean restart;

    public PlayState(GameStateHandler handler) {
        super(handler);
        this.restart = false;
    }
    
    public PlayState(GameStateHandler handler, boolean restart) {
        super(handler);
        this.restart = restart;
    }

    @Override
    public void init() {
        hud = new HUD();
        map = new Map(diff, hud);

        if (!restart) {
            map.init();
            hud.init();
        }
    }

    @Override
    public void render(Graphics2D g) {
        map.render(g);
        hud.render(g);
    }

    public void setDifficulty(Difficulty d) {
        this.diff = d;
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

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void keyTyped(int k) {
    }

}
