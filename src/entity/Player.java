package entity;

public class Player extends Entity {
    private double xVel = 0;
    private double yVel = 0;
    private int abl1 = 0;
    private int abl2;

    public Player(int health, int coins, Weapon weapon, int points, int xPos, int yPos, int speed, double XVel, double YVel, int abl1, int abl2) {
        super(health, coins, weapon, points, xPos, yPos, speed);
        this.xVel = XVel;
        this.yVel = YVel;
        this.abl1 = abl1;
        this.abl2 = abl2;
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
