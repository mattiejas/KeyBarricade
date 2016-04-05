package map;

import assets.ResourceLoader;
import blocks.BlockType;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.HUD;
import main.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {

    Map map;
    HUD hud;
    Player player;

    public MapTest() {
        ResourceLoader.init();

        map = new Map(Difficulty.EASY, new HUD());
        player = new Player(map, new HUD());
        hud = new HUD();

        hud.init();
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
     * Test of playerAllowedToMoveUp method, of class Map.
     */
    @Test
    public void testPlayerAllowedToMoveUp() {
        System.out.println("Testing playerAllowedToMoveUp() ..");
        System.out.println("Player position: " + player.getArrayX() + ", " + player.getArrayY());
        System.out.println("Expected position: " + player.getArrayX() + ", " + (player.getArrayY() - 1));

        // Player is at (0, 0), can't move up
        boolean expResult = false;
        boolean result = map.playerAllowedToMoveUp();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPlayerAllowedToMoveUpCopy() {
        System.out.println("Testing playerAllowedToMoveUp() for the second time ..");
        
        player.keyPressed(KeyEvent.VK_S);
        player.keyPressed(KeyEvent.VK_S);
        player.keyPressed(KeyEvent.VK_S);
        
        System.out.println("Player position: " + player.getArrayX() + ", " + player.getArrayY());
        System.out.println("Expected position: " + 0 + ", " + 3);
        
        // Player is at (0, 3), can move up
        boolean expResult = true;
        boolean result = map.playerAllowedToMoveUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of playerAllowedToMoveDown method, of class Map.
     */
//    @Test
//    public void testPlayerAllowedToMoveDown() {
//        System.out.println("playerAllowedToMoveDown");
//        Map instance = null;
//        boolean expResult = false;
//        boolean result = instance.playerAllowedToMoveDown();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerAllowedToMoveLeft method, of class Map.
//     */
//    @Test
//    public void testPlayerAllowedToMoveLeft() {
//        System.out.println("playerAllowedToMoveLeft");
//        Map instance = null;
//        boolean expResult = false;
//        boolean result = instance.playerAllowedToMoveLeft();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerAllowedToMoveRight method, of class Map.
//     */
//    @Test
//    public void testPlayerAllowedToMoveRight() {
//        System.out.println("playerAllowedToMoveRight");
//        Map instance = null;
//        boolean expResult = false;
//        boolean result = instance.playerAllowedToMoveRight();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of replaceTile method, of class Map.
//     */
//    @Test
//    public void testReplaceTile() {
//        System.out.println("replaceTile");
//        int x = 0;
//        int y = 0;
//        BlockType block = null;
//        Map instance = null;
//        instance.replaceTile(x, y, block);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
