package entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{
    private String species;
    private int difficulty;

    public Enemy(int health, int coins, Weapon weapon, int points, int XPos, int YPos, int speed, String species, int difficulty) {
        super(health, coins, weapon, points, XPos, YPos, speed);
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
