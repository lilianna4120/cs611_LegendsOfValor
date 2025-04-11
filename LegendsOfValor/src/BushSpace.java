/*
 * BushSpace.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a BushSpace within the World of Legends of Valor.
 * Extends Space and overrides various methods.
 */
public class BushSpace extends Space {
    // private variable for bush space class
    private int bonus = 10;

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
        hero.applyBonus("dexterity", bonus);
        System.out.println(Utility.BLUE + hero.getNickname() + " went into a bush and gets a dexterity bonus !" + Utility.RESET);
    }

    // the bonus is removed when the hero leaves the space
    @Override
    public void onExit(Hero hero){
        hero.removeBonus("dexterity", bonus);
        System.out.println(Utility.CYAN + hero.getNickname() + " leaves the bush and loses the dexterity bonus." + Utility.RESET);
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
