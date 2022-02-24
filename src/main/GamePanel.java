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

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 21;
    final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final Color backgroundColor = new Color(0.3109f, 0.3057f, 0.3834f);

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int FPS = 60;

    BufferedImage playerImg = ImageIO.read(getClass().getResourceAsStream("/assets/player.png"));
    Player player = new Player(100, 0, null, 100, 300, 300, 2, playerImg, 5, 5, 0, 0); //spawns in with slight velocity, nice effect

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

        //player movement
        double speedMult = 3.5;
        if (keyH.shiftPressed) {
            speedMult = 7;
        }
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
        if (player.getxVel() > (player.getSpeed() * speedMult)){
            player.setxVel((player.getSpeed() * speedMult));
        }
        if (player.getxVel() < (0 - (player.getSpeed() * speedMult))){
            player.setxVel((0 - (player.getSpeed() * speedMult)));
        }
        if (player.getyVel() > (player.getSpeed() * speedMult)){
            player.setyVel((player.getSpeed() * speedMult));
        }
        if (player.getyVel() < (0 - (player.getSpeed() * speedMult))){
            player.setyVel((0 - (player.getSpeed() * speedMult)));
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

        tileM.draw(g2);

        AffineTransform playerPos = AffineTransform.getTranslateInstance((int) player.getxPos(), (int) player.getyPos());
        AffineTransform playerScale = AffineTransform.getScaleInstance(4.5,4.5);
        g2.transform(playerScale);
        g2.drawImage(playerImg, playerPos, null);

        g2.dispose();

    }
}
