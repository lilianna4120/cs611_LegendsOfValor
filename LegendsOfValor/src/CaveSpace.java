/*
 * CaveSpace.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a CaveSpace within the World of Legends of Valor.
 * Extends Space and overrides various methods.
 */
public class CaveSpace extends Space {
    // private variable for cavespacee class
    private int bonus = 10;

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
        hero.applyBonus("agility", bonus);
        System.out.println(Utility.BLUE + hero.getNickname() + " went into a cave and gets a agility bonus !" + Utility.RESET);
    }

    // the bonus is removed when the hero leaves the space
    @Override
    public void onExit(Hero hero){
        hero.removeBonus("agility", bonus);
        System.out.println(Utility.CYAN + hero.getNickname() + " leaves the cave and loses the agility bonus." + Utility.RESET);
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
