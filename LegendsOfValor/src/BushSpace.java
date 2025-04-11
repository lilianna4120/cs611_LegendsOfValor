public class BushSpace extends Space {
    // constructor of bush space
    public BushSpace(int row, int col) {
        super(row, col);
    }

    // bush space is always accessible
    @Override
    public boolean isAccessible() {
        return true;
    }

    // hero's dexterity increases when they go to the bush space
    @Override
    public void onEnter(Hero hero){
        hero.applyBonus("dexterity", 10);
        System.out.println(Utility.BLUE + hero.getNickname() + " went into a bush and gets a dexterity bonus !" + Utility.RESET);
    }

    // display it on the world map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    // bush space is represented by letter B
    public char getSymbol(){
        return 'B';
    }
}
