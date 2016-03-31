package main;

import java.util.Random;

public class Level {

    private int[][] level;
    private Difficulty difficulty;
    private Random r;
    
    private final int GROUND = 0;
    private final int WALL = 1;
    private final int BARRICADE = 2;
    private final int KEY = 3;    

    public Level(Difficulty diff) {
        this.difficulty = diff;
        this.level = new int[10][10];
    }

    public void init() {
        r = new Random();
        
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                level[i][j] = -1;
            }
        }
        switch (difficulty) {
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
        int wallCount = 0;
        int wallLimit = 30;
        
        int barricadeCount = 0;
        int barricadeLimit = 10;
        
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                int random = r.nextInt(3);

                int randomI = r.nextInt(10);
                int randomJ = r.nextInt(10);

                if (level[randomI][randomJ] == -1) {
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
                    level[randomI][randomJ] = random;
                }
            }
        }
        
        int keyCount = 0;
        int keyLimit = 3;
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                keyCount++;
                int randomI = r.nextInt(10);
                int randomJ = r.nextInt(10);
                if (keyCount <= keyLimit) {
                    level[randomI][randomJ] = KEY;
                }
            }   
        }
        
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == -1) {
                    level[i][j] = GROUND;
                }
            }
        }        
       
    }

    private void generateNormal() {

    }

    private void generateHard() {

    }

    private void generateImpossible() {
    }

    private int replaceBlockType(int blockType) {
        int random = r.nextInt(4);
        if (random == blockType) {
            return replaceBlockType(blockType);
        } else {
            return random;
        }
    }
    
    public int[][] getLevel() {
        return level;
    }
}
