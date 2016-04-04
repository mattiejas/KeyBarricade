package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

    protected GameStateHandler handler;

    //These are the numbers of the location of the states
    //in the array of the GameStateHandler.
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

}
