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
    private int sprintMax = 120; //measured in 1/60th of a second.
    private int sprint;
    double speedMult;
    int delay = 0;


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
            if(sprintMax == sprint){
                delay++;
                if(delay >= sprintMax*2/3){
                    sprint--;
                    delay = 0;
                }
            }else{ sprint--; }
        }
    }

    public void draw(Graphics2D g2) {
        AffineTransform playerPos = AffineTransform.getTranslateInstance(((gp.originalTileSize * gp.maxScreenCol) / 2)-8,  ((gp.originalTileSize * gp.maxScreenRow) / 2)-8);
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


        //sprint bar outline
        g2.setColor(new Color(0.803f, 0.745f, 0.675f));
        g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(480, 294, 480, (294 - (sprintMax - sprint)*0.3)));

        //colours for sprint bar
        if((double)sprint/(double)sprintMax > 0.97) {
            //blackish red
            g2.setColor(new Color(0.38f/2, 0.20f/2, 0.20f/2));
        }else if((double)sprint/(double)sprintMax > 0.9){
            //dark red to blackish red
            g2.setColor(new Color(0.38f/2 + ((0.38f-0.38f/2)*((float)sprint/(float)sprintMax)/0.97f), 0.20f/2 + ((0.20f-0.20f/2)*((float)sprint/(float)sprintMax)/0.9f), 0.20f/2 + ((0.20f-0.20f/2)*((float)sprint/(float)sprintMax)/0.9f)));
        }else if ((double)sprint/(double)sprintMax > 0.8){
            //light red to dark red
            //light red -> 0.55f, 0.28f, 0.29f
            g2.setColor(new Color(0.38f + ((0.55f-0.38f)*((float)sprint/(float)sprintMax)/0.9f), 0.20f + ((0.28f-0.20f)*((float)sprint/(float)sprintMax)/0.9f), 0.20f + ((0.29f-0.20f)*((float)sprint/(float)sprintMax)/0.9f)));
        }else if ((double)sprint/(double)sprintMax > 0.35){
            //yellow to light red
            //yellow -> 0.623f, 0.404f, 0.243f
            g2.setColor(new Color(0.623f + ((0.55f-0.623f)*((float)sprint/(float)sprintMax)/0.8f), 0.404f + ((0.28f-0.404f)*((float)sprint/(float)sprintMax)/0.8f), 0.243f + ((0.29f-0.243f)*((float)sprint/(float)sprintMax)/0.8f)));
        }else{
            //green to yellow
            //green -> 0.31f, 0.33f, 0.31f
            g2.setColor(new Color(0.31f + ((0.623f-0.31f)*((float)sprint/(float)sprintMax)/0.35f), 0.33f + ((0.404f-0.31f)*((float)sprint/(float)sprintMax)/0.35f), 0.31f + ((0.243f-0.31f)*((float)sprint/(float)sprintMax)/0.35f)));
        }

        //main bar
        g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(480, 294, 480, (294 - (sprintMax - sprint)*0.3)));
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
