package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Weapon {
    private String weaponName;
    private int rpm;
    private int ammoPM;
    private int dmgPerHit;
    private int range;
    private int price;
    private int ability;
    private BufferedImage gunImg;
    GamePanel gp;

    public Weapon(String weaponName, int rpm, int ammoPM, int dmgPerHit, int range, int price, int ability) {
        this.weaponName = weaponName;
        this.rpm = rpm;
        this.ammoPM = ammoPM;
        this.dmgPerHit = dmgPerHit;
        this.range = range;
        this.price = price;
        this.ability = ability;
    }

    public void getGun() {
        try {
            if (this.weaponName.equals("machineGun")) {
                gunImg = ImageIO.read(getClass().getResourceAsStream("/assets/machineGun"));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void drawGun(Graphics2D g2) {
        AffineTransform gunPos = AffineTransform.getTranslateInstance((int) ((gp.originalTileSize * gp.maxScreenCol) / 2)-8, (int) ((gp.originalTileSize * gp.maxScreenRow) / 2)-8);
        g2.drawImage(gunImg, gunPos, null);
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getAmmoPM() {
        return ammoPM;
    }

    public void setAmmoPM(int ammoPM) {
        this.ammoPM = ammoPM;
    }

    public int getDmgPerHit() {
        return dmgPerHit;
    }

    public void setDmgPerHit(int dmgPerHit) {
        this.dmgPerHit = dmgPerHit;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }
}
