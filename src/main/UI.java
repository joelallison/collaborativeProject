package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


public class UI {
    Font font;

    public void draw(Graphics2D g2){
        InputStream is = getClass().getResourceAsStream("/assets/alagard.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        g2.setFont(font.deriveFont(Font.PLAIN, 10F));
        g2.setColor(new Color(0.803f, 0.745f, 0.675f));
        g2.drawString("sprint", 465, 308);
    }

}
