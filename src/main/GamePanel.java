package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 31;
    public final int maxScreenRow = 20;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    //(21 * 48)x(14 * 48) --> 1008px by 672px

    final Color backgroundColor = Color.decode("#3c3b4a");

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    UI ui = new UI();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    int FPS = 60;
    int centre = 0;

    Player player = new Player(100, 0, null, 100, 0, 0, 2.5, 0, 0, this, keyH);

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
