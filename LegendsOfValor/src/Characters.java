/*
 * Characters.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Abstract class that represents a Character in the Game of Legends of Valor.
 */
public abstract class Characters {
    // private variables for characters
    protected String name;
    protected int level;
    protected double hp;

    // constructor of a characters class
    public Characters(String name, int level) {
        this.name = name;
        this.level = level;
        this.hp = getMaxHP();
    }

    // accessor methods for characters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getHp() {
        return hp;
    }

    public double getMaxHP() {
        return level * 100;
    }

    // mutator method for character
    public void setHp(double hp) {
        this.hp = hp;
    }

    // if hp is less than 0, the character is not alive; dead
    public boolean isAlive() {
        return hp > 0;
    }

    // reduce the hp by dmg; hp can't be negative
    public void takeDamage(double dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    // abstract methods for characters; to be initialized later
    public abstract double attack();
    public abstract void displayInfo();
}
