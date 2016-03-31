package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Game;
import assets.ResourceLoader;
import assets.Sprite;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private String[] options;
    private String[] difficulty;
    private String title;

    private int currentSelection;
    private int currentDifficulty;
    private int menuLength;

    private Font titleFont;
    private Font titleFontShadow;
    private Font defaultFont;
    private Font smallFont;

    private boolean firstStart;
    private boolean difficultySelection;

    private int width;
    private int height;

    private BufferedImage[][] backGround;

    public MenuState(GameStateHandler handler) {
        super(handler);

        this.currentDifficulty = 0;
        this.firstStart = true;
    }

    @Override
    public void init() {
        backGround = new BufferedImage[10][10];

        // Initialize array with GROUND sprites.
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                backGround[y][x] = ResourceLoader.getSprite(Sprite.GROUND);
                if (y == 3 && x == 5) {
                    backGround[y][x] = ResourceLoader.getSprite(Sprite.KEY);
                }
            }
        }

        if (firstStart == true) {
            difficulty = new String[]{"Easy", "Normal", "Hard", "Impossible"};
            options = new String[]{"Start", "Help", "Exit"};
        } else {
            options = new String[]{"Resume Game", "Start New Game", "Help", "Exit"};
        }

        title = "KeyBarricade";
        currentSelection = 0;
        menuLength = options.length;

        titleFont = new Font("Joystix Monospace", Font.PLAIN, 52);
        titleFontShadow = new Font("Joystix Monospace", Font.PLAIN, 53);
        defaultFont = new Font("Joystix Monospace", Font.PLAIN, 36);
        smallFont = new Font("Joystix Monospace", Font.PLAIN, 24);
    }

    @Override
    public void render(Graphics2D g) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                g.drawImage(backGround[y][x], x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
            }
        }
        g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_DOWN), 4 * Game.BLOCKSIZE, 3 * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);

        g.setFont(titleFontShadow);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 124);

        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);

        // Draws options perfectly in the middle (width) of the screen
        g.setFont(defaultFont);
        int spacing = 50;
        int j = (Game.WINDOW_HEIGHT / Game.BLOCKSIZE);
        if (difficultySelection) {
            for (int i = 0; i < difficulty.length; i++) {
                j += spacing;
                if (i == currentSelection) {
                    g.setFont(smallFont);
                    g.setColor(Color.WHITE);
                    width = g.getFontMetrics().stringWidth("> " + difficulty[i] + " <");
                    height = g.getFontMetrics().getHeight();
                    g.drawString("> " + difficulty[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
                } else {
                    g.setFont(smallFont);
                    g.setColor(Color.WHITE);
                    width = g.getFontMetrics().stringWidth(difficulty[i]);
                    height = g.getFontMetrics().getHeight();
                    g.drawString(difficulty[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
                }
            }
        } else {
            for (int i = 0; i < options.length; i++) {
                j += spacing;
                g.setFont(defaultFont);
                height = g.getFontMetrics().getHeight();
                g.setColor(Color.WHITE);
                if (i == currentSelection) {
                    width = g.getFontMetrics().stringWidth("> " + options[i] + " <");
                    g.drawString("> " + options[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
                } else {
                    width = g.getFontMetrics().stringWidth(options[i]);
                    g.drawString(options[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
                }
            }
        }
    }

    @Override
    public void keyPressed(int k
    ) {
        if (k == KeyEvent.VK_ENTER) {
            if (firstStart && !difficultySelection) {
                switch (currentSelection) {
                    case 0:
                        difficultySelection = true;
                        menuLength = difficulty.length;
//                        handler.setState(PLAYSTATE);
//                        firstStart = false;
                        break;
                    case 1:
                        handler.setState(HELPSTATE);
                        break;
                    case 2:
                        System.exit(0);
                    default:
                        break;
                }
            } else if (!difficultySelection) {
                switch (currentSelection) {
                    case 0:
                        handler.setPreviousState();
                        break;
                    case 1:
                        handler.setState(PLAYSTATE);
                        break;
                    case 2:
                        handler.setState(HELPSTATE);
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        break;
                }
            } else {
                switch (currentSelection) {
                    default:
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        difficultySelection = false;
                        menuLength = options.length;
                        handler.setState(PLAYSTATE);
                        firstStart = false;
                        break;
                }
            }
        } else if (k == KeyEvent.VK_S) {
            if (currentSelection < menuLength - 1) {
                currentSelection++;
            } else {
                currentSelection = 0;
            }
        } else if (k == KeyEvent.VK_W) {
            if (currentSelection > 0) {
                currentSelection--;
            } else {
                currentSelection = menuLength - 1;
            }
        }
    }

    @Override
    public void keyReleased(int k
    ) {
        //     ------       -------
        //     | O   |      |    O |
        //            ------
        //                  |
        //                  |
        //            ------
        //
        //    ___/\__________/\____

    }

    @Override
    public void keyTyped(int k
    ) {

    }

}
