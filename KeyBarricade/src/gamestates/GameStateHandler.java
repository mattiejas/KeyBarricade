package gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateHandler {

    private GameState currentState;
    private ArrayList<GameState> previousStates;
    private ArrayList<GameState> states;

    public GameStateHandler() {
        states = new ArrayList<>();
        states.add(new MenuState(this));
        states.add(new PlayState(this));
        states.add(new HelpState(this));
        currentState = states.get(0);

        previousStates = new ArrayList<>();
    }

    public void init() {
        currentState.init();
    }

    public void render(Graphics2D g) {
        currentState.render(g);
    }

    public void setState(int gameState) {
        previousStates.add(currentState);
        currentState = states.get(gameState);
        this.init();
    }

    public void setPreviousState() {
        currentState = previousStates.get(previousStates.size() - 1);
    }

    public void setPreviousState(int previous) {
        currentState = previousStates.get(previousStates.size() - (previous + 1));
    }

    public GameState getPreviousState() {
        return previousStates.get(previousStates.size() - 1);
    }

    public GameState getPreviousState(int previous) {
        return previousStates.get(previousStates.size() - (previous + 1));
    }

    public GameState getState() {
        return currentState;
    }

    public int getTotalPreviousStates() {
        return previousStates.size();
    }

    public void keyPressed(int k) {
        currentState.keyPressed(k);
    }

    public void keyTyped(int k) {
        currentState.keyTyped(k);
    }

    public void keyReleased(int k) {
        currentState.keyReleased(k);
    }
}
