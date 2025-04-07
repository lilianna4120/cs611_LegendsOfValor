public class CaveSpace extends Space {
    public CaveSpace(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void onEnter(Hero hero){
        hero.applyBonus("agility", 10);
        System.out.println(hero.getName() + " went into a cave and gets a agility bonus !");
    }

    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    public char getSymbol(){
        return 'C';
    }
}
