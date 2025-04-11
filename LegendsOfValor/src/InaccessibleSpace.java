/*
 * InaccessibleSpace.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents an InaccessibleSpace within the World of Legends of Valor.
 * Extends Space and overrides various methods.
 */
public class InaccessibleSpace extends Space {
    // public constructor for inaccessible space class
    public InaccessibleSpace(int row, int col) {
        super(row, col);
    }

    // is not accessible
    @Override
    public boolean isAccessible() {
        return false;
    }

    // hero can't enter but just in case
    @Override
    public void onEnter(Hero hero){
        System.out.println("This space is inaccessible !");
    }

    // hero can't enter so it can exit
    @Override
    public void onExit(Hero hero){
        System.out.println("Hero is leaving the inaccessible space ..?");
    }
    
    // display it on the world map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    // represented as X
    public char getSymbol(){
        return 'X';
    }
}
