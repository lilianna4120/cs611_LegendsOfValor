public class BushSpace extends Space {
    public BushSpace(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void onEnter(Hero hero){
        hero.applyBonus("dexterity", 10);
        System.out.println(Utility.BLUE + hero.getNickname() + " went into a bush and gets a dexterity bonus !" + Utility.RESET);
    }

    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    public char getSymbol(){
        return 'B';
    }
}
