package game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 21;
    final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final Color backgroundColor = new Color(0.804f, 0.745f, 0.675f);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int FPS = 60;

    Player player = new Player(1, 0, null, 100, 0, 0, 2, 0.0, 0.0, 0, 0);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(backgroundColor);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) { //every 1/60th of a second
                update();
                repaint();
                delta--;
            }


        }

    }

    public void update() {

        //player movement
        if (keyH.upPressed) {
            player.setYVel(player.getYVel() - player.getSpeed());
        }
        if (keyH.downPressed) {
            player.setYVel(player.getYVel() + player.getSpeed());
        }
        if (keyH.leftPressed) {
            player.setXVel(player.getXVel() - player.getSpeed());
        }
        if (keyH.rightPressed) {
            player.setXVel(player.getXVel() + player.getSpeed());
        }

        player.setXVel(player.getXVel() * 0.7);
        player.setYVel(player.getYVel() * 0.7);

        player.setxPos((int) (player.getxPos() + player.getXVel()));
        player.setyPos((int) (player.getyPos() + player.getYVel()));

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //'convert' Graphics to Graphics2D

        g2.setColor(new Color(0.933f, 0.60f, 0.612f));

        g2.fillRect(player.getxPos(), player.getyPos(), tileSize, tileSize);

        g2.dispose();

    }
}
