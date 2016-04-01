package gamestates;

import java.awt.Graphics2D;
import main.Difficulty;

public class GameStateHandler {

    private GameState currentState;
    private GameState previousGameState;
    private GameState previousPlayState;
    private final GameState[] STATES;

    public GameStateHandler() {
        STATES = new GameState[4];
        STATES[0] = new MenuState(this);
        STATES[1] = new PlayState(this);
        STATES[2] = new HelpState(this);
        STATES[3] = new DifficultyState(this);
        currentState = STATES[GameState.MENU_STATE];

    }

    public void init() {
        currentState.init();
    }

    public void render(Graphics2D g) {
        currentState.render(g);
    }

    public void setState(int gameState) {
        previousGameState = currentState;
        
        if (currentState instanceof PlayState) {
            previousPlayState = currentState;
        } 

        currentState = STATES[gameState];
        this.init();
    }

    public void setPlayState(Difficulty d) {
        currentState = STATES[GameState.PLAY_STATE];
        ((PlayState) currentState).setDifficulty(d);
        this.init();
    }

    public void setPreviousGameState() {
        currentState = previousGameState;
    }

    public void setPreviousPlayState() {
        currentState = previousPlayState;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public MenuState getMenuState() {
        return (MenuState) STATES[GameState.MENU_STATE];
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
