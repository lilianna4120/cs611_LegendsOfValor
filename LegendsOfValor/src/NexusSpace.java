import java.util.Scanner;
public class NexusSpace extends Space {
    Market market;

    public NexusSpace(int row, int col) {
        super(row, col);
        this.market = new Market();
    }
    
    @Override
    public boolean isAccessible() {
        return true;
    }
    
    @Override
    public void onEnter(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        // Check which Nexus this is:
        if (row == 7) {
            // This is the heroes' Nexus: trigger market functionality.
            // System.out.println(Utility.BLUE + hero.getName() + " has entered the Heroes' Nexus (Market)!" + Utility.RESET);
            //System.out.print("You are in Heroes' Nexus space. Do you want to enter a market ? (Y/N) (any other )");
            //String response = scanner.nextLine();
            // HERE !!!!
            // if(response.equalsIgnoreCase("y")){

            // }
            this.getMarket().enterMarket(hero);  // Assuming you have a Market class with a static method.
        } else if (row == 0) {
            // Monsters' Nexus: if a hero enters here, they win.
            System.out.println(Utility.GREEN + hero.getName() + " has reached the Monsters' Nexus! Heroes win!" + Utility.RESET);
            System.exit(0);
        }
    }
    
    @Override
    public void display() {
        System.out.print("N ");
    }
    
    public char getSymbol() {
        return 'N';
    }

    public Market getMarket() {
        return this.market;
    }
}

