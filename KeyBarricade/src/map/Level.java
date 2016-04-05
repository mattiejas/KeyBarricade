package map;

import java.util.ArrayList;
import java.util.Random;
import main.Difficulty;
import main.Game;

public class Level {

    private final int[][] LEVEL;
    private final int[][] POINTS;
    private final ArrayList<Coordinate> COORDINATES;
    private final Difficulty DIFFICULTY;

    public Level(Difficulty diff) {
        this.DIFFICULTY = diff;
        this.LEVEL = new int[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];
        this.POINTS = new int[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];
        this.COORDINATES = new ArrayList();
    }

    public void init() {
        // Initialize the available coordinates.
        for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
            for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
                COORDINATES.add(new Coordinate(x, y));
            }
        }

        // Fill the entire array with the value of -1 in order to track the 'empty' level tiles.
        for (Coordinate c : COORDINATES) {
            LEVEL[c.getX()][c.getY()] = -1;
        }

        // Generate the level according to the difficulty setting.
        switch (DIFFICULTY) {
            case TEST:
                generateTest();
                break;
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
    
    /**
     * Initialize the preferred settings of the test difficulty.
     */
    private void generateTest() {
        int wallLimit = 0;
        int barricadeLimit = 0;
        int keyLimit = 0;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    /**
     * Initialize the preferred settings of the easy difficulty.
     */
    private void generateEasy() {
        int wallLimit = 30;
        int barricadeLimit = 10;
        int keyLimit = 3;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    /**
     * Initialize the preferred settings of the normal difficulty.
     */
    private void generateNormal() {
        int wallLimit = 30;
        int barricadeLimit = 20;
        int keyLimit = 4;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    /**
     * Initialize the preferred settings of the hard difficulty.
     */
    private void generateHard() {
        int wallLimit = 30;
        int barricadeLimit = 30;
        int keyLimit = 5;

        generateLevel(wallLimit, barricadeLimit);
        generateKey(keyLimit);
        generateGround();
    }

    /**
     * Initialize the preferred settings of the impossible difficulty.
     */
    private void generateImpossible() {
        LEVEL[0][1] = Map.BARRICADE;
        LEVEL[1][1] = Map.WALL;
        LEVEL[1][0] = Map.WALL;
        LEVEL[0][2] = Map.KEY;
        generateGround();
    }

    /**
     * Generates the level according to the specified settings.
     *
     * @param wallLimit maximum amount of walls that gets generated.
     * @param barricadeLimit maximum amount of barricades that gets generated
     */
    private void generateLevel(int wallLimit, int barricadeLimit) {
        Random r = new Random();
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

    /**
     * Generate keys to the level array.
     *
     * @param keyLimit maximum amount of keys that gets generated
     */
    private void generateKey(int keyLimit) {
        Random r = new Random();
        int keyCount = 0;
        for (int i = 0; i < LEVEL.length; i++) {
            for (int j = 0; j < LEVEL[i].length; j++) {
                keyCount++;
                if (keyCount == 1) {
                    // Place the first key close to the user
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

    /**
     * Generate grounds to the level array.
     */
    private void generateGround() {
        for (int y = 0; y < LEVEL.length; y++) {
            for (int x = 0; x < LEVEL[y].length; x++) {
                if (LEVEL[y][x] == -1) {
                    LEVEL[y][x] = Map.GROUND;
                }
            }
        }
    }

    /**
     * Generate the points array
     */
    private void generatePoints() {
        Random r = new Random();
        for (Coordinate c : COORDINATES) {
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

    /**
     * Returns the two-dimensional array existing of integers.
     *
     * @return level
     */
    public int[][] getLevel() {
        return LEVEL;
    }

    /**
     * Returns the two-dimensional array existing of integers.
     *
     * @return points
     */
    public int[][] getPoints() {
        return POINTS;
    }

    /**
     * Returns the ArrayList of Coordinate object
     *
     * @return coordinates
     */
    public ArrayList<Coordinate> getCoordinates() {
        return COORDINATES;
    }

}
