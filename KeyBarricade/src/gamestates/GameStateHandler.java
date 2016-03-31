package gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import main.Difficulty;

public class GameStateHandler {

    private GameState currentState;
    private GameState previousGameState;
    private GameState previousPlayState;
    private ArrayList<GameState> states;

    public GameStateHandler() {
        states = new ArrayList<>();
        states.add(new MenuState(this));
        states.add(new PlayState(this));
        states.add(new HelpState(this));
        states.add(new DifficultyState(this));
        currentState = states.get(0);
    }

    public void init() {
        currentState.init();
    }

    public void render(Graphics2D g) {
        currentState.render(g);
    }

    public void setState(int gameState) {
        previousGameState = currentState;
        
        if(currentState instanceof PlayState){
            previousPlayState = currentState;
        }
        
        currentState = states.get(gameState);
        this.init();
    }
    
    public void setState(int gameState, Difficulty d){
        currentState = states.get(gameState);
        PlayState state = (PlayState)currentState;
        state.setDifficulty(d);
        this.init();
    }
    
    public void setPreviousGameState(){
        currentState = previousGameState;
    }
    
    public void setPreviousPlayState(){
        currentState = previousPlayState;
    }
    
    public GameState getState() {
        return currentState;
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
