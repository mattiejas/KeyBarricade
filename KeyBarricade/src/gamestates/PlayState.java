package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.HUD;
import main.Map;

public class PlayState extends GameState {
    private Map map;
    private HUD hud;
    private Difficulty diff;
    boolean restart;

    public PlayState(GameStateHandler handler, boolean restart) {
        super(handler);
        this.restart = restart;
    }

    @Override
    public void init() {
        map = new Map(diff, hud);  
        if (restart) {
            map.restart();
        } else {   
            map.init();
        }
        hud = new HUD(); 
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
