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
