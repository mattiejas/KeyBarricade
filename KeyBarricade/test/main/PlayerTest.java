/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import map.Difficulty;
import assets.ResourceLoader;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics2D;
import map.Map;
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
public class PlayerTest {

    Map map;
    HUD hud;

    public PlayerTest() {
        ResourceLoader.init();
        map = new Map(Difficulty.EASY, hud);
        hud = new HUD();

        map.init();

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
     * Test of keyPressed method, of class Player.
     */
    @Test
    public void testKeyPressedRight() {
        System.out.println("keyPressedRight");
        Player instance = new Player(map, hud);

        int k = KeyEvent.VK_RIGHT;
        
        if (!map.getTile(1, 0).getSolid()) {
            instance.keyPressed(k);
            int actualsX = instance.getPositionX();
            int actualsY = instance.getPositionY();
            int expectedsX = 1;
            int expectedsY = 0;
            
            assertEquals(expectedsX, actualsX);
            assertEquals(expectedsY, actualsY);
        }
        else{
            int actualsX = instance.getPositionX();
            int actualsY = instance.getPositionY();
            int expectedsX = 0;
            int expectedsY = 0;
            
            assertEquals(expectedsX, actualsX);
            assertEquals(expectedsY, actualsY);
        }
    }
    
    /**
     * Test of keyPressed method, of class Player.
     */
    @Test
    public void testKeyPressedLeft() {
        System.out.println("keyPressedLeft");
        Player instance = new Player(map, hud);

        int k = KeyEvent.VK_RIGHT;
        
        if (!map.getTile(1, 0).getSolid()) {
            instance.keyPressed(k);
            int actualsX = instance.getPositionX();
            int actualsY = instance.getPositionY();
            int expectedsX = 1;
            int expectedsY = 0;
            
            assertEquals(expectedsX, actualsX);
            assertEquals(expectedsY, actualsY);
        }
        else{
            int actualsX = instance.getPositionX();
            int actualsY = instance.getPositionY();
            int expectedsX = 0;
            int expectedsY = 0;
            
            assertEquals(expectedsX, actualsX);
            assertEquals(expectedsY, actualsY);
        }
    }

    /**
     * Test of useKey method, of class Player.
     */
    @Test
    public void testUseKey() {
        System.out.println("useKey");
        Player instance = null;

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of grabKey method, of class Player.
     */
    @Test
    public void testGrabKey() {
        System.out.println("grabKey");
        Player instance = null;

        // TODO review the generated test code and remove the default call to fail.
    }

}
