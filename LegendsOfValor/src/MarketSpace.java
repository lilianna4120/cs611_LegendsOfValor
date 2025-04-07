// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
// import java.util.Scanner;

// public class MarketSpace extends Space {
//     private List<Item> itemsForSale;

//     public MarketSpace(int row, int col) {
//         super(row, col);
//         itemsForSale = new ArrayList<>();

//         List<Item> allItems = new ArrayList<>();
//         allItems.addAll(ArmorLoader.loadArmoryItems("Armory.txt"));
//         allItems.addAll(SpellLoader.loadSpellItems("FireSpells.txt", "Fire"));
//         allItems.addAll(SpellLoader.loadSpellItems("IceSpells.txt", "Ice"));
//         allItems.addAll(SpellLoader.loadSpellItems("LightningSpells.txt", "Lightning"));
//         allItems.addAll(PotionLoader.loadPotionItems("Potions.txt"));
//         allItems.addAll(WeaponLoader.loadWeaponItems("Weaponry.txt"));
    
//         Random rand = new Random();
//         int itemsNum = allItems.size();
//         int itemCount = rand.nextInt(itemsNum-1) + 1;
//         for (int i = 0; i < itemCount; i++) {
//             Item randomItem = allItems.get(rand.nextInt(allItems.size()));
//             if(!itemsForSale.contains(randomItem)){
//                 itemsForSale.add(randomItem);
//             }else{
//                 Item newRandItem = allItems.get(rand.nextInt(allItems.size()));
//                 itemsForSale.add(newRandItem);
//             }
            
//         }
//     }

//     @Override
//     public boolean isAccessible() {
//         return true;
//     }

//     @Override
//     public void display() {
//         System.out.print("M "); 
//     }

//     public void enterMarket(Party party) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println(Utility.YELLOW + "Welcome to the Market!" + Utility.RESET);
//         boolean exit = false;
//         while (!exit) {
//             System.out.println("\nMarket Menu: 1. Buy  2. Sell  3. Repair  4. Exit");
//             String choice = scanner.nextLine();
//             if(choice.equalsIgnoreCase("q")){
//                 System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
//                 System.exit(0);
//             }
//             switch (choice) {
//                 case "1":
//                 showItems();
//                 System.out.print("Enter the index of the item to buy (or type 'exit' to cancel): ");
//                 String input = scanner.nextLine();
//                 if(input.equalsIgnoreCase("exit"))
//                     break;
                
//                 try {
//                     int idx = Integer.parseInt(input);
//                     if(idx >= 0 && idx < itemsForSale.size()){
//                         System.out.println();
//                         System.out.println("Item Details:");
//                         itemsForSale.get(idx).display();
//                         System.out.print("Enter the hero index who will buy the item (0 to " + (party.getHeroes().size()-1) + "): ");
                        
//                         String heroInput = scanner.nextLine();
//                         int heroIdx = Integer.parseInt(heroInput);
//                         if(heroIdx < 0 || heroIdx >= party.getHeroes().size()){
//                             System.out.println(Utility.RESET + "Invalid hero index." + Utility.RESET);
//                             break;
//                         }
                        
