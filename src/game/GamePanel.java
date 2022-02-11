package game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final Color backgroundColor = new Color(1f, 0.5f, 0f);

    Thread gameThread;

     public GamePanel() {

         this.setPreferredSize(new Dimension(screenWidth, screenHeight));
         this.setBackground(backgroundColor);
         this.setDoubleBuffered(true);

    }

    public static void introScreen() {

        //loop to slowly increase
        //fori

    }
}
