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

    private String[] options;
    private String title;

    private int currentSelection;

    private Font titleFont;
    private Font titleFontShadow;
    private Font defaultFont;
    private Font smallFont;

    private int width;
    private int height;

    private BufferedImage[][] backGround;

    private Difficulty selectedDifficulty;

    public DifficultyState(GameStateHandler handler) {
        super(handler);
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

        options = new String[]{"Easy", "Normal", "Hard", "Impossible"};

        title = "KeyBarricade";
        currentSelection = 0;

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
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 122);

        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);
        
        g.setFont(smallFont);
        int spacing = g.getFontMetrics().getHeight() + 10;
        int j = (Game.WINDOW_HEIGHT / 2 - (spacing * 4 * 2));
        
        for (int i = 0; i < options.length; i++) {
            j += spacing;
            g.setColor(Color.WHITE);
            height = g.getFontMetrics().getHeight();
            if (i == currentSelection) {
                width = g.getFontMetrics().stringWidth("> " + options[i] + " <");
                g.drawString("> " + options[i] + " <", (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            } else {
                width = g.getFontMetrics().stringWidth(options[i]);
                g.drawString(options[i], (Game.WINDOW_WIDTH / 2) - (width / 2), (Game.WINDOW_HEIGHT / 2) - (height / 2) + j);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE){
            switch (currentSelection) {
                    default:
                    case 0:
                        handler.setState(PLAYSTATE, Difficulty.EASY);
                        break;
                    case 1:
                        handler.setState(PLAYSTATE, Difficulty.NORMAL);
                        break;
                    case 2:
                        handler.setState(PLAYSTATE, Difficulty.HARD);
                        break;
                    case 3:
                        handler.setState(PLAYSTATE, Difficulty.IMPOSSIBLE);
                        break;
                }
        }else if(k == KeyEvent.VK_ESCAPE){
            handler.setState(MENUSTATE);
        }else if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
            if (currentSelection < options.length - 1) {
                currentSelection++;
            } else {
                currentSelection = 0;
            }
        } else if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
            if (currentSelection > 0) {
                currentSelection--;
            } else {
                currentSelection = options.length - 1;
            }
        } 
    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void keyTyped(int k) {

    }

}
