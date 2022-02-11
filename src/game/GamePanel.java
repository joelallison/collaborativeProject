package game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 28;
    final int maxScreenRow = 21;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final Color backgroundColor = new Color(0.804f, 0.745f, 0.675f);

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

    public void startGameThread() {

         gameThread = new Thread(this);
         gameThread.start();
    }

    @Override
    public void run() {

         while(gameThread != null) {

             //System.out.println("The game loop is running.");

             update();

             repaint();
         }

    }

    public void update(){

    }
    public void paintComponent(Graphics g) {

         super.paintComponent(g);

         Graphics2D g2 = (Graphics2D)g; //'convert' Graphics to Graphics2D

        g2.setColor(new Color(0.933f, 0.60f, 0.612f));

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();

    }
}
