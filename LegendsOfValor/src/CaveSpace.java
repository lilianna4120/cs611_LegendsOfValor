public class CaveSpace extends Space {
    // constructor of cave space
    public CaveSpace(int row, int col) {
        super(row, col);
    }

    // cave space is always accessible
    @Override
    public boolean isAccessible() {
        return true;
    }

    // hero's agility increases when they go to the bush space
    @Override
    public void onEnter(Hero hero){
        hero.applyBonus("agility", 10);
        System.out.println(Utility.BLUE + hero.getNickname() + " went into a cave and gets a agility bonus !" + Utility.RESET);
    }

    // display it on the world map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    // bush space is represented by letter B
    public char getSymbol(){
        return 'C';
    }
}
