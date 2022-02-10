package game;

public class Entity {
    private int health;
    private int coins;
    private int points;
    private Weapon weapon;

    public Entity(int health, int coins, Weapon weapon, int points) {
        this.health = health;
        this.coins = coins;
        this.weapon = weapon;
        this.points = points;
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
}
