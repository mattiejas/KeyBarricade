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
    private Font[] fonts;

    private int width;

    public HelpState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {
        background = new BufferedImage[10][10];
        fonts = new Font[3];
        text = new ArrayList<>();
        
        fonts[0] = new Font("Joystix Monospace", Font.PLAIN, 50);
        fonts[1] = new Font("Joystix Monospace", Font.PLAIN, 51);
        fonts[2] = new Font("Joystix Monospace", Font.PLAIN, 19);

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
        
        text.add("\nControls:");
        text.add("- W, A, S, D op met de speler te lopen");
        text.add("- Spatie om een sleutel op te pakken");
        text.add("en om een barricade mee te openen");
        text.add("- Escape om terug naar het menu te gaan");        

        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                if (i == 1 && (x == 0 || x == 1 || x == 2 || x == 7 || x == 8 || x == 9)) {
                    background[i][x] = ResourceLoader.getSprite(Sprite.BARRICADE);
                } else {
                    background[i][x] = ResourceLoader.getSprite(Sprite.GROUND);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                g.drawImage(background[i][x], x * Game.BLOCKSIZE, i * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
            }
        }
        
        g.setFont(fonts[1]);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 115);
        
        g.setFont(fonts[0]);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 112);        

        g.setFont(fonts[2]);
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
            handler.setState(MENUSTATE);
        }
    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void keyTyped(int k) {

    }

}
