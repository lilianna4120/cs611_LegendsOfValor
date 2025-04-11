public class PlainSpace extends Space {

    // public constructor for PlainSlace class
    public PlainSpace(int row, int col) {
        super(row, col);
    }
    
    // plain space is always accessible
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    // nothing happens if a character enters plain space
    @Override
    public void onEnter(Hero hero) {
        // Plain space has no special effect.
    }

    // nothing happens if a character exits plain space
    @Override
    public void onExit(Hero hero){
    }
    
    // display it on the world map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    // plain space is represented by letter P
    public char getSymbol() {
        return 'P';
    }
}