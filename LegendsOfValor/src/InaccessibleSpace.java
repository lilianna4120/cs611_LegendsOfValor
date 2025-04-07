public class InaccessibleSpace extends Space {
    public InaccessibleSpace(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isAccessible() {
        return false;
    }

    @Override
    public void onEnter(Hero hero){
        System.out.println("This space is inaccessible !");
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    public char getSymbol(){
        return 'X';
    }
}
