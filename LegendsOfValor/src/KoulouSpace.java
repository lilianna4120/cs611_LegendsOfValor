
public class KoulouSpace extends Space {

    // public constructor for koulou space class
    public KoulouSpace(int row, int col) {
        super(row, col);
    }
    
    // koulou space is always accessible
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    // koulou space increases the strength of any hero who is inside them 
    @Override
    public void onEnter(Hero hero) {
        hero.applyBonus("strength", 10);
        System.out.println(Utility.BLUE + hero.getName() + " steps into a koulou and gains a strength bonus !" + Utility.RESET);
    }
    
    // display it on the world map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    // represented as K
    public char getSymbol() {
        return 'K';
    }
}
