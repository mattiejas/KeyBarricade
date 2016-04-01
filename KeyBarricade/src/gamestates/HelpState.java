package gamestates;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import main.Game;

public class HelpState extends GameState {

    private BufferedImage[][] backGround;

    private String title;
    private String[] text;

    private Font titleFont;
    private Font titleFontShadow;
    private Font textFont;
    private Font textFont2;

    private int width, height;

    public HelpState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {
        backGround = new BufferedImage[10][10];
        text = new String[16];

        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                if (i == 1 && (x == 0 || x == 1 || x == 2 || x == 6 || x == 7 || x == 8 || x == 9)) {
                    backGround[i][x] = ResourceLoader.getSprite(Sprite.BARRICADE);
                } else {
                    backGround[i][x] = ResourceLoader.getSprite(Sprite.WALL);
                }
            }
        }

        titleFont = new Font("Joystix Monospace", Font.PLAIN, 50);
        titleFontShadow = new Font("Joystix Monospace", Font.PLAIN, 51);
        textFont = new Font("Joystix Monospace", Font.PLAIN, 19);
        textFont2 = new Font("Joystix Monospace", Font.PLAIN, 19);

        title = "Help";
        text[0] = "Welkom bij het spel KeyBarricade.";
        text[1] = "Het is de bedoeling om met je speler";
        text[2] = "de finish te bereiken. Op weg naar";
        text[3] = "de finish zul je de juiste sleutels";
        text[4] = "moeten oppakken om de juiste";
        text[5] = "barricades te openen. Muren kunnen";
        text[6] = "niet geopent worden. De speler mag";
        text[7] = "maar 1 sleutel bij zich houden.";
        text[8] = "Als er een nieuwe sleutel wordt";
        text[9] = "gepakt zal de oude sleutel verdwijnen";
        text[10] = "";
        text[11] = "Controls:";
        text[12] = "- W, A, S, D op met de speler te lopen";
        text[13] = "- Spatie om een sleutel op te pakken";
        text[14] = "en om een barricade mee te openen";
        text[15] = "- Escape om terug naar het menu te gaan";
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                g.drawImage(backGround[i][x], x * Game.BLOCKSIZE, i * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
            }
        }
        g.setFont(titleFontShadow);
        g.setColor(Color.DARK_GRAY);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 122);

        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - width / 2, 125);

        g.setFont(textFont);
        g.setColor(Color.WHITE);
        int spacing = g.getFontMetrics().getHeight();
        int j = (Game.WINDOW_HEIGHT / 2 - (spacing * text.length / 2)) + 50;
        for (int i = 0; i < text.length; i++, j += spacing) {
            width = g.getFontMetrics().stringWidth(text[i]);
            g.drawString(text[i], Game.WINDOW_WIDTH / 2 - width / 2, j);
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
