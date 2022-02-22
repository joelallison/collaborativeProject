package main;

import entity.Player;

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

    final Color backgroundColor = new Color(0.3109f, 0.3057f, 0.3834f);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int FPS = 60;

    Player player = new Player(1, 0, null, 100, 300, 300, 2, 5, 5, 0, 0); //spawns in with slight velocity, nice effect

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
            player.setyVel(player.getyVel() - player.getSpeed());
        }
        if (keyH.downPressed) {
            player.setyVel(player.getyVel() + player.getSpeed());
        }
        if (keyH.leftPressed) {
            player.setxVel(player.getxVel() - player.getSpeed());
        }
        if (keyH.rightPressed) {
            player.setxVel(player.getxVel() + player.getSpeed());
        }

        //limit max speed
        if (player.getxVel() > (player.getSpeed() * 3.5)){
            player.setxVel((player.getSpeed() * 3.5));
        }
        if (player.getxVel() < (0 - (player.getSpeed() * 3.5))){
            player.setxVel((0 - (player.getSpeed() * 3.5)));
        }
        if (player.getyVel() > (player.getSpeed() * 3.5)){
            player.setyVel((player.getSpeed() * 3.5));
        }
        if (player.getyVel() < (0 - (player.getSpeed() * 3.5))){
            player.setyVel((0 - (player.getSpeed() * 3.5)));
        }

        //creates glide effect
        player.setxVel(player.getxVel() * 0.93);
        player.setyVel(player.getyVel() * 0.93);

        //changing the positions by velocity implements the glide
        player.setxPos((player.getxPos() + player.getxVel()));
        player.setyPos((player.getyPos() + player.getyVel()));

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //'convert' Graphics to Graphics2D

        g2.setColor(new Color(0.933f, 0.60f, 0.612f));

        g2.fillRect((int) player.getxPos(), (int) player.getyPos(), tileSize, tileSize);

        g2.dispose();

    }
}
