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
    private int menuLength;

    private Font titleFont;
    private Font titleFontShadow;
    private Font defaultFont;

    private boolean firstStart;

    private int width;
    private int height;

    private BufferedImage[][] backGround;


    public MenuState(GameStateHandler handler) {
        super(handler);

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
    }

    @Override
    public void render(Graphics2D g) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                g.drawImage(backGround[y][x], x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
            }
        }
        g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_RIGHT), 4 * Game.BLOCKSIZE, 3 * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);

        g.setFont(titleFontShadow);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 122);

        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);

        // Draws options perfectly in the middle (width) of the screen
        g.setFont(defaultFont);
        int spacing = g.getFontMetrics().getHeight();
        int j = (Game.WINDOW_HEIGHT / 2 - (spacing * menuLength * 2));
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
    
    public void setFirstStartFalse(){
        this.firstStart = false;
    }

    @Override
    public void keyPressed(int k
    ) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
            if (firstStart) {
                switch (currentSelection) {
                    case 0:
                        handler.setState(DIFFICULTYSTATE);
                        break;
                    case 1:
                        handler.setState(HELPSTATE);
                        break;
                    case 2:
                        System.exit(0);
                    default:
                        break;
                }
            } else if (!firstStart) {
                switch (currentSelection) {
                    case 0:
                        handler.setPreviousPlayState();
                        break;
                    case 1:
                        handler.setState(DIFFICULTYSTATE);
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
        } else if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
            if (currentSelection < menuLength - 1) {
                currentSelection++;
            } else {
                currentSelection = 0;
            }
        } else if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
            if (currentSelection > 0) {
                currentSelection--;
            } else {
                currentSelection = menuLength - 1;
            }
        } else if (!firstStart && k == KeyEvent.VK_ESCAPE) {
            handler.setPreviousPlayState();
        }
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void keyTyped(int k) {
    }
}
