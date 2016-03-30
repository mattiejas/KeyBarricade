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
        int keyCount = 0;
        int[] randomKeyReplacement = { GROUND, WALL, BARRICADE };
        
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                int random = r.nextInt(4);
                if (random == KEY) {
                    keyCount++;
                    if (keyCount > KEY) {
                        random = randomKeyReplacement[r.nextInt(randomKeyReplacement.length)];
                    }
                }
                level[i][j] = random;
            }
        }
    }

    private void generateNormal() {

    }

    private void generateHard() {

    }

    private void generateImpossible() {

    }

    public int[][] getLevel() {
        return level;
    }
}
