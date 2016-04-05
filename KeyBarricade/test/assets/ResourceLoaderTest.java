/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeffrey
 */
public class ResourceLoaderTest {
    
    public ResourceLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ResourceLoader.init();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteGROUND() {
        System.out.println("Testing the position of the GROUND sprite method.");
        
        Sprite s = Sprite.GROUND;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 0;
        int expResultY = 32;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteKEY() {
        System.out.println("Testing the position and size of the KEY sprite method.");
        
        Sprite s = Sprite.KEY;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 64;
        int expResultY = 0;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteWALL() {
        System.out.println("Testing the position and size of the WALL sprite method.");
        
        Sprite s = Sprite.WALL;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 0;
        int expResultY = 0;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }    
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteBARRICADE() {
        System.out.println("Testing the position and size of the BARRICADE sprite method.");
        
        Sprite s = Sprite.BARRICADE;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 32;
        int expResultY = 0;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }  
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteBARRICADE_UNLOCKED() {
        System.out.println("Testing the position and size of the BARRICADE_UNLOCKED sprite method.");
        
        Sprite s = Sprite.BARRICADE_UNLOCKED;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 32;
        int expResultY = 32;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }   
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteSTART() {
        System.out.println("Testing the position and size of the BARRICADE_UNLOCKED sprite method.");
        
        Sprite s = Sprite.START;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 0;
        int expResultY = 64;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }    
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteFINISH() {
        System.out.println("Testing the position and size of the FINISH sprite method.");
        
        Sprite s = Sprite.FINISH;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 32;
        int expResultY = 64;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_DOWN() {
        System.out.println("Testing the position and size of the PLAYER_DOWN sprite method.");
        
        Sprite s = Sprite.PLAYER_DOWN;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 0;
        int expResultY = 96;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }  
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_UP() {
        System.out.println("Testing the position and size of the PLAYER_UP sprite method.");
        
        Sprite s = Sprite.PLAYER_UP;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 32;
        int expResultY = 96;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_LEFT() {
        System.out.println("Testing the position and size of the PLAYER_LEFT sprite method.");
        
        Sprite s = Sprite.PLAYER_LEFT;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 64;
        int expResultY = 96;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_RIGHT() {
        System.out.println("Testing the position and size of the PLAYER_RIGHT sprite method.");
        
        Sprite s = Sprite.PLAYER_RIGHT;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 96;
        int expResultY = 96;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_ITEM_DOWN() {
        System.out.println("Testing the position and size of the PLAYER_ITEM_DOWN sprite method.");
        
        Sprite s = Sprite.PLAYER_ITEM_DOWN;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 0;
        int expResultY = 128;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_ITEM_UP() {
        System.out.println("Testing the position and size of the PLAYER_ITEM_UP sprite method.");
        
        Sprite s = Sprite.PLAYER_ITEM_UP;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 32;
        int expResultY = 128;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    } 
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_ITEM_LEFT() {
        System.out.println("Testing the position and size of the PLAYER_ITEM_LEFT sprite method.");
        
        Sprite s = Sprite.PLAYER_ITEM_LEFT;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 64;
        int expResultY = 128;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpritePLAYER_ITEM_RIGHT() {
        System.out.println("Testing the position and size of the PLAYER_ITEM_RIGHT sprite method.");
        
        Sprite s = Sprite.PLAYER_ITEM_RIGHT;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 32;
        int expResultHeight = 32;
        int expResultX = 96;
        int expResultY = 128;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }     
    
    /**
     * Test of getSprite method, of class ResourceLoader.
     */
    @Test
    public void testGetSpriteITEM_KEY() {
        System.out.println("Testing the position and size of the ITEM_KEY sprite method.");
        
        Sprite s = Sprite.ITEM_KEY;
        BufferedImage image = ResourceLoader.getSprite(s);
        
        int width = image.getWidth();
        int height = image.getHeight();
        int x = image.getTileGridXOffset() / -1;
        int y = image.getTileGridYOffset() / -1;
        
        int expResultWidth = 16;
        int expResultHeight = 16;
        int expResultX = 64;
        int expResultY = 32;
        
        assertEquals(expResultWidth, width);
        assertEquals(expResultHeight, height);
        assertEquals(expResultX, x);
        assertEquals(expResultY, y);
        
    }    
}
