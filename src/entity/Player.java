package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private double xVel = 0;
    private double yVel = 0;
    private int abl1 = 0;
    private int abl2;
    GamePanel gp;
    KeyHandler keyH;

    public Player(int health, int coins, Weapon weapon, int points, int xPos, int yPos, int speed, BufferedImage image, double XVel, double YVel, int abl1, int abl2, GamePanel gp, KeyHandler keyH) {
        super(health, coins, weapon, points, xPos, yPos, speed, image);
        this.xVel = XVel;
        this.yVel = YVel;
        this.abl1 = abl1;
        this.abl2 = abl2;
        this.gp = gp;
        this.keyH = keyH;
    }

    public int update(int centre) {
        //player movement
        if (centre == 0) {
            this.setxPos(90);
            this.setyPos(50);
            centre = 1;
        }

        double speedMult = 1;
        if (keyH.shiftPressed) {
            speedMult = 2;
        }
        if (keyH.upPressed) {
            this.setyVel(this.getyVel() - this.getSpeed());
        }
        if (keyH.downPressed) {
            this.setyVel(this.getyVel() + this.getSpeed());
        }
        if (keyH.leftPressed) {
            this.setxVel(this.getxVel() - this.getSpeed());
        }
        if (keyH.rightPressed) {
            this.setxVel(this.getxVel() + this.getSpeed());
        }

        //limit max speed
        if (this.getxVel() > (this.getSpeed() * speedMult)){
            this.setxVel((this.getSpeed() * speedMult));
        }
        if (this.getxVel() < (0 - (this.getSpeed() * speedMult))){
            this.setxVel((0 - (this.getSpeed() * speedMult)));
        }
        if (this.getyVel() > (this.getSpeed() * speedMult)){
            this.setyVel((this.getSpeed() * speedMult));
        }
        if (this.getyVel() < (0 - (this.getSpeed() * speedMult))){
            this.setyVel((0 - (this.getSpeed() * speedMult)));
        }

        //creates glide effect
        this.setxVel(this.getxVel() * 0.93);
        this.setyVel(this.getyVel() * 0.93);

        //changing the positions by velocity implements the glide
        this.setxPos((this.getxPos() + this.getxVel()));
        this.setyPos((this.getyPos() + this.getyVel()));

        return centre;
    }

    public void draw(Graphics2D g2) {
        AffineTransform playerPos = AffineTransform.getTranslateInstance((int) this.getxPos(), (int) this.getyPos());
        AffineTransform playerScale = AffineTransform.getScaleInstance(3,3);
        g2.transform(playerScale);
        g2.drawImage(this.image, playerPos, null);

    }

    public double getxVel() { return xVel; }

    public void setxVel(double xVel) { this.xVel = xVel; }

    public double getyVel() { return yVel; }

    public void setyVel(double yVel) { this.yVel = yVel; }

    public int getAbl1() {
        return abl1;
    }

    public void setAbl1(int abl1) {
        this.abl1 = abl1;
    }

    public int getAbl2() {
        return abl2;
    }

    public void setAbl2(int abl2) {
        this.abl2 = abl2;
    }
}
