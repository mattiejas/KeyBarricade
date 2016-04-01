/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import assets.ResourceLoader;
import java.awt.Graphics2D;
import main.Difficulty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ewoud
 */
public class GameStateHandlerTest {
    
    public GameStateHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setState method, of class GameStateHandler.
     */
    @Test
    public void testSetState_int1() {
        ResourceLoader.init();
        System.out.println("setState1");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 0;
        instance.setState(gameState);
        assertTrue(isMenuState(instance.getState()));
    }
    
    @Test
    public void testSetState_int2() {
        ResourceLoader.init();
        System.out.println("setState1");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 1;
        instance.setState(gameState, Difficulty.EASY);
        assertTrue(isPlayState(instance.getState()));
    }
    
    
    @Test
    public void testSetState_int3() {
        ResourceLoader.init();
        System.out.println("setState3");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 2;
        instance.setState(gameState);
        assertTrue(isHelpState(instance.getState()));
    }
    
    @Test
    public void testSetState_int4() {
        ResourceLoader.init();
        System.out.println("setState4");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 3;
        instance.setState(gameState);
        assertTrue(isDifficultyState(instance.getState()));
    }
    
    @Test
    public void testSetPreviousGameState(){
        System.out.println("setPreviousGameState");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 2;
        instance.setState(gameState);
        
        gameState = 0;
        instance.setState(gameState);
        
        gameState = 3;
        instance.setState(gameState);
        
        gameState = 0;
        instance.setState(gameState);
        
        instance.setPreviousGameState();
        
        assertTrue(isMenuState(instance.getState()));
        
    }
    
    @Test
    public void testSetPreviousPlayState(){
        System.out.println("setPreviousPlayState");
        GameStateHandler instance = new GameStateHandler();
        
        int gameState = 1;
        instance.setState(gameState, Difficulty.EASY);
        
        gameState = 0;
        instance.setState(gameState);
        
        gameState = 3;
        instance.setState(gameState);
        
        instance.setPreviousPlayState();
        
        assertTrue(isPlayState(instance.getState()));
        
    }
    
    public boolean isMenuState(Object a){
        return a instanceof MenuState;
    }
    
    public boolean isPlayState(Object b){
        return b instanceof PlayState;
    }
    
    public boolean isHelpState(Object c){
        return c instanceof HelpState;
    }
    
    public boolean isDifficultyState(Object d){
        return d instanceof DifficultyState;
    }

    
    
}
