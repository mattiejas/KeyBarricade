/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import assets.ResourceLoader;
import assets.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import main.Game;

/**
 *
 * @author Ewoud
 */
public class HelpState extends GameState {

    private BufferedImage[][] backGround;

    private String title;
    private String[] text;

    private Font titleFont;
    private Font titleFont2;
    private Font textFont;
    private Font textFont2;

    public HelpState(GameStateHandler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 10; x++) {
                if (i == 1 && (x == 0 || x == 1 || x == 2 || x == 6 || x == 7 || x == 8 || x == 9)) {
                    g.drawImage(backGround[i][x], x * Game.BLOCKSIZE, i * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
                } else {
                    g.drawImage(backGround[i][x], x * Game.BLOCKSIZE, i * Game.BLOCKSIZE, Game.BLOCKSIZE, Game.BLOCKSIZE, null);
                }
            }
        }

        g.setFont(titleFont2);
        g.setColor(Color.BLACK);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - 115, 110);

        g.setFont(titleFont);
        g.setColor(Color.GREEN);
        g.drawString(title, Game.WINDOW_WIDTH / 2 - 113, 115);

        for (int i = 0; i < text.length; i++) {
            g.setColor(Color.BLACK);
            g.setFont(textFont2);
            g.drawString(text[i], 10, 160 + (i * 30));

            g.setColor(Color.GREEN);
            g.setFont(textFont);
            g.drawString(text[i], 9, 160 + (i * 30) + 1);
        }
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
        titleFont2 = new Font("Joystix Monospace", Font.PLAIN, 51);
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
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
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
