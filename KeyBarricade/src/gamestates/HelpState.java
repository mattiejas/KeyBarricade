package gamestates;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;

public class HelpState extends GameState {

    private BufferedImage[][] background;

    private String title;
    private ArrayList<String> text;
    private Font[] font;

    private int width;

    public HelpState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {
        background = new BufferedImage[Game.HORIZONTAL_AMOUNT][Game.VERTICAL_AMOUNT];
        font = new Font[2];
        text = new ArrayList<>();
        
        font[0] = new Font("Joystix Monospace", Font.PLAIN, 50);
        font[1] = new Font("Joystix Monospace", Font.PLAIN, 18);

        title = "Help";
        text.add("Welkom bij het spel KeyBarricade.");
        text.add("Het is de bedoeling om met je speler");
        text.add("de finish te bereiken. Op weg naar");
        text.add("de finish zul je de juiste sleutels");
        text.add("moeten oppakken om de juiste");
        text.add("barricades te openen. Muren kunnen");
        text.add("niet geopent worden. De speler mag");
        text.add("maar 1 sleutel bij zich houden.");
        text.add("Als er een nieuwe sleutel wordt");
        text.add("gepakt zal de oude sleutel verdwijnen.");
        text.add("");
        text.add("Controls");
        text.add("W/A/S/D   walk around    ");
        text.add("SPACE     pick up keys   ");
        text.add("K         open barricades");
        text.add("ESC       pause the game ");        

        for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
            for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
                if (y == 1 && (x == 0 || x == 1 || x == 2 || x == Game.HORIZONTAL_AMOUNT - 3 || x == Game.HORIZONTAL_AMOUNT - 2 || x == Game.HORIZONTAL_AMOUNT - 1)) {
                    background[x][y] = ResourceLoader.getSprite(Sprite.BARRICADE);
                } else {
                    background[x][y] = ResourceLoader.getSprite(Sprite.GROUND);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int y = 0; y < Game.VERTICAL_AMOUNT; y++) {
            for (int x = 0; x < Game.HORIZONTAL_AMOUNT; x++) {
                g.drawImage(background[x][y], x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
            }
        }
        
        g.setFont(font[0]);
        g.setColor(Color.BLACK);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2 + 3, 115);
        
        g.setFont(font[0]);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 112);        

        g.setFont(font[1]);
        g.setColor(Color.WHITE);
        int spacing = g.getFontMetrics().getHeight();
        int j = (Game.WINDOW_HEIGHT / 2 - (spacing * text.size() / 2)) + 50;
        for (int i = 0; i < text.size(); i++, j += spacing) {
            width = g.getFontMetrics().stringWidth(text.get(i));
            g.drawString(text.get(i), Game.WINDOW_WIDTH / 2 - width / 2, j);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE || k == KeyEvent.VK_SPACE) {
            handler.setPreviousGameState();
        }
    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void keyTyped(int k) {

    }

}
