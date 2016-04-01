package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

    protected GameStateHandler handler;

    protected static final int MENU_STATE = 0;
    protected static final int PLAY_STATE = 1;
    protected static final int HELP_STATE = 2;
    protected static final int DIFFICULTY_STATE = 3;

    public GameState(GameStateHandler handler) {
        this.handler = handler;
    }

    public abstract void init();

    public abstract void render(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

    public abstract void keyTyped(int k);
}
