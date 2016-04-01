package gamestates;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import main.Difficulty;
import main.Game;

public class DifficultyState extends GameState {

    private MenuState ms;

    private String[] option;
    private String title;

    private int currentSelection;

    private Font[] font;

    private int width;
    private int height;

    private BufferedImage[][] background;

    public DifficultyState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {
        background = new BufferedImage[Game.VERTICAL_AMOUNT][Game.HORIZONTAL_AMOUNT];
        font = new Font[3];
        option = new String[]{"Easy", "Normal", "Hard", "Impossible"};

        font[0] = new Font("Joystix Monospace", Font.PLAIN, 52);
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 53);
        font[2] = new Font("Joystix Monospace", Font.PLAIN, 24);

        title = "KeyBarricade";
        currentSelection = 0;

        // Initialize array with GROUND sprites.
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                background[y][x] = ResourceLoader.getSprite(Sprite.GROUND);
                if (y == 3 && x == 5) {
                    background[y][x] = ResourceLoader.getSprite(Sprite.KEY);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                g.drawImage(background[y][x], x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
            }
        }
        g.drawImage(ResourceLoader.getSprite(Sprite.PLAYER_DOWN), 4 * Game.BLOCK_SIZE, 3 * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);

        g.setFont(font[1]);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 122);

        g.setFont(font[0]);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);

        g.setFont(font[2]);
        int spacing = g.getFontMetrics().getHeight() + 10;
        int j = (Game.WINDOW_HEIGHT / 2 - (spacing * 4 * 2));
        
        g.setColor(Color.WHITE);
        
        for (int i = 0; i < option.length; i++, j += spacing) {
            height = g.getFontMetrics().getHeight();
            if (i == currentSelection) {
                width = g.getFontMetrics().stringWidth("> " + option[i] + " <");
                g.drawString("> " + option[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            } else {
                width = g.getFontMetrics().stringWidth(option[i]);
                g.drawString(option[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                switch (currentSelection) {
                    default:
                    case 0:
                        handler.setPlayState(Difficulty.EASY);
                        ms = handler.getMenuState();
                        ms.setFirstStart(false);
                        break;
                    case 1:
                        handler.setPlayState(Difficulty.NORMAL);
                        ms = handler.getMenuState();
                        ms.setFirstStart(false);
                        break;
                    case 2:
                        handler.setPlayState(Difficulty.HARD);
                        ms = handler.getMenuState();
                        ms.setFirstStart(false);
                        break;
                    case 3:
                        handler.setPlayState(Difficulty.IMPOSSIBLE);
                        ms = handler.getMenuState();
                        ms.setFirstStart(false);
                        break;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                handler.setPreviousGameState();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (currentSelection < option.length - 1) {
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
                    currentSelection = option.length - 1;
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
