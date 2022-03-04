package main;

import entity.Player;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    //(21 * 48)x(14 * 48) --> 1008px by 672px

    final Color backgroundColor = new Color(0.3109f, 0.3057f, 0.3834f);

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    UI ui = new UI();
    Thread gameThread;

    int FPS = 60;
    int centre = 0;

    Player player = new Player(100, 0, null, 100, 300, 300, 4,0, 0, this, keyH);

    public GamePanel() throws IOException {

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
        centre = player.update(centre);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //'convert' Graphics to Graphics2D
        tileM.draw(g2, player);
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }
}
