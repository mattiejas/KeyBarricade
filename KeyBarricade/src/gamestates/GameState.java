package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

    protected GameStateHandler handler;
    
    protected final int MENUSTATE = 0;
    protected final int PLAYSTATE = 1;
    
    public GameState(GameStateHandler handler) {
        this.handler = handler;
        init();
    }
    
    public abstract void init();
    public abstract void render(Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void keyTyped(int k);    
}
