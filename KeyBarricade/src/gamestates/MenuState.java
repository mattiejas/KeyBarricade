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
    private String title;

    private int currentSelection;

    private Font titleFont;
    private Font titleFontShadow;
    private Font optionFont;
    private Font optionFont2;

    private boolean firstStart;
    private int optionPlacement;

    private int width;
    private int height;

    private BufferedImage[][] backGround;

    public MenuState(GameStateHandler handler) {
        super(handler);

        firstStart = true;
        optionPlacement = 2;
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

//        for (int i = 0; i < options.length; i++) {
//            if (i == currentSelection) {
//                g.setColor(Color.BLACK);
//                g.setFont(optionFont2);
//                int width = g.getFontMetrics().stringWidth(options[i]);
//                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - (width / 2), (i + 7) * 45 + 3);
//
//                g.setColor(Color.CYAN);
//                g.setFont(optionFont);
//                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - (width / 2) - 4, (i + 7) * 45 + 3 - 5);
//                g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_DOWN), Game.WINDOW_WIDTH / optionPlacement - 125, (i + 7) * 45 - 50, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
//
//            } else {
//                g.setFont(optionFont);
//                int width = g.getFontMetrics().stringWidth(options[i]);
//                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - (width / 2), (i + 7) * 45 + 3);
//            }
//        }
        // Draws options perfectly in the middle (width) of the screen
        g.setFont(optionFont);
        int spacing = 50;
        int j = (Game.WINDOW_HEIGHT / Game.BLOCKSIZE);
        for (int i = 0; i < options.length; i++) {
            j += spacing;
            width = g.getFontMetrics().stringWidth(options[i]);
            height = g.getFontMetrics().getHeight();
            if (i == currentSelection) {
                g.setColor(Color.WHITE);
                width = g.getFontMetrics().stringWidth("> " + options[i] + " <");
                g.drawString("> " + options[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            } else {
                g.setColor(Color.WHITE);
                g.drawString(options[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            }
        }
    }

    @Override
    public void init() {
        backGround = new BufferedImage[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int x = 0; x < 10; x++) {
//                if (i == 1 && (x == 8 || x == 9)) {
//                    backGround[i][x] = ResourceLoader.getSprite(Sprite.KEY);
//                } else {
//                    backGround[i][x] = ResourceLoader.getSprite(Sprite.GROUND);
//                }
//            }
//        }

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
            options = new String[]{"Start", "Help", "Exit"};
        } else {
            options = new String[]{"Resume Game", "Start New Game", "Help", "Exit"};
        }

        title = "KeyBarricade";
        currentSelection = 0;
        titleFont = new Font("Joystix Monospace", Font.PLAIN, 50);
        titleFontShadow = new Font("Joystix Monospace", Font.PLAIN, 51);
        optionFont = new Font("Joystix Monospace", Font.PLAIN, 35);
        optionFont2 = new Font("Joystix Monospace", Font.PLAIN, 36);
    }

    @Override
    public void keyPressed(int k
    ) {
        if (k == KeyEvent.VK_ENTER) {
            if (firstStart) {
                switch (currentSelection) {
                    case 0:
                        handler.setState(PLAYSTATE);
                        firstStart = false;
                        optionPlacement = 3;
                        break;
                    case 1:
                        handler.setState(HELPSTATE);
                        break;
                    case 2:
                        System.exit(0);
                    default:
                        break;
                }
            } else {
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

            }

        } else if (k == KeyEvent.VK_S) {
            if (currentSelection < options.length - 1) {
                currentSelection++;
            } else {
                currentSelection = 0;
            }
        } else if (k == KeyEvent.VK_W) {
            if (currentSelection > 0) {
                currentSelection--;
            } else {
                currentSelection = options.length - 1;
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
