public class ObstacleSpace extends Space {
    // private variable for obstacle space class
    private boolean cleared = false;

    // public constructor for obstacle space class
    public ObstacleSpace(int row, int col) {
        super(row, col);
    }
    
    // obstacle space is accessible when obstacle is cleraed
    @Override
    public boolean isAccessible() {
        return cleared;
    }
    
    // can't enter when there is an obstacle
    @Override
    public void onEnter(Hero hero) {
        if (!cleared) {
            System.out.println(Utility.YELLOW + hero.getNickname() + " encountered an obstacle at (" + row + "," + col + ")." + Utility.RESET);
        }
    }
    
    // display it on the map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }

    // when the player request to remove obstacle
    public void removeObstacle() {
        cleared = true;
        System.out.println("The obstacle at (" + row + "," + col + ") has been removed and is now a plain space.");
    }

    // hero can't leave obstacle space since it can't be entered
    @Override
    public void onExit(Hero hero){
        System.out.println(Utility.CYAN + hero.getNickname() + " leaves the obstacle space." + Utility.RESET);
    }
    
    // represented as O if there is an obstacle
    public char getSymbol() {
        if(cleared){
            return 'P';
        }else{
            return 'O';
        }
    }
}

