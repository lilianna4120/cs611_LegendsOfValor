/*
 * MarketSpace.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a MarketSpace within the World of Legends of Valor.
 * Does not extend Space in the way other ____Space classes do but is rather associated with a NexusSpace. 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MarketSpace {
    // private variable for market space class
    private List<Item> itemsForSale;

    // public constructor for market space class
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

    // when a hero enter a market space
    public void enterMarket(Hero hero, Party party, World world) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Utility.YELLOW + "Welcome to the Market!" + Utility.RESET);
        boolean exit = false;
        while (!exit) {
            String choice = "";
            while(true) {
                System.out.println("\nMarket Menu: 1. Buy  2. Sell  3. Repair  4. Exit");
                choice = scanner.nextLine();
                // player should be able to access instruction, characters' statistics, inventory, map or quit at anytime
                if(choice.equalsIgnoreCase("i")) {
                    Game.printInstructions();
                } else if(choice.equalsIgnoreCase("stats")) {
                    party.displayInfo();
                } else if(choice.equalsIgnoreCase("inv")) {
                    hero.printInventory();
                } else if(choice.equalsIgnoreCase("map")) {
                    world.printMap(party);
                } else if(choice.equalsIgnoreCase("q")) {
                    System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                    System.exit(0);
                } else {
                    break;
                }
            }
            switch (choice) {
                case "1": // buy
                showItems();
                String input = "";
                while(true) {
                    System.out.print("Enter the index of the item to buy (or type 'exit' to cancel): ");
                    input = scanner.nextLine();
                    if(input.equalsIgnoreCase("exit")) {
                        break;
                    // player should be able to access instruction, characters' statistics, inventory, map or quit at anytime
                    } else if(input.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(input.equalsIgnoreCase("stats")) {
                        party.displayInfo();
                    } else if(input.equalsIgnoreCase("inv")) {
                        hero.printInventory();
                    } else if(input.equalsIgnoreCase("map")) {
                        world.printMap(party);
                    } else if(input.equalsIgnoreCase("q")) {
                        System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                        System.exit(0);
                    } else {
                        break;
                    }
                }
                if(input.equalsIgnoreCase("exit")) {
                    break;
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
                            Item newItem;
                            if(item instanceof Armor) {
                                newItem = new Armor(item.getName(), item.getPrice(), item.getRequiredLevel(), item.getUses(), ((Armor)item).getDamageReduction());
                            } else if(item instanceof Potion) {
                                newItem = new Potion(item.getName(), item.getPrice(), item.getRequiredLevel(), item.getUses(), ((Potion)item).getEffectType(), ((Potion)item).getEffectAmount());
                            } else if(item instanceof Spell) {
                                newItem = new Spell(item.getName(), item.getPrice(), item.getRequiredLevel(), item.getUses(), ((Spell)item).getDamage(), ((Spell)item).getManaCost(), ((Spell)item).getSpellType());
                            } else {
                                newItem = new Weapon(item.getName(), item.getPrice(), item.getRequiredLevel(), item.getUses(), ((Weapon)item).getDamage(), ((Weapon)item).getHandsRequired());
                            }
                            hero.addItem(newItem);
                            itemsForSale.remove(idx);
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
                case "2": // sell

                String sellInput = "";
                while(true) {
                    System.out.println(Utility.YELLOW + hero.getNickname() + "'s Inventory: " + Utility.RESET);
                    for(int j = 0; j < hero.getInventory().size(); j++){
                        System.out.println(Utility.GREEN + "  [" + j + "] " + hero.getInventory().get(j).getName() + Utility.RESET);
                    }
                    System.out.print("Enter the index of the item to sell (or type 'exit' to cancel): ");
                    sellInput = scanner.nextLine();
                    if(sellInput.equalsIgnoreCase("exit")) {
                        break;
                    // player should be able to access instruction, characters' statistics, inventory, map or quit at anytime
                    } else if(sellInput.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(sellInput.equalsIgnoreCase("stats")) {
                        party.displayInfo();
                    } else if(sellInput.equalsIgnoreCase("inv")) {
                        hero.printInventory();
                    } else if(sellInput.equalsIgnoreCase("map")) {
                        world.printMap(party);
                    } else if(sellInput.equalsIgnoreCase("q")) {
                        System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                        System.exit(0);
                    } else {
                        break;
                    }
                }
                if(sellInput.equalsIgnoreCase("exit")) {
                    break;
                }
                try {
                    int sellIdx = Integer.parseInt(sellInput);
                    if(sellIdx >= 0 && sellIdx < hero.getInventory().size()){
                        Item sellItem = hero.getInventory().remove(sellIdx);
                        int salePrice = sellItem.getPrice() / 2;
                        hero.gold += salePrice;
                        itemsForSale.add(sellItem);
                        System.out.println(Utility.GREEN + hero.getName() + " sold " + sellItem.getName() + " for " + salePrice + " gold." + Utility.RESET);
                    } else {
                        System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the item index." + Utility.RESET);
                }
                break;
                case "3": // repair
                String itemInput = "";
                while(true) {
                    System.out.println(Utility.YELLOW + hero.getNickname() + "'s Inventory: " + Utility.RESET);
                    for(int j = 0; j < hero.getInventory().size(); j++){
                        System.out.println(Utility.GREEN + "  [" + j + "] " + hero.getInventory().get(j).getName() + Utility.RESET);
                    }
                    System.out.print("Enter the index of the item to repair (or type 'exit' to cancel): ");
                    itemInput = scanner.nextLine();
                    if(itemInput.equalsIgnoreCase("exit")) {
                        break;
                    // player should be able to access instruction, characters' statistics, inventory, map or quit at anytime
                    } else if(itemInput.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(itemInput.equalsIgnoreCase("stats")) {
                        party.displayInfo();
                    } else if(itemInput.equalsIgnoreCase("inv")) {
                        hero.printInventory();
                    } else if(itemInput.equalsIgnoreCase("map")) {
                        world.printMap(party);
                    } else if(itemInput.equalsIgnoreCase("q")) {
                        System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                        System.exit(0);
                    } else {
                        break;
                    }
                }
                if(itemInput.equalsIgnoreCase("exit")) {
                    break;
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
                case "4": // exit
                    System.out.println("Exiting market !");
                    exit = true;
                    break;
                default:
                    System.out.println(Utility.RED + "Invalid input; Please try again." + Utility.RESET);
                    break;
            }
        }
    }

    // show itmes in the market
    public void showItems() {
        System.out.println("\nItems available in this market: ");
        for (int i = 0; i < itemsForSale.size(); i++) {
            System.out.println("[" + i + "]");
            itemsForSale.get(i).display();
        }
    }
}
