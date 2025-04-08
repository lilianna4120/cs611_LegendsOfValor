import java.util.List;

public abstract class Space {
    protected int row;
    protected int col;

    public Space(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract boolean isAccessible();
    public abstract void display();
    public abstract void onEnter(Hero hero);
    public abstract char getSymbol();

    public boolean isOccupiedbyHero(List<Hero> heroes) {
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
