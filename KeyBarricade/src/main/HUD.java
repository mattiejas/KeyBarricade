package main;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class HUD implements ActionListener {

    private final Font FONT[];

    private final Timer MESSAGE_TIMER;
    private final Timer PRESS_KEY_TIMER;

    private boolean newMessage;
    private boolean hasItem;
    private boolean startNewGame;
    private boolean winGame;

    private Color blink;
    private Color none;

    private String message;
    private String itemName;

    private int itemCount;

    private final ArrayList<String> MOTIVATIONAL_MESSAGES;

    public HUD() {
        MESSAGE_TIMER = new Timer(4000, this);
        PRESS_KEY_TIMER = new Timer(700, this);

        FONT = new Font[2];
        MOTIVATIONAL_MESSAGES = new ArrayList<>();
    }

    public void init() {
        FONT[0] = new Font("Joystix Monospace", Font.PLAIN, 36);
        FONT[1] = new Font("Joystix Monospace", Font.PLAIN, 18);

        blink = Color.WHITE;
        none = new Color(0, 0, 0, 0f);

        MOTIVATIONAL_MESSAGES.add("Good work!");
        MOTIVATIONAL_MESSAGES.add("You're doing great, my friend.");
        MOTIVATIONAL_MESSAGES.add("Keep up the good work!");
        MOTIVATIONAL_MESSAGES.add("What the hell?");
        MOTIVATIONAL_MESSAGES.add("Oh my..");
        MOTIVATIONAL_MESSAGES.add("Really?");
        MOTIVATIONAL_MESSAGES.add("Very well then..");
        MOTIVATIONAL_MESSAGES.add("Uh, okay?");
        MOTIVATIONAL_MESSAGES.add("Great..");
    }

    public void render(Graphics2D g) {
        if (winGame) {
            hasItem = false;

            g.setColor(new Color(0, 0, 0, 1f));
            g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
            g.setColor(new Color(0, 0, 0, 0.7f));
            g.fillRect((int) (Game.BLOCK_SIZE * 0.5), (int) (Game.BLOCK_SIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCK_SIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCK_SIZE));
            g.setColor(Color.WHITE);
            g.drawRect((int) (Game.BLOCK_SIZE * 0.5), (int) (Game.BLOCK_SIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCK_SIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCK_SIZE));

            g.setFont(FONT[0]);
            int width = g.getFontMetrics().stringWidth("Congratulations!");
            g.setColor(Color.BLACK);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 2);
            g.setColor(Color.WHITE);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE);

            g.setFont(FONT[1]);
            width = g.getFontMetrics().stringWidth("You won the game");
            g.setColor(Color.BLACK);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 22);
            g.setColor(Color.WHITE);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 20);

            g.setFont(FONT[1]);
            width = g.getFontMetrics().stringWidth("Press any key to continue..");
            PRESS_KEY_TIMER.start();
            g.setColor(blink);
            g.drawString("Press SPACE to continue..", (Game.WINDOW_WIDTH / 2) - (width / 2), 6 * Game.BLOCK_SIZE);
        }

        if (hasItem) {
            g.setFont(FONT[1]);
            int width = g.getFontMetrics().stringWidth(itemName + ": " + itemCount);

            g.setColor(Color.BLACK);
            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width - 10, 30);
            g.setColor(Color.WHITE);
            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width - 10, 28);
        }

        if (newMessage) {
            g.setFont(FONT[1]);

            g.setColor(Color.BLACK);
            g.drawString(message, 21, Game.WINDOW_HEIGHT - 28);

            g.setColor(Color.WHITE);
            g.drawString(message, 20, Game.WINDOW_HEIGHT - 30);
        }
    }

    public void setNewMessage(String s) {
        message = s;
        newMessage = true;
        MESSAGE_TIMER.start();
        System.out.println(message);
    }

    public void setNewMessage(boolean motivation) {
        if (motivation) {
            message = MOTIVATIONAL_MESSAGES.get(new Random().nextInt(MOTIVATIONAL_MESSAGES.size()));
            newMessage = true;
            MESSAGE_TIMER.start();
        }
    }

    public void setItem(boolean hasItem, String itemName, int count) {
        this.hasItem = hasItem;
        this.itemName = itemName;
        this.itemCount = count;
    }

    public void setItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    public void winGame() {
        this.winGame = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (newMessage) {
            newMessage = false;
            MESSAGE_TIMER.stop();
        }

        if (blink == none) {
            blink = Color.WHITE;
        } else {
            blink = none;
        }
    }

    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_SPACE:
                if (winGame) {
                    startNewGame = true;
                }
                break;
        }
    }

    public boolean isReady() {
        return startNewGame;
    }
}
