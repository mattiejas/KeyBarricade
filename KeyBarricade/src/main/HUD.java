package main;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

    /**
     * Initialize a Head-up display that draws certain messages to the screen.
     */
    public HUD() {
        MESSAGE_TIMER = new Timer(4000, this);
        PRESS_KEY_TIMER = new Timer(700, this);

        FONT = new Font[3];
        MOTIVATIONAL_MESSAGES = new ArrayList<>();
    }

    public void init() {
        // Initialize fonts
        FONT[0] = new Font("Joystix Monospace", Font.PLAIN, 36);
        FONT[1] = new Font("Joystix Monospace", Font.PLAIN, 40);
        FONT[2] = new Font("Joystix Monospace", Font.PLAIN, 28);

        // New colors
        blink = Color.WHITE;
        none = new Color(0, 0, 0, 0f);

        // Add some cool messages
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
        // If the game is finished draw this..
        if (winGame) {
            hasItem = false;
            newMessage = false;

            g.setColor(new Color(0, 0, 0, 1f));
            g.fillRect(0, 0, Game.HORIZONTAL_AMOUNT * Game.BLOCK_SIZE, Game.VERTICAL_AMOUNT * Game.BLOCK_SIZE);
            g.setColor(new Color(0, 0, 0, 0.7f));
            g.fillRect((int) (Game.BLOCK_SIZE * 0.5), (int) (Game.BLOCK_SIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCK_SIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCK_SIZE));
            g.setColor(new Color(1, 1, 1, 0.5f));
            g.drawRect((int) (Game.BLOCK_SIZE * 0.5), (int) (Game.BLOCK_SIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCK_SIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCK_SIZE));

            g.setFont(FONT[0]);
            int width = g.getFontMetrics().stringWidth("Congratulations!");
            g.setColor(Color.BLACK);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 2);
            g.setColor(Color.WHITE);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE);

            g.setFont(FONT[2]);
            width = g.getFontMetrics().stringWidth("You won the game");
            g.setColor(Color.BLACK);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 25);
            g.setColor(Color.WHITE);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCK_SIZE + 23);

            g.setFont(FONT[2]);
            width = g.getFontMetrics().stringWidth("Press SPACE to continue..");
            PRESS_KEY_TIMER.start();
            g.setColor(blink);
            g.drawString("Press SPACE to continue..", (Game.WINDOW_WIDTH / 2) - (width / 2), 6 * Game.BLOCK_SIZE);
        }

        // If player picked up an item, draw this..
        if (hasItem) {

            g.setFont(FONT[1]);
            int height = g.getFontMetrics().getHeight() + 10;
            g.setFont(FONT[2]);
            int width = g.getFontMetrics().stringWidth(itemCount + "") + Game.BLOCK_SIZE / 2;

            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(Game.WINDOW_WIDTH - (width * 2 + 5), 0, width * 2 - 20, height + 10);
            g.setColor(new Color(1, 1, 1, 0.5f));
            g.drawRect(Game.WINDOW_WIDTH - (width * 2 + 5), -1, width * 2 - 20, height + 10);

            g.setColor(new Color(0, 0, 0, 0.5f));
            g.drawString(itemCount + "", Game.WINDOW_WIDTH - width + 2, height + 3);
            g.setColor(Color.WHITE);
            g.drawString(itemCount + "", Game.WINDOW_WIDTH - width, height);
            //g.drawImage(ResourceLoader.getSprite(Sprite.ITEM_KEY), Game.WINDOW_WIDTH - Game.BLOCK_SIZE * 2, 0, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);

            g.setFont(FONT[1]);
            g.setColor(new Color(0, 0, 0, 0.5f));
            width = width + g.getFontMetrics().stringWidth(itemName);
            g.drawString(itemName, Game.WINDOW_WIDTH - width + 3, height + 3);
//            g.setColor(Color.YELLOW);
//            g.drawString(itemName, Game.WINDOW_WIDTH - Game.BLOCK_SIZE * 2 - 3, g.getFontMetrics().getHeight() + 11);
            g.setColor(Color.WHITE);
            g.drawString(itemName, Game.WINDOW_WIDTH - width, height);
        }

        // If there is a message ready to be drawn, do it..
        if (newMessage) {
            g.setFont(FONT[1]);

            g.setColor(new Color(0, 0, 0, 0.5f));
            g.drawString(message, 23, Game.WINDOW_HEIGHT - 27);

            g.setColor(Color.WHITE);
            g.drawString(message, 20, Game.WINDOW_HEIGHT - 30);
        }
    }

    /**
     * Print a message to the screen.
     *
     * @param s String that needs to be printed
     */
    public void setNewMessage(String s) {
        message = s;
        newMessage = true;
        MESSAGE_TIMER.start();
    }

    /**
     * Print a random motivational message to the screen.
     */
    public void setNewMotivationMessage() {
        message = MOTIVATIONAL_MESSAGES.get(new Random().nextInt(MOTIVATIONAL_MESSAGES.size()));
        newMessage = true;
        MESSAGE_TIMER.start();
    }

    /**
     * Sets the item in the upper right corner of the screen.
     *
     * @param itemName the name of the item
     * @param count amount of items
     */
    public void setItem(String itemName, int count) {
        this.hasItem = true;
        this.itemName = itemName;
        this.itemCount = count;
    }

    /**
     * Clear the item in the upper right corner of the screen.
     */
    public void clearItem() {
        this.hasItem = false;
    }

    /**
     * Winning mode enabled.
     */
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

    /**
     * Checks whether the game is ready to start a new game.
     *
     * @return <code>true</code> if ready, otherwise <code>false</code>
     */
    public boolean isReady() {
        return startNewGame;
    }
}
