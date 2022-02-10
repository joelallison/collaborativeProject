package game;

public class Enemy extends Entity{
    private String species;
    private int difficulty;

    public Enemy(int health, int coins, Weapon weapon, int points, String species, int difficulty) {
        super(health, coins, weapon, points);
        this.species = species;
        this.difficulty = difficulty;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
