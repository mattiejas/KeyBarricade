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
        background = new BufferedImage[Game.VERTICAL_AMOUNT][Game.HORIZONTAL_AMOUNT];

        font = new Font[3];
        font[0] = new Font("Joystix Monospace", Font.PLAIN, 52); // title
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 53); // title shadow
        font[2] = new Font("Joystix Monospace", Font.PLAIN, 36); // default font        

        title = "KeyBarricade";
        currentSelection = 0;
        
        // Initialize array with GROUND sprites.
        for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
            for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
                background[y][x] = ResourceLoader.getSprite(Sprite.GROUND);
                if (y == 3 && x == 5) {
                    background[y][x] = ResourceLoader.getSprite(Sprite.KEY);
                }
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
                g.drawImage(background[y][x], x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
            }
        }
        g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_RIGHT), 4 * Game.BLOCK_SIZE, 3 * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);

        g.setFont(font[1]);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 122);

        g.setFont(font[0]);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);

        // Draws options perfectly in the middle (width) of the screen
        g.setFont(font[2]);
        int spacing = g.getFontMetrics().getHeight();
        int j = (int)(Game.WINDOW_HEIGHT / 2 - (spacing * menuLength * 1.25));
        if (firstStart) {
            j = (Game.WINDOW_HEIGHT / 2 - (spacing * menuLength * 2));
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
                            handler.setState(PLAY_STATE);
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

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void keyTyped(int k) {
    }
}
