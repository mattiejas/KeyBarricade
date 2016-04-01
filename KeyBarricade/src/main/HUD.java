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

    private Timer t;

    private boolean newMessage;
    private boolean hasItem;

    private String message;
    private String itemName;

    private int itemCount;

    private ArrayList<String> motivationalMessages;
    private boolean winGame;

    public HUD() {
        t = new Timer(4000, this);
        font = new Font[2];
        motivationalMessages = new ArrayList<>();
    }

    public void init() {
        font[0] = new Font("Joystix Monospace", Font.PLAIN, 46);
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 18);

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
            g.setColor(new Color(0, 0, 0, 0.7f));
            g.fillRect(Game.BLOCKSIZE, Game.BLOCKSIZE, Game.WINDOW_WIDTH - (2 * Game.BLOCKSIZE), Game.WINDOW_HEIGHT - (2 * Game.BLOCKSIZE));
            
            int width = g.getFontMetrics().stringWidth("Congratulations!");
            g.setFont(font[0]);
            g.setColor(Color.BLACK);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 30);
            System.out.println((Game.WINDOW_WIDTH / 2) - (width / 2));
            
            g.setFont(font[0]);
            g.setColor(Color.WHITE);
            g.drawString("Congratulations!", (Game.WINDOW_WIDTH / 2) - (width / 2), 28);
        }
        
//        if (hasItem) {
//            int width = g.getFontMetrics().stringWidth(itemName + ": " + itemCount);
//            g.setFont(font[1]);
//            g.setColor(Color.BLACK);
//            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width / 2, 30);
//            g.setFont(font[1]);
//            g.setColor(Color.WHITE);
//            g.drawString(itemName + ": " + itemCount, Game.WINDOW_WIDTH - width / 2, 28);
//        }

        if (newMessage) {
            g.setFont(font[1]);
            g.setColor(Color.BLACK);
            g.drawString(message, 21, Game.WINDOW_HEIGHT - 28);
            g.setFont(font[1]);
            g.setColor(Color.WHITE);
            g.drawString(message, 20, Game.WINDOW_HEIGHT - 30);
        }
    }

    public void setNewMessage(String s) {
        message = s;
        newMessage = true;
        t.start();
        System.out.println(message);
    }

    public void setNewMessage(boolean motivation) {
        if (motivation) {
            message = motivationalMessages.get(new Random().nextInt(motivationalMessages.size()));
            newMessage = true;
            t.start();
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
            t.stop();
        }
    }
}
