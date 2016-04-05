package map;

import blocks.BlockType;
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
        map = new Map(Difficulty.EASY, new HUD());
        player = new Player(map, new HUD());
        hud = new HUD();
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
        boolean expResult = false;
        System.out.println("Player position: " + player.getArrayX() + ", " + player.getArrayY());
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
