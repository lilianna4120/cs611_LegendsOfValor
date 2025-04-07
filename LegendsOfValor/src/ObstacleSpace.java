public class ObstacleSpace extends Space {
    private boolean cleared = false;

    public ObstacleSpace(int row, int col) {
        super(row, col);
    }
    
    @Override
    public boolean isAccessible() {
        cleared = true;
        return cleared; // Not accessible until cleared.
    }
    
    @Override
    public void onEnter(Hero hero) {
        if (!cleared) {
            System.out.println(hero.getName() + " encountered an obstacle at (" + row + "," + col + "). Clearing it now...");
            cleared = true;
        }
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    public char getSymbol() {
        return cleared ? 'P' : 'O'; // 'O' indicates an uncleared obstacle; once cleared, acts as a plain space.
    }
}