//                         Hero buyer = party.getHeroes().get(heroIdx);
//                         Item item = itemsForSale.get(idx);
//                         if(buyer.gold >= item.getPrice() && buyer.level >= item.getRequiredLevel()){
//                             buyer.gold -= item.getPrice();
//                             buyer.addItem(item);
//                             System.out.println(Utility.GREEN + buyer.getName() + " bought " + item.getName() + Utility.RESET);
//                         } else {
//                             System.out.println(Utility.YELLOW + "Not enough gold or your level is too low to buy this item." + Utility.RESET);
//                         }
//                     } else {
//                         System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
//                     }
//                 } catch(NumberFormatException e) {
//                     System.out.println(Utility.RED + "Invalid input. Please enter a valid integer." + Utility.RESET);
//                 }
//                     break;
//                 case "2":
//                     System.out.print("Enter the hero index to sell an item (0 to " + (party.getHeroes().size()-1) + "): ");
//                     String heroInput = scanner.nextLine();
//                     try {
//                         int heroIdx = Integer.parseInt(heroInput);
//                         if(heroIdx < 0 || heroIdx >= party.getHeroes().size()){
//                             System.out.println("Invalid hero index.");
//                             break;
//                         }
//                         Hero seller = party.getHeroes().get(heroIdx);
//                         System.out.println(seller.getName() + "'s Inventory: ");
//                         for (int i = 0; i < seller.getInventory().size(); i++){
//                             System.out.println("[" + i + "] " + seller.getInventory().get(i).getName());
//                         }
//                         System.out.print("Enter the index of the item to sell: ");
//                         String sellInput = scanner.nextLine();
//                         try {
//                             int sellIdx = Integer.parseInt(sellInput);
//                             if(sellIdx >= 0 && sellIdx < seller.getInventory().size()){
//                                 Item sellItem = seller.getInventory().remove(sellIdx);
//                                 int salePrice = sellItem.getPrice() / 2;
//                                 seller.gold += salePrice;
//                                 // Optionally, add the sold item to the market's inventory.
//                                 itemsForSale.add(sellItem);
//                                 System.out.println(Utility.GREEN + seller.getName() + " sold " + sellItem.getName() + " for " + salePrice + " gold." + Utility.RESET);
//                             } else {
//                                 System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
//                             }
//                         } catch(NumberFormatException e) {
//                             System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the item index." + Utility.RESET);
//                         }
//                     } catch(NumberFormatException e) {
//                         System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the hero index." + Utility.RESET);
//                     }
//                     break;
//                 case "3":
//                     System.out.print("Enter the hero index for repair: ");
//                     String heroIndex = scanner.nextLine();
//                     try {
//                         int hIdx = Integer.parseInt(heroIndex);
//                         if(hIdx < 0 || hIdx >= party.getHeroes().size()){
//                             System.out.println("Invalid hero index.");
//                             break;
//                         }
//                         Hero repairHero = party.getHeroes().get(hIdx);
//                         System.out.println(repairHero.getName() + "'s Inventory: ");
//                         for (int i = 0; i < repairHero.getInventory().size(); i++){
//                             System.out.println("[" + i + "] " + repairHero.getInventory().get(i).getName() 
//                                 + " (" + repairHero.getInventory().get(i).getUses() + " uses left)");
//                         }
//                         System.out.print("Enter the index of the item to repair: ");
//                         String itemInput = scanner.nextLine();
//                         try {
//                             int repIdx = Integer.parseInt(itemInput);
//                             if(repIdx >= 0 && repIdx < repairHero.getInventory().size()){
//                                 Item repItem = repairHero.getInventory().get(repIdx);
//                                 int repairCost = repItem.getPrice() / 2;
//                                 if(repairHero.gold >= repairCost) {
//                                     repairHero.gold -= repairCost;
//                                     repItem.repair();
//                                     System.out.println(Utility.GREEN + repItem.getName() + " has been repaired for " + repairCost + " gold." + Utility.RESET);
//                                 } else {
//                                     System.out.println("Not enough gold to repair.");
//                                 }
//                             } else {
//                                 System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
//                             }
//                         } catch(NumberFormatException e) {
//                             System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the item index." + Utility.RESET);
//                         }
//                     } catch(NumberFormatException e) {
//                         System.out.println(Utility.RED + "Invalid input. Please enter a valid integer for the hero index." + Utility.RESET);
//                     }
//                     break;
//                 case "4":
//                     exit = true;
//                     break;
//                 default:
//                     System.out.println(Utility.RED + "Invalid input; Please try again." + Utility.RESET);
//                     break;
//             }
//         }
//     }

//     public void showItems() {
//         System.out.println("\nItems available in this market: ");
//         for (int i = 0; i < itemsForSale.size(); i++) {
//             System.out.println("[" + i + "]");
//             itemsForSale.get(i).display();
//         }
//     }
// }
