
public class KoulouSpace extends Space {

    public KoulouSpace(int row, int col) {
        super(row, col);
    }
    
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    @Override
    public void onEnter(Hero hero) {
        hero.applyBonus("strength", 10);
        System.out.println(Utility.BLUE + hero.getName() + " steps into a koulou and gains a strength bonus !" + Utility.RESET);
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    public char getSymbol() {
        return 'K';
    }
}
