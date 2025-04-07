import java.util.Scanner;

public class Market {
    public static void enterMarket(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Market, " + hero.getName() + "!");
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter 1 to Buy, 2 to Sell, or 0 to exit the market.");
            String choice = scanner.nextLine().trim();
            switch(choice) {
                case "1":
                    // Call your buy method.
                    buy(hero);
                    break;
                case "2":
                    // Call your sell method.
                    sell(hero);
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
    
    private static void buy(Hero hero) {
        // Implement buying logic:
        System.out.println("Buying items (not yet implemented)...");
    }
    
    private static void sell(Hero hero) {
        // Implement selling logic:
        System.out.println("Selling items (not yet implemented)...");
    }
}
