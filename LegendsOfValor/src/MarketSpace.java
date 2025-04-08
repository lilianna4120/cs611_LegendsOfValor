import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MarketSpace {
    private List<Item> itemsForSale;

    public MarketSpace(int row, int col) {
        itemsForSale = new ArrayList<>();
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(ArmorLoader.loadArmoryItems("LegendsOfValor/src/Armory.txt"));
        allItems.addAll(SpellLoader.loadSpellItems("LegendsOfValor/src/FireSpells.txt", "Fire"));
        allItems.addAll(SpellLoader.loadSpellItems("LegendsOfValor/src/IceSpells.txt", "Ice"));
        allItems.addAll(SpellLoader.loadSpellItems("LegendsOfValor/src/LightningSpells.txt", "Lightning"));
        allItems.addAll(PotionLoader.loadPotionItems("LegendsOfValor/src/Potions.txt"));
        allItems.addAll(WeaponLoader.loadWeaponItems("LegendsOfValor/src/Weaponry.txt"));
    
        Random rand = new Random();
        int itemsNum = allItems.size();
        int itemCount = rand.nextInt(itemsNum-1) + 1;
        for (int i = 0; i < itemCount; i++) {
            Item randomItem = allItems.get(rand.nextInt(allItems.size()));
            if(!itemsForSale.contains(randomItem)){
                itemsForSale.add(randomItem);
            }else{
                Item newRandItem = allItems.get(rand.nextInt(allItems.size()));
                itemsForSale.add(newRandItem);
            }
            
        }
    }

    public void enterMarket(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Utility.YELLOW + "Welcome to the Market!" + Utility.RESET);
        boolean exit = false;
        while (!exit) {
            String choice = "";
            while(true) {
                System.out.println("\nMarket Menu: 1. Buy  2. Sell  3. Repair  4. Exit");
                choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("q")){
                    System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
                    System.exit(0);
                } else if(choice.equalsIgnoreCase("i")) {
                    Game.printInstructions();
                } else if(choice.equalsIgnoreCase("stats")) {
                    // need to implement
                } else if(choice.equalsIgnoreCase("inv")) {
                    // need to implement
                } else if(choice.equalsIgnoreCase("map")) {
                    // need to implement
                } else if(choice.equalsIgnoreCase("q")) {
                    System.out.println("Quitting the game ... ");
                    System.exit(0);
                } else {
                    break;
                }
            }
            switch (choice) {
                case "1":
                showItems();
                String input = "";
                while(true) {
                    System.out.print("Enter the index of the item to buy (or type 'exit' to cancel): ");
                    input = scanner.nextLine();
                    if(input.equalsIgnoreCase("exit")) {
                        return;
                    } else if(input.equalsIgnoreCase("q")){
                        System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
                        System.exit(0);
                    } else if(input.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(input.equalsIgnoreCase("stats")) {
                        // need to implement
                    } else if(input.equalsIgnoreCase("inv")) {
                        // need to implement
                    } else if(input.equalsIgnoreCase("map")) {
                        // need to implement
                    } else {
                        break;
                    }
                }
                
                try {
                    int idx = Integer.parseInt(input);
                    if(idx >= 0 && idx < itemsForSale.size()){
                        System.out.println();
                        System.out.println("Item Details:");
                        itemsForSale.get(idx).display();
                        
                        Item item = itemsForSale.get(idx);
                        if(hero.gold >= item.getPrice() && hero.level >= item.getRequiredLevel()){
                            hero.gold -= item.getPrice();
                            hero.addItem(item);
                            System.out.println(Utility.GREEN + hero.getName() + " bought " + item.getName() + Utility.RESET);
                        } else {
                            System.out.println(Utility.YELLOW + "Not enough gold or your level is too low to buy this item." + Utility.RESET);
                        }
                    } else {
                        System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer." + Utility.RESET);
                }
                    break;
                case "2":
                // TODO:
                // need to print inventory
                String sellInput = "";
                while(true) {
                    System.out.print("Enter the index of the item to sell (or type 'exit' to cancel): ");
                    sellInput = scanner.nextLine();
                    if(sellInput.equalsIgnoreCase("exit")) {
                        return;
                    } else if(sellInput.equalsIgnoreCase("q")){
                        System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
                        System.exit(0);
                    } else if(sellInput.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(sellInput.equalsIgnoreCase("stats")) {
                        // need to implement
                    } else if(sellInput.equalsIgnoreCase("inv")) {
                        // need to implement
                    } else if(sellInput.equalsIgnoreCase("map")) {
                        // need to implement
                    } else {
                        break;
                    }
                }
                try {
                    int sellIdx = Integer.parseInt(sellInput);
                    if(sellIdx >= 0 && sellIdx < hero.getInventory().size()){
                        Item sellItem = hero.getInventory().remove(sellIdx);
                        int salePrice = sellItem.getPrice() / 2;
                        hero.gold += salePrice;
                        // Optionally, add the sold item to the market's inventory.
                        itemsForSale.add(sellItem);
                        System.out.println(Utility.GREEN + hero.getName() + " sold " + sellItem.getName() + " for " + salePrice + " gold." + Utility.RESET);
                    } else {
                        System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the item index." + Utility.RESET);
                }
                break;
                case "3":
                String itemInput = "";
                while(true) {
                    System.out.print("Enter the index of the item to repair (or type 'exit' to cancel): ");
                    itemInput = scanner.nextLine();
                    if(itemInput.equalsIgnoreCase("exit")) {
                        return;
                    } else if(itemInput.equalsIgnoreCase("q")){
                        System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
                        System.exit(0);
                    } else if(itemInput.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(itemInput.equalsIgnoreCase("stats")) {
                        // need to implement
                    } else if(itemInput.equalsIgnoreCase("inv")) {
                        // need to implement
                    } else if(itemInput.equalsIgnoreCase("map")) {
                        // need to implement
                    } else {
                        break;
                    }
                }
                try {
                    int repIdx = Integer.parseInt(itemInput);
                    if(repIdx >= 0 && repIdx < hero.getInventory().size()){
                        Item repItem = hero.getInventory().get(repIdx);
                        int repairCost = repItem.getPrice() / 2;
                        if(hero.gold >= repairCost) {
                            hero.gold -= repairCost;
                            repItem.repair();
                            System.out.println(Utility.GREEN + repItem.getName() + " has been repaired for " + repairCost + " gold." + Utility.RESET);
                        } else {
                            System.out.println("Not enough gold to repair.");
                        }
                    } else {
                        System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the item index." + Utility.RESET);
                }
                break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println(Utility.RED + "Invalid input; Please try again." + Utility.RESET);
                    break;
            }
        }
    }

    public void showItems() {
        System.out.println("\nItems available in this market: ");
        for (int i = 0; i < itemsForSale.size(); i++) {
            System.out.println("[" + i + "]");
            itemsForSale.get(i).display();
        }
    }
}
