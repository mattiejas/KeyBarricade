package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class HUD implements ActionListener {

    private Font font[];

    private Timer messageTimer;
    private Timer pressKeyTimer;

    private boolean newMessage;
    private boolean hasItem;
    private boolean startNewGame;

    private Color blink;
    private Color none;

    private String message;
    private String itemName;

    private int itemCount;

    private ArrayList<String> motivationalMessages;
    private boolean winGame;

    public HUD() {
        messageTimer = new Timer(4000, this);
        pressKeyTimer = new Timer(700, this);

        font = new Font[2];
        motivationalMessages = new ArrayList<>();
    }

    public void init() {
        font[0] = new Font("Joystix Monospace", Font.PLAIN, 36);
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 18);

        blink = Color.WHITE;
        none = new Color(0, 0, 0, 0f);

        motivationalMessages.add("Good work!");
        motivationalMessages.add("You're doing great, my friend.");
        motivationalMessages.add("Keep up the good work!");
        motivationalMessages.add("What the hell?");
        motivationalMessages.add("Oh my..");
        motivationalMessages.add("Really?");
        motivationalMessages.add("Very well then..");
        motivationalMessages.add("Uh, okay?");
        motivationalMessages.add("Great..");
    }

    public void render(Graphics2D g) {
        if (winGame) {
            hasItem = false;

            g.setColor(new Color(0, 0, 0, 1f));
            g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
            g.setColor(new Color(0, 0, 0, 0.7f));
            g.fillRect((int) (Game.BLOCKSIZE * 0.5), (int) (Game.BLOCKSIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCKSIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCKSIZE));
            g.setColor(Color.WHITE);
            g.drawRect((int) (Game.BLOCKSIZE * 0.5), (int) (Game.BLOCKSIZE * 0.5), Game.WINDOW_WIDTH - (1 * Game.BLOCKSIZE), Game.WINDOW_HEIGHT - (1 * Game.BLOCKSIZE));
            
            g.setFont(font[0]);
            int width = g.getFontMetrics().stringWidth("Congratulations!");
            g.setColor(Color.BLACK);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCKSIZE + 2);
            g.setColor(Color.WHITE);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCKSIZE);
           
            g.setFont(font[1]);
            width = g.getFontMetrics().stringWidth("You won the game");
            g.setColor(Color.BLACK);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCKSIZE + 22);
            g.setColor(Color.WHITE);
            g.drawString("You won the game", (Game.WINDOW_WIDTH / 2) - (width / 2), 3 * Game.BLOCKSIZE + 20);
            
            g.setFont(font[1]);
            width = g.getFontMetrics().stringWidth("Press any key to continue..");
            pressKeyTimer.start();
            g.setColor(blink);
            g.drawString("Press any key to continue..", (Game.WINDOW_WIDTH / 2) - (width / 2), 6 * Game.BLOCKSIZE);
        }

        if (hasItem) {
            g.setFont(font[1]);
            int width = g.getFontMetrics().stringWidth(itemName + ": " + itemCount);

            g.setColor(Color.BLACK);
            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width - 10, 30);
            g.setColor(Color.WHITE);
            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width - 10, 28);
        }

        if (newMessage) {
            g.setFont(font[1]);

            g.setColor(Color.BLACK);
            g.drawString(message, 21, Game.WINDOW_HEIGHT - 28);

            g.setColor(Color.WHITE);
            g.drawString(message, 20, Game.WINDOW_HEIGHT - 30);
        }
    }

    public void setNewMessage(String s) {
        message = s;
        newMessage = true;
        messageTimer.start();
        System.out.println(message);
    }

    public void setNewMessage(boolean motivation) {
        if (motivation) {
            message = motivationalMessages.get(new Random().nextInt(motivationalMessages.size()));
            newMessage = true;
            messageTimer.start();
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
            messageTimer.stop();
        }

        if (blink == none) {
            blink = Color.WHITE;
        } else {
            blink = none;
        }
    }

    public void keyPressed(int k) {
        if (winGame) {
            startNewGame = true;
        }
    }

    public boolean isReady() {
        return startNewGame;
    }
}
