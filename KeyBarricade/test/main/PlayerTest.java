/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics2D;
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
    
    public PlayerTest() {
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
     * Test of render method, of class Player.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Graphics2D g = null;
        Player instance = null;
        instance.render(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class Player.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        int k = 0;
        Player instance = null;
        instance.keyPressed(k);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useKey method, of class Player.
     */
    @Test
    public void testUseKey() {
        System.out.println("useKey");
        Player instance = null;
        instance.useKey();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of grabKey method, of class Player.
     */
    @Test
    public void testGrabKey() {
        System.out.println("grabKey");
        Player instance = null;
        instance.grabKey();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositionX method, of class Player.
     */
    @Test
    public void testGetPositionX() {
        System.out.println("getPositionX");
        Player instance = null;
        int expResult = 0;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositionY method, of class Player.
     */
    @Test
    public void testGetPositionY() {
        System.out.println("getPositionY");
        Player instance = null;
        int expResult = 0;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrayX method, of class Player.
     */
    @Test
    public void testGetArrayX() {
        System.out.println("getArrayX");
        Player instance = null;
        int expResult = 0;
        int result = instance.getArrayX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrayY method, of class Player.
     */
    @Test
    public void testGetArrayY() {
        System.out.println("getArrayY");
        Player instance = null;
        int expResult = 0;
        int result = instance.getArrayY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
