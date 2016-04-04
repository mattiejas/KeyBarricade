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

    private String[] option;
    private String title;

    private int currentSelection;
    private int menuLength;

    private Font[] font;

    private boolean firstStart;

    private int width;
    private int height;

    private BufferedImage[][] background;

    public MenuState(GameStateHandler handler) {
        super(handler);
        this.firstStart = true;
    }

    @Override
    public void init() {
        background = new BufferedImage[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];

        font = new Font[2];
        font[0] = new Font("Joystix Monospace", Font.PLAIN, 50); // title
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 36); // default font        

        title = "KeyBarricade";
        currentSelection = 0;

        // Initialize array with GROUND sprites.
        for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
            for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
                background[x][y] = ResourceLoader.getSprite(Sprite.GROUND);
            }
        }

        if (firstStart) {
            option = new String[]{"Start", "Help", "Exit"};
        } else {
            option = new String[]{"Resume game", "Restart game", "Start new game", "Help", "Exit"};
        }
        menuLength = option.length;
    }

    @Override
    public void render(Graphics2D g) {
        for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
            for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
                g.drawImage(background[x][y], x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
            }
        }
        g.drawImage(ResourceLoader.getSprite(Sprite.BARRICADE), (Game.HORIZONTAL_AMOUNT / 2) * Game.BLOCK_SIZE, 3 * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
        g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_RIGHT), (Game.HORIZONTAL_AMOUNT / 2 - 1) * Game.BLOCK_SIZE, 3 * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
        g.drawImage(ResourceLoader.getSprite(Sprite.KEY), (Game.HORIZONTAL_AMOUNT / 2 + 1) * Game.BLOCK_SIZE, 3 * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);

        g.setFont(font[0]);
        g.setColor(Color.BLACK);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2 + 3, 115);

        g.setFont(font[0]);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 112);

        // Draws options perfectly in the middle (width) of the screen
        g.setFont(font[1]);
        int spacing = g.getFontMetrics().getHeight();
        int j = 100;
        if (!firstStart) {
            j = 70;
        }
        
        g.setColor(Color.BLACK);
        for (int i = 0; i < option.length; i++, j += spacing) {
            height = g.getFontMetrics().getHeight();
            if (i == currentSelection) {
                width = g.getFontMetrics().stringWidth("> " + option[i] + " <");
                g.drawString("> " + option[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2) + 2, (Game.WINDOW_HEIGHT / 2) + j + 2);
            } else {
                width = g.getFontMetrics().stringWidth(option[i]);
                g.drawString(option[i], (Game.WINDOW_WIDTH / 2) - (width / 2) + 2, (Game.WINDOW_HEIGHT / 2) + j + 2);
            }
        }
        
        j = 100;
        if (!firstStart) {
            j = 70;
        }
        g.setColor(Color.WHITE);
        for (int i = 0; i < option.length; i++, j += spacing) {
            height = g.getFontMetrics().getHeight();
            if (i == currentSelection) {
                width = g.getFontMetrics().stringWidth("> " + option[i] + " <");
                g.drawString("> " + option[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) + j);
            } else {
                width = g.getFontMetrics().stringWidth(option[i]);
                g.drawString(option[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) + j);
            }
        }
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                if (firstStart) {
                    switch (currentSelection) {
                        case 0:
                            handler.setState(DIFFICULTY_STATE);
                            break;
                        case 1:
                            handler.setState(HELP_STATE);
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
                            handler.restartPlayState();
                            break;
                        case 2:
                            handler.setState(DIFFICULTY_STATE);
                            break;
                        case 3:
                            handler.setState(HELP_STATE);
                            break;
                        case 4:
                            System.exit(0);
                        default:
                            break;
                    }
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (currentSelection < menuLength - 1) {
                    currentSelection++;
                } else {
                    currentSelection = 0;
                }
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (currentSelection > 0) {
                    currentSelection--;
                } else {
                    currentSelection = menuLength - 1;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (!firstStart) {
                    handler.setPreviousPlayState();
                }
                break;
        }
    }
}
