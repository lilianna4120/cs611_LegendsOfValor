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

    // abstract methods
    public abstract boolean isAccessible();
    public abstract void display();
    public abstract void onEnter(Hero hero);
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
}
