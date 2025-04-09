// import java.util.Scanner;
public class NexusSpace extends Space {

    public NexusSpace(int row, int col) {
        super(row, col);
    }
    
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    @Override
    public void onEnter(Hero hero) {
        if (row == 0) {
            // Monsters' Nexus: if a hero enters here, they win.
            System.out.println(Utility.GREEN + hero.getName() + " has reached the Monsters' Nexus! Heroes win!" + Utility.RESET);
            System.exit(0);
        }
    }
    
    @Override
    public void display() {
        System.out.print(getSymbol() + " ");
    }
    
    public char getSymbol() {
        return 'N';
    }
}

