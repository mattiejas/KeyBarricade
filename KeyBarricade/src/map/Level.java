package map;

import java.util.ArrayList;
import java.util.Random;
import main.Difficulty;
import main.Game;

public class Level {

    private final int[][] LEVEL;
    private final int[][] POINTS;
    private ArrayList<Coordinate> coordinates;
    private final Difficulty DIFFICULTY;
    private Random r;

    public Level(Difficulty diff) {
        this.DIFFICULTY = diff;
        this.LEVEL = new int[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];
        this.POINTS = new int[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];
        this.coordinates = new ArrayList();
    }

    public void init() {
        r = new Random();

        for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
            for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
                coordinates.add(new Coordinate(x, y));
            }
        }

        // Insert the entire array with value -1 in order to track the 'empty' level tiles.
        for (Coordinate c : coordinates) {
            LEVEL[c.getX()][c.getY()] = -1;
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
        
        generatePoints();
    }

    private void generateEasy() {
        int wallLimit = 30;
        int barricadeLimit = 10;
        int keyLimit = 3;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    private void generateNormal() {
        int wallLimit = 30;
        int barricadeLimit = 20;
        int keyLimit = 4;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    private void generateHard() {
        int wallLimit = 30;
        int barricadeLimit = 30;
        int keyLimit = 5;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    private void generateImpossible() {
        LEVEL[0][1] = Map.BARRICADE;
        LEVEL[1][1] = Map.WALL;
        LEVEL[1][0] = Map.WALL;
        LEVEL[0][2] = Map.KEY;
        generateGround();
    }

    private void generateLevel(int wallLimit, int barricadeLimit) {
        int wallCount = 0;
        int barricadeCount = 0;

        for (int y = 0; y < LEVEL.length; y++) {
            for (int x = 0; x < LEVEL[y].length; x++) {
                int random = r.nextInt(2) + 1; // Set random number between WALL and BARRICADE

                int randomX = r.nextInt(Game.HORIZONTAL_AMOUNT);
                int randomY = r.nextInt(Game.VERTICAL_AMOUNT);

                if (LEVEL[randomX][randomY] == -1) {
                    if (random == Map.WALL) {
                        wallCount++;
                        if (wallCount > wallLimit) {
                            random = Map.GROUND;
                        }
                    }
                    if (random == Map.BARRICADE) {
                        barricadeCount++;
                        if (barricadeCount > barricadeLimit) {
                            random = Map.GROUND;
                        }
                    }
                    LEVEL[randomX][randomY] = random;
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
                    LEVEL[randomY][randomX] = Map.KEY;
                } else {
                    int randomY = r.nextInt(10);
                    int randomX = r.nextInt(10);
                    if (keyCount <= keyLimit) {
                        LEVEL[randomY][randomX] = Map.KEY;
                    }
                }
            }
        }
    }

    private void generatePoints() {
        Random r = new Random();
        for (Coordinate c : coordinates) {
            switch (LEVEL[c.getX()][c.getY()]) {
                default:
                    POINTS[c.getX()][c.getY()] = 0;
                    break;
                case Map.BARRICADE:
                    POINTS[c.getX()][c.getY()] = (r.nextInt(3) + 1) * 100;
                    break;
                case Map.KEY:
                    POINTS[c.getX()][c.getY()] = (r.nextInt(3) + 1) * 100;
                    break;
            }
        }
    }

    private void generateGround() {
        for (int y = 0; y < LEVEL.length; y++) {
            for (int x = 0; x < LEVEL[y].length; x++) {
                if (LEVEL[y][x] == -1) {
                    LEVEL[y][x] = Map.GROUND;
                }
            }
        }
    }

    public int[][] getLevel() {
        return LEVEL;
    }
    
    public int[][] getPoints() {
        return POINTS;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }
}
