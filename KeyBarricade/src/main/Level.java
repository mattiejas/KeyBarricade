package main;

import java.util.Random;

public class Level {

    private int[][] level;
    private Difficulty difficulty;
    private Random r;

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
//        level = new char[][]{
//            {G, G, G, G, G, G, G, G, G, W},
//            {G, G, G, G, K, G, G, G, G, G},
//            {G, G, G, G, G, G, G, G, G, G},
//            {G, G, G, G, G, K, G, G, G, G},
//            {G, G, G, G, B, G, G, G, G, G},
//            {G, G, G, G, G, G, G, G, G, G},
//            {G, G, G, G, G, G, W, W, G, G},
//            {G, G, G, G, G, G, W, W, G, G},
//            {G, G, G, G, G, G, G, G, G, G},
//            {G, G, G, G, G, G, G, G, G, G}
//        };
        int keyCount = 0;
        for (int x = 0; x < level.length; x++) {
            for (int y = 0; y < level[x].length; y++) {
                int random = r.nextInt(4);
                if (random == 3) {
                    keyCount++;
                    if (keyCount >= 4) {
                        random = 0;
                    }
                }
                level[x][y] = random;
                System.out.println(level[x][y]);
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
