public class NexusSpace extends Space {
    // public constructor for nexus space
    public NexusSpace(int row, int col) {
        super(row, col);
    }
    
    // nexus space is always accessible
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    // if hero reaches monsters' nexus, they win
    @Override
    public void onEnter(Hero hero) {
        if (row == 0) {
            System.out.println(Utility.GREEN + hero.getNickname() + " has reached the Monsters' Nexus! Heroes win!" + Utility.RESET);
            System.exit(0);
        }
    }
    
    // display it on the map
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    // represented as M
    public char getSymbol() {
        return 'N';
    }
}

