package main;

import java.util.Random;

public class Level {

    private final int[][] LEVEL;
    private final Difficulty DIFFICULTY;
    private Random r;
    
    private final int GROUND = 0;
    private final int WALL = 1;
    private final int BARRICADE = 2;
    private final int KEY = 3;    

    public Level(Difficulty diff) {
        this.DIFFICULTY = diff;
        this.LEVEL = new int[10][10];
    }

    public void init() {
        r = new Random();
        
        // Insert the entire array with value -1 in order to track the 'empty' level tiles.
        for (int i = 0; i < LEVEL.length; i++) {
            for (int j = 0; j < LEVEL[i].length; j++) {
                LEVEL[i][j] = -1;
            }
        }
        
        switch (DIFFICULTY) {
            case EASY:
                generateEasy();
                break;
            case NORMAL:
                generateNormal();
                break;
            case HARD:
                generateHard();
                break;
            case IMPOSSIBLE:
                generateImpossible();
                break;
        }
    }

    private void generateEasy() {
        int wallLimit = 30;
        int barricadeLimit = 10;
        
        generateLevel(wallLimit, barricadeLimit);
        generateKey(3);
        generateGround();
    }

    private void generateNormal() {
        int wallLimit = 30;
        int barricadeLimit = 20;
        
        generateLevel(wallLimit, barricadeLimit);
        generateKey(3);
        generateGround();
    }

    private void generateHard() {
        int wallLimit = 30;
        int barricadeLimit = 30;
        
        generateLevel(wallLimit, barricadeLimit);
        generateKey(3);
        generateGround();
    }

    private void generateImpossible() {
        LEVEL[0][1] = BARRICADE;
        LEVEL[1][1] = WALL;
        LEVEL[1][0] = WALL;
        LEVEL[0][2] = KEY;
        generateGround();
    }
    
    private void generateLevel(int wallLimit, int barricadeLimit) {
        int wallCount = 0;
        int barricadeCount = 0;
        
        for (int i = 0; i < LEVEL.length; i++) {
            for (int j = 0; j < LEVEL[i].length; j++) {
                int random = r.nextInt(2) + 1; // Set random number between WALL and BARRICADEs

                int randomY = r.nextInt(10);
                int randomX = r.nextInt(10);

                if (LEVEL[randomY][randomX] == -1) {
                    if (random == WALL) {
                        wallCount++;
                        if(wallCount > wallLimit) {
                            random = GROUND;
                        }
                    }
                    if (random == BARRICADE) {
                        barricadeCount++;
                        if(barricadeCount > barricadeLimit) {
                            random = GROUND;
                        }
                    }                    
                    LEVEL[randomY][randomX] = random;
                }
            }
        }        
    }
    
    private void generateKey(int keyLimit) {
        int keyCount = 0;
        for (int i = 0; i < LEVEL.length; i++) {
            for (int j = 0; j < LEVEL[i].length; j++) {
                keyCount++;
                if (keyCount == 1) {
                    // Place to first key close to the user
                    int randomY = r.nextInt(3);
                    int randomX = r.nextInt(3);
                    
                    if (randomY == 0 && randomX == 0) {
                        randomY = r.nextInt(3);
                        randomX = r.nextInt(3);
                    }
                    LEVEL[randomY][randomX] = KEY;
                } else {
                    int randomY = r.nextInt(10);
                    int randomX = r.nextInt(10);
                    if (keyCount <= keyLimit) {
                        LEVEL[randomY][randomX] = KEY;
                    }
                }
            }   
        }        
    }
    
    private void generateGround() {
        for (int y = 0; y < LEVEL.length; y++) {
            for (int x = 0; x < LEVEL[y].length; x++) {
                if (LEVEL[y][x] == -1) {
                    LEVEL[y][x] = GROUND;
                }
            }
        }        
    }
    
    public int[][] getLevel() {
        return LEVEL;
    }
}
