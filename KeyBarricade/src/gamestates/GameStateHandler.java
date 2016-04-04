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

    /**
     * Initializes the current state.
     */
    public void init() {
        currentState.init();
    }

    /**
     * Renders the current state.
     * @param g     draws by the given instructions.
     */
    public void render(Graphics2D g) {
        currentState.render(g);
    }

    /**
     * Sets the specified game state and keep track of the previous game state.
     * 
     * @param gameState 
     */
    public void setState(int gameState) {
        previousGameState = currentState;
        
        if (currentState instanceof PlayState) {
            previousPlayState = currentState;
        } 

        currentState = STATES[gameState];
        this.init();
    }

    /**
     * Set and initialize the current play state, including the difficulty option.
     * 
     * @param d     the difficulty of the play state
     */
    public void setPlayState(Difficulty d) {
        currentState = STATES[GameState.PLAY_STATE];
        ((PlayState) currentState).setDifficulty(d);
        this.init();
    }
    
    /**
     * Restart the current play state.
     */
    public void restartPlayState() {
        currentState = previousPlayState;
        ((PlayState) currentState).restart();
    }

    /**
     * Set the previous game state as the current state.
     */
    public void setPreviousGameState() {
        currentState = previousGameState;
    }

    /**
     * Set the previous play state as the current state.
     */    
    public void setPreviousPlayState() {
        currentState = previousPlayState;
    }

    /**
     * Get the current state.
     * @return  current state
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * Get the menu state
     * @return  menu state
     */
    public MenuState getMenuState() {
        return (MenuState) STATES[GameState.MENU_STATE];
    }

    /**
     * Detect which key is pressed and pass it down to the current state.
     * @param k the pressed keyCode in Game class.
     */
    public void keyPressed(int k) {
        currentState.keyPressed(k);
    }
}
