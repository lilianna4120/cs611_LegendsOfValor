/*
 * Space.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Abstract class that represents a Space within the World of Legends of Valor.
 */
import java.util.List;

public abstract class Space {
    // protected variables for space class
    protected int row;
    protected int col;

    // public constructor for space class
    public Space(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // abstract methods to be overriden by subclasses of Space
    public abstract boolean isAccessible();
    public abstract void display();
    public abstract void onEnter(Hero hero);
    public abstract void onExit(Hero hero);
    public abstract char getSymbol();

    // to check if the space is occupied by a hero so other heroes can't move there
    public boolean isNotOccupiedbyHero(List<Hero> heroes) {
        if (!isAccessible()) {
            return false;
        }

        for (Hero hero : heroes) {
            if (hero.getRow() == this.row && hero.getCol() == this.col && hero.isAlive()) {
                return false;
            }
        }

        return true;
    }
    // to check if the space is occupied by a monster so other monsters can't move there
    public boolean isNotOccupiedbyMonster(List<Monster> monsters) {
        if (!isAccessible()) {
            return false;
        }

        for (Monster monster : monsters) {
            if (monster.getRow() == this.row && monster.getCol() == this.col && monster.isAlive()) {
                return false;
            }
        }

        return true;
    }
}
