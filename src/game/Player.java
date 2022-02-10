package game;

public class Player extends Entity{
    private int abl1 = 0;
    private int abl2;

    public Player(int health, int coins, Weapon weapon, int points, int abl1, int abl2) {
        super(health, coins, weapon, points);
        this.abl1 = abl1;
        this.abl2 = abl2;
    }

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
