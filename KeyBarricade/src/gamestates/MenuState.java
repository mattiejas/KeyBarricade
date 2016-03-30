package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.Game;
import assets.ResourceLoader;
import assets.Sprite;
import main.Map;

public class MenuState extends GameState {

    private String[] options;
    private String title;

    private int currentSelection;

    private Font titleFont;
    private Font titleFont2;
    private Font optionFont;
    private Font optionFont2;

    private boolean firstStart;
    private int optionPlacement;

    public MenuState(GameStateHandler handler) {
        super(handler);
        
        firstStart = true;
        optionPlacement = 2;
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                if (i == 1 && (x == 8 || x == 9)) {
                    g.drawImage(ResourceLoader.getSprite(Sprite.KEY), x * Game.BLOCKSIZE , i * Game.BLOCKSIZE , Game.BLOCKSIZE , Game.BLOCKSIZE , null);
                } else {
                    g.drawImage(ResourceLoader.getSprite(Sprite.GROUND), x * Game.BLOCKSIZE , i * Game.BLOCKSIZE , Game.BLOCKSIZE , Game.BLOCKSIZE , null);
                }
            }
        }
        g.setFont(titleFont2);
        g.setColor(Color.BLACK);
        g.drawString(title, 7, 120);

        g.setFont(titleFont);
        g.setColor(Color.CYAN);
        g.drawString(title, 11, 125);

        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                g.setColor(Color.BLACK);
                g.setFont(optionFont2);
                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - 65, (i + 7) * 45 + 3);

                g.setColor(Color.CYAN);
                g.setFont(optionFont);
                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - 65 - 4, (i + 7) * 45 + 3 - 5);
            } else {
                g.setFont(optionFont);
                g.drawString(options[i], Game.WINDOW_WIDTH / optionPlacement - 65, (i + 7) * 45 + 3);
            }
        }
    }

    @Override
    public void init() {
        if (firstStart == true) {
            options = new String[]{"Start", "Help", "Exit"};
        } else {
            options = new String[]{"Resume game", "Start new game", "Help", "Exit"};
        }

        title = "KeyBarricade";
        currentSelection = 0;
        titleFont = new Font("Joystix Monospace", Font.PLAIN, 50);
        titleFont2 = new Font("Joystix Monospace", Font.PLAIN, 51);
        optionFont = new Font("Joystix Monospace", Font.PLAIN, 35);
        optionFont2 = new Font("Joystix Monospace", Font.PLAIN, 36);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            if (firstStart) {
                switch (currentSelection) {
                    case 0:
                        handler.setState(PLAYSTATE);
                        firstStart = false;
                        optionPlacement = 3;
                        break;
                    case 1:
                        break;
                    case 2:
                        System.exit(0);
                    default:
                        break;
                }
            }else{
                switch (currentSelection) {
                    case 0:
                        handler.setPreviousState();
                        break;
                    case 1:
                        handler.setState(PLAYSTATE);
                        break;
                    case 2:
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
    public void keyReleased(int k) {

    }

    @Override
    public void keyTyped(int k) {

    }

}
