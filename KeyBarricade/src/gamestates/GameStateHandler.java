package gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateHandler {

    private GameState currentState;
    private ArrayList<GameState> states;

    public GameStateHandler() {
        states = new ArrayList<>();
        states.add(new MenuState(this));
        states.add(new PlayState(this));
        currentState = states.get(0);
    }

    public void init() {
        currentState.init();
    }
    
    public void render(Graphics2D g) {
        currentState.render(g);
    }

    public void setState(int gameState) {
        currentState = states.get(gameState);
        this.init();
    }

    public GameState getState() {
        return currentState;
    }
    
    public void keyPressed(int k) {
        currentState.keyPressed(k);
    }
    public void keyReleased(int k) {
        currentState.keyReleased(k);
    }
    public void keyTyped(int k) {
        currentState.keyTyped(k);
    }
}
