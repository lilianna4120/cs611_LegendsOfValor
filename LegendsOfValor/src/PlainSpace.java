public class PlainSpace extends Space {

    public PlainSpace(int row, int col) {
        super(row, col);
    }
    
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    @Override
    public void onEnter(Hero hero) {
        // Plain space has no special effect.
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    public char getSymbol() {
        return 'P';
    }
}