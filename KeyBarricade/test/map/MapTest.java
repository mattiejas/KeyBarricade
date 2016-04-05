package map;

import assets.ResourceLoader;
import java.awt.event.KeyEvent;
import main.Difficulty;
import main.Game;
import main.HUD;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {

    Map map;
    HUD hud;

    public MapTest() {
        ResourceLoader.init();

        hud = new HUD();
        map = new Map(Difficulty.TEST, new HUD());

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

        // Player is at (0, 0), not allowed to move up
        boolean expResult = false;
        boolean result = map.playerAllowedToMoveUp();
        assertEquals(expResult, result);
    }

    @Test
    public void testPlayerAllowedToMoveUpCopy() {
        System.out.println("Testing playerAllowedToMoveUp() for the second time ..");

        map.keyPressed(KeyEvent.VK_DOWN);
        map.keyPressed(KeyEvent.VK_DOWN);
        map.keyPressed(KeyEvent.VK_DOWN);

        // Player is at (0, 3), allowed to move up
        boolean expResult = true;
        boolean result = map.playerAllowedToMoveUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of playerAllowedToMoveDown method, of class Map.
     */
    @Test
    public void testPlayerAllowedToMoveDown() {
        System.out.println("Testing playerAllowedToMoveDown() ..");

        // Player is at (0, 0), allowed to move down
        boolean expResult = true;
        boolean result = map.playerAllowedToMoveDown();
        assertEquals(expResult, result);
    }

    @Test
    public void testPlayerAllowedToMoveDownCopy() {
        System.out.println("Testing playerAllowedToMoveDown() for the second time ..");

        for (int i = 0; i < Game.VERTICAL_AMOUNT; i++) {
            map.keyPressed(KeyEvent.VK_DOWN);
        }

        // Player is at the lower left corner, not allowed to move down
        boolean expResult = false;
        boolean result = map.playerAllowedToMoveDown();
        assertEquals(expResult, result);
    }

    /**
     * Test of playerAllowedToMoveLeft method, of class Map.
     */
    @Test
    public void testPlayerAllowedToMoveLeft() {
        System.out.println("Testing playerAllowedToMoveLeft() ..");

        // Player is at (0, 0), not allowed to move left
        boolean expResult = false;
        boolean result = map.playerAllowedToMoveLeft();
        assertEquals(expResult, result);
    }

    @Test
    public void testPlayerAllowedToMoveLeftCopy() {
        System.out.println("Testing playerAllowedToMoveLeft() for the second time ..");

        map.keyPressed(KeyEvent.VK_RIGHT);

        // Player is at (1, 0), allowed to move left
        boolean expResult = true;
        boolean result = map.playerAllowedToMoveLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of playerAllowedToMoveRight method, of class Map.
     */
    @Test
    public void testPlayerAllowedToMoveRight() {
        System.out.println("Testing playerAllowedToMoveRight() ..");

        // Player is at (0, 0), allowed to move right
        boolean expResult = true;
        boolean result = map.playerAllowedToMoveRight();
        assertEquals(expResult, result);
    }

    @Test
    public void testPlayerAllowedToMoveRightCopy() {
        System.out.println("Testing playerAllowedToMoveRight() for the second time ..");

        for (int i = 0; i < Game.HORIZONTAL_AMOUNT; i++) {
            map.keyPressed(KeyEvent.VK_RIGHT);
        }

        // Player is at the most right tile of the map, not allowed to move right
        boolean expResult = false;
        boolean result = map.playerAllowedToMoveRight();
        assertEquals(expResult, result);
    }
}
