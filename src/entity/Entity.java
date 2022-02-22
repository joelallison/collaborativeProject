package entity;

import java.awt.image.BufferedImage;

public class Entity {
    private int health;
    private int coins;
    private int points;
    private Weapon weapon;
    private double xPos;
    private double yPos;
    private int speed;
    public BufferedImage image;

    public Entity(int health, int coins, Weapon weapon, int points, double xPos, double yPos, int speed, BufferedImage image) {
        this.health = health;
        this.coins = coins;
        this.weapon = weapon;
        this.points = points;
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.image = image;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getPoints() {
        return points;
    }

    public void setWeapon(int points) {
        this.points = points;
    }

    public double getxPos() { return xPos; }

    public void setxPos(double xPos) { this.xPos = xPos; }

    public double getyPos() { return yPos; }

    public void setyPos(double yPos) { this.yPos = yPos; }

    public int getSpeed() { return speed; }
}
