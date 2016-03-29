package main;

public class Level {

    private char[][] level;
    private Difficulty difficulty;

    private final char G = 'g';
    private final char K = 'k';
    private final char W = 'w';
    private final char B = 'b';

    public Level(Difficulty diff) {
        this.difficulty = diff;
    }

    public void init() {
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
        level = new char[][]{
            {G, G, G, G, G, G, G, G, G, W},
            {G, G, G, G, K, G, G, G, G, G},
            {G, G, G, G, G, G, G, G, G, G},
            {G, G, G, G, G, K, G, G, G, G},
            {G, G, G, G, B, G, G, G, G, G},
            {G, G, G, G, G, G, G, G, G, G},
            {G, G, G, G, G, G, W, W, G, G},
            {G, G, G, G, G, G, W, W, G, G},
            {G, G, G, G, G, G, G, G, G, G},
            {G, G, G, G, G, G, G, G, G, G}
        };
    }

    private void generateNormal() {

    }

    private void generateHard() {

    }

    private void generateImpossible() {

    }

    public char[][] getLevel() {
        return level;
    }
}
