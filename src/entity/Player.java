package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.geom.Line2D;
import java.io.InputStream;

public class Player extends Entity {

    private String direction;
    private double xVel = 0;
    private double yVel = 0;
    private int abl1 = 0;
    private int abl2;
    GamePanel gp;
    KeyHandler keyH;
    boolean waitingForAbility = false;
    private int sprintMax = 360; //measured in 1/60th of a second.
    private int sprint;
    double speedMult;


    public Player(int health, int coins, Weapon weapon, int points, int xPos, int yPos, double speed, int abl1, int abl2, GamePanel gp, KeyHandler keyH) {
        super(health, coins, weapon, points, xPos, yPos, speed);
        this.abl1 = abl1;
        this.abl2 = abl2;
        this.gp = gp;
        this.keyH = keyH;

        direction = "right";
        getPlayerImage();
    }

    public void getPlayerImage() {

        try {

            right = ImageIO.read(getClass().getResourceAsStream("/assets/player_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/assets/player_left.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int update(int centre) {
        //player movement
        if (centre == 0) {
            this.setxPos(((gp.originalTileSize * gp.maxScreenCol) / 2)-8);
            this.setyPos(((gp.originalTileSize * gp.maxScreenRow) / 2)-8);
            centre = 1;
        }

        manageSprint();

        if (keyH.upPressed) {
            this.setyVel(this.getyVel() - this.getSpeed());
        }
        if (keyH.downPressed) {
            this.setyVel(this.getyVel() + this.getSpeed());
        }
        if (keyH.leftPressed) {
            direction = "left";
            this.setxVel(this.getxVel() - this.getSpeed());
        }
        if (keyH.rightPressed) {
            direction = "right";
            this.setxVel(this.getxVel() + this.getSpeed());
        }

        //ability
        if (keyH.abilityPressed) { waitingForAbility = true; }

        if (waitingForAbility){
            if(keyH.abilityPressed == false){
                doAbility();
                waitingForAbility = false;
            }
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

    public void doAbility() {

    }

    public void manageSprint() {
        speedMult = 1.5;

        //if maxsprint - sprint != 0, sprint.
        //otherwise, sprint - 1;
        if (keyH.shiftPressed) {
            if (sprintMax - sprint > 0) {
                speedMult = 2.5;
                sprint++;
            }
        }else if (sprint > 0){
            sprint--;
        }
    }

    public void draw(Graphics2D g2) {
        AffineTransform playerPos = AffineTransform.getTranslateInstance((int) ((gp.originalTileSize * gp.maxScreenCol) / 2)-8, (int) ((gp.originalTileSize * gp.maxScreenRow) / 2)-8);
        AffineTransform playerScale = AffineTransform.getScaleInstance(gp.scale,gp.scale);
        g2.transform(playerScale);

        BufferedImage image = null;

        switch(direction) {
            case "right":
                image = right;
                break;
            case "left":
                image = left;
                break;
        }

        g2.drawImage(image, playerPos, null); //draw player


        g2.setColor(new Color(0.773f, 0.643f, 0.42f));
        g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(317, 208, 317, (208 - (sprintMax - sprint)*0.1)));
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
