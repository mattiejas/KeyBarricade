/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import assets.ResourceLoader;
import blocks.BlockType;
import blocks.Tile;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;
import main.HUD;
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
public class PlayerMovementTest {
    
    private HUD hud;
    
    public PlayerMovementTest() {
        hud = new HUD();
        ResourceLoader.init();
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
     * Test of the players move down
     */
    @Test
    public void testPlayerMoveDown() {
        System.out.println("Player move down");
        Map instance = new Map(Difficulty.TEST, hud);
        instance.init();
        
        int k = KeyEvent.VK_DOWN;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        int actualX = instance.getPlayer().getCoordinateX();
        int actualY = instance.getPlayer().getCoordinateY();
        
        int expectedX = 0;
        int expectedY = 2;
        
        assertEquals(actualX, expectedX);
        assertEquals(expectedY, actualY);
    }
    
    /**
     * Test of the players move up
     */
    @Test
    public void testPlayerMoveUp() {
        System.out.println("Player move up");
        Map instance = new Map(Difficulty.TEST, hud);
        instance.init();
        
        int k = KeyEvent.VK_DOWN;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_UP;
        
        instance.getPlayer().keyPressed(k);
        
        int actualX = instance.getPlayer().getCoordinateX();
        int actualY = instance.getPlayer().getCoordinateY();
        
        int expectedX = 0;
        int expectedY = 1;
        
        assertEquals(actualX, expectedX);
        assertEquals(expectedY, actualY);
    }
    
    /**
     * Test of the players move right
     */
    @Test
    public void testPlayerMoveRight() {
        System.out.println("Player move right");
        Map instance = new Map(Difficulty.TEST, hud);
        instance.init();
        
        int k = KeyEvent.VK_RIGHT;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        int actualX = instance.getPlayer().getCoordinateX();
        int actualY = instance.getPlayer().getCoordinateY();
        
        int expectedX = 3;
        int expectedY = 0;
        
        assertEquals(actualX, expectedX);
        assertEquals(expectedY, actualY);
    }

    /**
     * Test of the players move left
     */
    @Test
    public void testPlayerMoveLeft() {
        System.out.println("Player move left");
        Map instance = new Map(Difficulty.TEST, hud);
        instance.init();
        
        int k = KeyEvent.VK_RIGHT;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_LEFT;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        int actualX = instance.getPlayer().getCoordinateX();
        int actualY = instance.getPlayer().getCoordinateY();
        
        int expectedX = 2;
        int expectedY = 0;
        
        assertEquals(actualX, expectedX);
        assertEquals(expectedY, actualY);
    }
    
     /**
     * Test of teh players multiple movements
     */
    @Test
    public void testPlayerMoveMultipleDirections() {
        System.out.println("Player moves multiple directions");
        Map instance = new Map(Difficulty.TEST, hud);
        instance.init();
        
        int k = KeyEvent.VK_RIGHT;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_DOWN;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_LEFT;
        
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_UP;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        k = KeyEvent.VK_RIGHT;
        
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        instance.getPlayer().keyPressed(k);
        
        
        int actualX = instance.getPlayer().getCoordinateX();
        int actualY = instance.getPlayer().getCoordinateY();
        
        int expectedX = 8;
        int expectedY = 3;
        
        assertEquals(actualX, expectedX);
        assertEquals(expectedY, actualY);
    }
    
}
