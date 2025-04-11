/*
 * NexusSpace.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a NexusSpace within the World of Legends of Valor.
 * Extends Space and overrides various methods.
 */
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
        // if (row == 0) {
        //     System.out.println(Utility.GREEN + hero.getNickname() + " has reached the Monsters' Nexus! Heroes win!" + Utility.RESET);
        //     System.exit(0);
        // }
    }

    // if the hero leaves the nexus space
    @Override
    public void onExit(Hero hero){  
        System.out.println(Utility.CYAN + hero.getNickname() + " leaves the nexus space." + Utility.RESET);
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

