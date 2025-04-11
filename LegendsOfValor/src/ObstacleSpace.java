public class ObstacleSpace extends Space {
    private boolean cleared = false;

    public ObstacleSpace(int row, int col) {
        super(row, col);
    }
    
    @Override
    public boolean isAccessible() {
        return cleared;
    }
    
    @Override
    public void onEnter(Hero hero) {
        if (!cleared) {
            System.out.println(hero.getNickname() + " encountered an obstacle at (" + row + "," + col + "). Clearing it now...");
            cleared = true;
        }
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    public void removeObstacle() {
        cleared = true;
        System.out.println("The obstacle at (" + row + "," + col + ") has been removed and is now a plain space.");
    }
    
    public char getSymbol() {
        if(cleared){
            return 'P';
        }else{
            return 'O';
        }
    }
}

