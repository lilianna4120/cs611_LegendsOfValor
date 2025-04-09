import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Game{
    private World world;
    private Party party;
    private Scanner scanner;
    private int round;
    private int monstersNum;

    public Game(){
        scanner = new Scanner(System.in);
        world = new World(8, 8);
        setupParty();
        monstersNum = 0;
        spawnMonsters();
        round = 0;
    }   

    public void start(){
        printInstructions();

        boolean quit = false;

        while(!quit){
            round++;
            System.out.println(Utility.YELLOW + "\nRound " + round + Utility.RESET);

            for(Hero h: party.getHeroes()){
                if(h.isAlive()){
                    world.display(party);
                    processHeroTurn(h);
                }
            }

            if(checkWinCondition()){
                quit = true;
                break;
            }

            for(Monster m: party.getMonsters()){
                if(m.isAlive()){
                    processMonsterTurn(m);
                }
            }

            if(checkWinCondition()){
                quit = true;
                break;
            }

            if(round%8 == 0){
                spawnMonsters();
            }

            // respawn heroes
            if(party.getHeroes().size() < 3){
                for(int i = 0; i < party.getHeroes().size(); i++){

                }
            }
        }

        System.out.println("\n");
        System.out.println("Thank you for playing ! ");
        // displayScoreSummary();
        System.out.println("Bye ! ");
    }

    public void setupParty() {
        HeroLoader heroLoader = new HeroLoader();
        List<Hero> allHeroes = heroLoader.loadAllHeroes();
    
        System.out.println(Utility.YELLOW + "Available Heroes: " + Utility.RESET);
        for (int i = 0; i < allHeroes.size(); i++) {
            Hero h = allHeroes.get(i);
            System.out.println("[" + i + "] " + h.getName() + " - " + h.getClass().getSimpleName());
            System.out.println("     Level: " + h.level);
            System.out.println("     EXP: " + h.experience);
            System.out.println("     HP: " + h.hp + "/" + h.getMaxHP());
            System.out.println("     MP: " + h.mp + "/" + h.getMaxMP());
            System.out.println("     Strength: " + h.strength);
            System.out.println("     Dexterity: " + h.dexterity);
            System.out.println("     Agility: " + h.agility);
            System.out.println("     Gold: " + h.gold);
            System.out.println("----------------------");
        }

        System.out.println("Welcome to Legends of Valor!");
        System.out.println("Instructions are to follow, but");
        System.out.println("You'll now choose 3 of the above heroes to add to your party.");
        
        party = new Party();
        Scanner scanner = new Scanner(System.in);
        int heroCount = 0;
        ArrayList<Integer> chosenHeroes = new ArrayList<>();
        while (heroCount < 3) { 
            int choice = 0;
            while (true) {
                System.out.print("Select hero index to add to your party: ");
                if (!scanner.hasNextInt()) {
                    System.out.println(Utility.RED + "Invalid input; Please enter an integer." + Utility.RESET);
                    scanner.next();
                    continue;
                }
                choice = scanner.nextInt();

                if (choice < 0 || choice > 17) {
                    System.out.println("Invalid choice; Please enter a valid hero index.");
                    continue;
                }

                if(chosenHeroes.contains(choice) ) {
                    System.out.println("Invalid choice; You have already selected this hero.");
                    continue;
                }
                break;
            }

            chosenHeroes.add(choice);
            Hero chosenHero = allHeroes.get(choice);
            String heroNickname = "H" + (heroCount+1);
            // System.out.print("Assign lane (1, 2, or 3) for " + chosenHero.getName() + ": ");
            // int lane = Integer.parseInt(scanner.nextLine().trim());
            // if (lane < 1 || lane > 3) {
            //     System.out.println("Invalid lane. Try again.");
            //     continue;
            // }

            int col = 0;
            if(heroCount == 0){
                col = 0;
            }else if(heroCount == 1){
                col = 3;
            }else if(heroCount == 2){
                col = 6;
            }

            chosenHero.setPosition(world.getHeight()-1, col);
            chosenHero.assignNickname(heroNickname);
            party.addHero(chosenHero);
            System.out.println(Utility.BLUE + chosenHero.getNickname() + " assigned ("  + (world.getHeight()-1) + "," + col + ")" + Utility.RESET);
            heroCount++;  
        }

        System.out.println(Utility.GREEN + "Finished setting up the party !" + Utility.RESET);
    }

    public void spawnMonsters(){
        int maxHeroLevel = 0;
        for(Hero h: party.getHeroes()){
            int level = h.getLevel();
            if(level > maxHeroLevel){
                maxHeroLevel = level;
            }
        }

        List<Monster> monstersList = MonsterLoader.generateThreeMonsters(maxHeroLevel);
        Monster m1 = monstersList.get(0);
        monstersNum++;
        String n1 = "M" + monstersNum;
        m1.assignNickname(n1);
        m1.setPosition(0, 1);

        Monster m2 = monstersList.get(1);
        monstersNum++;
        String n2 = "M" + monstersNum;
        m2.assignNickname(n2);
        m2.setPosition(0, 4);

        Monster m3 = monstersList.get(2);
        monstersNum++;
        String n3 = "M" + monstersNum;
        m3.assignNickname(n3);
        m3.setPosition(0, 7);

        party.addMonster(m1);
        party.addMonster(m2);
        party.addMonster(m3);
    }

    private void processHeroTurn(Hero hero){
        if(hero.getRow() == 7){
            while(true) {
                System.out.print(hero.getNickname() + " is in Heroes' Nexus space. Do you want to enter a market ? (Y/N) (Any invalid inputs will considered No) ");
                String response = scanner.nextLine();
                if(response.equalsIgnoreCase("y")){
                    System.out.println("Entering a market !");
                    MarketSpace ms= new MarketSpace(hero.getRow(), hero.getCol());
                    ms.enterMarket(hero);
                    world.display(party);
                    break;
                } else if(response.equalsIgnoreCase("n")) {
                    System.out.println("Not entering market.");
                    break;
                } else if(response.equalsIgnoreCase("i")) {
                    printInstructions();
                } else if(response.equalsIgnoreCase("stats")) {
                    // need to implement
                } else if(response.equalsIgnoreCase("inv")) {
                    // need to implement
                } else if(response.equalsIgnoreCase("map")) {
                    // need to implement
                } else if(response.equalsIgnoreCase("q")) {
                    System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                    System.exit(0);
                } else{
                    System.out.println(Utility.YELLOW + "Invalid input; you can not enter market now" + Utility.RESET);
                    break;
                }
            }
        }
        String action = "";
        while(true) {
            System.out.println("\n" + hero.getNickname() + "'s turn. Choose an action:");
            System.out.println("1. Move  2. Attack  3. Use Potion  4. Teleport  5. Recall  6. Remove Obstacle  7. Cast Spell 8. Change Weapon/Armor Q. Quit ");
            System.out.println("Enter STATS for characters and monsters statistics, INV for inventory, and MAP for world map !");
            action = scanner.nextLine();
        
            if(action.equalsIgnoreCase("q")){
                System.out.println(Utility.RED + "Quitting the game..." + Utility.RESET);
                System.exit(0);
            } else if(action.equalsIgnoreCase("i")) {
                Game.printInstructions();
            } else if(action.equalsIgnoreCase("stats")) {
                party.displayInfo();
                // need to implement
            } else if(action.equalsIgnoreCase("inv")) {
                
                System.out.println(Utility.YELLOW + hero.getNickname() + "'s Inventory: " + Utility.RESET);
                if(hero.inventory.size() == 0){
                    System.out.println(hero.getNickname() + "'s inventory is empty !");
                }
                for(int j = 0; j < hero.inventory.size(); j++){
                    System.out.println(Utility.GREEN + "  [" + j + "] " + hero.inventory.get(j).getName() + Utility.RESET);
                }


                // uncomment if we want to let them equit item in the inventory !

                // System.out.println(Utility.YELLOW + "Enter item index to equip item or -1 to skip: " + Utility.RESET);
                // int indexInt = 0;
                // while(true){
                //     String indexStr = scanner.nextLine();
                //     try{
                //         indexInt = Integer.parseInt(indexStr);
                //         if(indexInt == -1){
                //             break;
                //         }
                //         if(indexInt >= 0 && indexInt < hero.inventory.size()){
                //             break;
                //         }else{
                //             System.out.println(Utility.YELLOW + "Invalid index; Enter a number between 0 and " + (hero.inventory.size() - 1) + ", or -1 to skip." + Utility.RESET);
                //         }
                //     } catch(NumberFormatException e){
                //         System.out.println(Utility.YELLOW + "Invalid input; Please enter a valid integer." + Utility.RESET);
                //     }
                // }

                // if(indexInt != -1){
                //     Item item = hero.inventory.get(indexInt);
                //     hero.equipItem(item);
                // }
                // need to implement
            } else if(action.equalsIgnoreCase("map")) {
                System.out.println(Utility.CYAN + "Printing The World Map: " + Utility.RESET);
                world.display(party);
                // need to implement
            } else {
                break;
            }
        }

        switch(action){
            case "1":
                heroMove(hero);
                break;
            case "2":
                heroAttack(hero);
                break;
            case "3":
                heroUsePotion(hero);
                break;
            case "4":
                heroTeleport(hero);
                break;
            case "5":
                hero.recall(world.getHeight()-1);
                break;
            case "6":
                System.out.print("Enter direction (W/A/S/D) to remove obstacle: ");
                String direction = scanner.nextLine().trim();
                boolean removed = MovementUtil.removeObstacle(hero, direction, world);
                if (!removed) {
                    System.out.println("Could not remove obstacle. Turn skipped.");
                }
                break;
            case "7":
              heroCastSpell(hero);
              break;
            case "8":
              heroChangeWeaponOrArmor(hero);
              break;
            default:
                System.out.println("Invalid action; Your turn has been skipped.");
                break;
        }
    }

    private void heroMove(Hero hero){
        System.out.print("Enter W/A/S/D to move: "); 
            String dir = scanner.nextLine().trim().toLowerCase();
            // int r = hero.getRow();
            // int c = hero.getCol();
            if(!MovementUtil.moveHero(hero, dir, party, world)){
                System.out.println(Utility.YELLOW + "You can't move there !" + Utility.RESET);
            }
    }

    private void heroAttack(Hero hero){
        Monster target = getMonsterInRange(hero);
        if(target == null){
            System.out.println("There's no monster in attack range !");
            return;
        }

        double damage = hero.attack();
        System.out.println(hero.getNickname() + " attacks " + target.getNickname() + " for " + damage + " damage.");
        target.takeDamage(damage);
        if(!target.isAlive()){
            System.out.println(target.getNickname() + " is defeated !");
        }
    }

    private void heroUsePotion(Hero hero){ 
        ArrayList<Item> potions = new ArrayList<>();
        for (Item item : hero.getInventory()) {
            if (item instanceof Potion && item.isUsable()) {
                System.out.print("[" + potions.size() + "]");
                item.display();
                potions.add(item);
            }
        }

        if(potions.size() <= 0){
            System.out.println(Utility.YELLOW + "There's no potion " + hero.getNickname() + " can use ..." + Utility.RESET);
        }else{
            System.out.println("Enter the index of one of the above potions to use it (or type 'exit' to select none)");
       
            String input = scanner.nextLine();
            while(true) {
                if(input.equalsIgnoreCase("exit")) {
                    System.out.println("You've opted to exit. Turn is over");
                    break;
                }
                try {
                    int idx = Integer.parseInt(input);
                    if(idx >= 0 && idx < potions.size()){
                        hero.usePotion((Potion) potions.get(idx));
                        break;
                    } else {
                        System.out.println(Utility.RED + "Invalid potion index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer." + Utility.RESET);
                }
            }
        }
        
    }

    private void heroCastSpell(Hero hero){ 
        // TODO: 
        // add ability to choose spell to be used
        // add cause spell type based damage
        Monster monster = getMonsterInRange(hero);
        if(monster == null){
            System.out.println("There's no monster in attack range !");
            return;
        }

        Spell spellToCast = null;
        for (Item item : hero.getInventory()) {
            if (item instanceof Spell && item.isUsable()) {
                spellToCast = (Spell) item;
                break;
            }
        }
        if (spellToCast != null) {
            if (hero.mp >= spellToCast.getManaCost()) {
                hero.mp -= spellToCast.getManaCost();
                double spellDamage = spellToCast.getDamage() + (hero.dexterity / 10000.0) * spellToCast.getDamage();
                System.out.println(Utility.CYAN + hero.getNickname() + " casts " + spellToCast.getName() + " dealing " + spellDamage + " damage." + Utility.RESET);
                monster.takeDamage(spellDamage);
                if (!monster.isAlive()) {
                    System.out.println(Utility.GREEN + monster.getNickname() + " is defeated!"+ Utility.RESET);
                }
                spellToCast.use();
            } else {
                System.out.println(Utility.YELLOW + "Not enough MP to cast " + spellToCast.getName() + Utility.RESET);
            }
        } else {
            System.out.println(Utility.YELLOW + "No spell available." + Utility.RESET);
        }
    }

    private void heroChangeWeaponOrArmor(Hero hero){ 
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : hero.getInventory()) {
            if ((item instanceof Armor || item instanceof Weapon) && item.isUsable()) {
                System.out.print("[" + items.size() + "]");
                item.display();
                items.add(item);
            }
        }
        if(items.size() <= 0){
            System.out.println(Utility.YELLOW + "There's no Weapon or Armor " + hero.getNickname() + " can use ..." + Utility.RESET);
        }else{
            System.out.println("Enter the index of one of the above weapons or armors to use it (or type 'exit' to select none) ");
       
            String input = scanner.nextLine();
            while(true) {
                if(input.equalsIgnoreCase("exit")) {
                    System.out.println("You've opted to exit. Turn is over");
                    break;
                }
                try {
                    int idx = Integer.parseInt(input);
                    if(idx >= 0 && idx < items.size()){
                        hero.equipItem(items.get(idx));
                        break;
                    } else {
                        System.out.println(Utility.RED + "Invalid item index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer." + Utility.RESET);
                }
            }
        }
        
    }

    private Monster getMonsterInRange(Hero hero){
        for(Monster m: party.getMonsters()){
            if(m.isAlive() && isInRange(hero, m)){
                return m;
            }
        }
        return null;
    }

    private boolean isInRange(Hero hero, Monster monster){
        int row = Math.abs(hero.getRow() - monster.getRow());
        int col = Math.abs(hero.getCol() - monster.getCol());

        return (row <= 1 && col <= 1);
    }

    private void heroTeleport(Hero hero) {
        System.out.print("Enter target hero to teleport adjacent to: ");
        String targetNickname = scanner.nextLine().trim().toUpperCase();
        Hero target = party.getHerobyNickname(targetNickname);
        if (target == null) {
            System.out.println(Utility.YELLOW + "Target hero not found." + Utility.RESET);
            return;
        }
        if(targetNickname.equalsIgnoreCase(hero.getNickname()) || hero.getLane() == target.getLane()){
            System.out.println(Utility.YELLOW + "You have to move to different lane !" + Utility.RESET);
            return;
        }
        hero.teleport(hero, target, world, party);
    }

    private void processMonsterTurn(Monster monster){
        Hero target = getHeroInRange(monster);
        if (target != null) {
            double damage = monster.attack();
            System.out.println(monster.getNickname() + " attacks " + target.getNickname() + " for " + damage + " damage.");
            target.takeDamage(damage);
            if (!target.isAlive()) {
                System.out.println(target.getNickname() + " is defeated!");
                party.addDeadHero(target);
            }
        } else {
            boolean moved = MovementUtil.moveMonster(monster, "S", world);
            if (!moved) {
                System.out.println(monster.getNickname() + " cannot move south.");
            }
        }
    }

    private Hero getHeroInRange(Monster monster) {
        for (Hero hero : party.getHeroes()) {
            if (hero.isAlive() && Math.abs(hero.getRow() - monster.getRow()) <= 1 &&
                Math.abs(hero.getCol() - monster.getCol()) <= 1) {
                return hero;
            }
        }
        return null;
    }

    private boolean checkWinCondition() {
        // Check heroes:
        for (Hero hero : party.getHeroes()) {
            if (hero.getRow() == 0) {
                System.out.println(Utility.GREEN + hero.getNickname() + " has reached the Monsters' Nexus! Heroes win !" + Utility.RESET);
                return true;
            }
        }
        // Check monsters:
        for (Monster monster : party.getMonsters()) {
            if (monster.getRow() == world.getHeight() - 1) {
                System.out.println(Utility.RED + monster.getNickname() + " has reached the Heroes' Nexus! Heroes lose ..." + Utility.RESET);
                return true;
            }
        }
        if(party.getDeadHeros().size() == 3){
            System.out.println(Utility.YELLOW + "All the heroes are dead. Heroes lose ... " + Utility.RESET);
            return true;
        }
        return false;
    }
    
    public static void printInstructions(){
        System.out.println();
        System.out.println(Utility.YELLOW + "Instructions: \n");
        System.out.println("Make your way through the gameboard to the Monsters' Nexus.");
        System.out.println("Your goal to arrive at the Monsters' Nexus before they arrive at yours.");
        System.out.println("But you'll have to work your way through the board and battle monsters to get there.");
        System.out.println("Killed Heroes will respawn at their Nexus and killed Monsters will eventually be replaced with new ones.\n");
        System.out.println("Enter W to move up, A to move left, D to move right, and S to move down.");
        System.out.println("When on heroes' Nexus, which is N on the board, you'll have the option to buy or sell items at the Market.");
        System.out.println("You can't enter Inaccessible Tile, which is X on the board.\n");
        System.out.println("General Commands to be used at any point during gameplay:");
        System.out.println("Enter I to view instructions about the game.");
        System.out.println("Enter STATS to view hero and monster statistics.");
        System.out.println("Enter INV to see the inventory full of items you can equip for each hero.");
        System.out.println("Enter MAP to display the world map.");
        System.out.println("Enter Q to quit the game at any time." + Utility.RESET);
        System.out.println();
        System.out.println(Utility.GREEN + "Enter X to exit Instructions" + Utility.RESET);
        Scanner scan = new Scanner(System.in);
        while(true) {
            String response = scan.next();
            if(response.equalsIgnoreCase("x")) {
                break;
            } else if(response.equalsIgnoreCase("i")) {
                System.out.println("You're already viewing instructions");
            } else if(response.equalsIgnoreCase("stats")) {
                // need to implement
            } else if(response.equalsIgnoreCase("inv")) {
                // need to implement
            } else if(response.equalsIgnoreCase("map")) {
                // need to implement
            } else if(response.equalsIgnoreCase("q")) {
                System.out.println("Quitting the game ... ");
                System.exit(0);
            } else {
                System.out.println(Utility.RED + "Invalid resposne" + Utility.RESET);
            }
        }
    }

    private void displayScoreSummary(){
        System.out.println("\n" + Utility.CYAN + "Game Summary" + Utility.RESET);

        int totalGold = 0;
        int totalExp = 0;
        int maxLevel = 0;

        for(Hero hero: party.getHeroes()){
            totalGold = hero.gold;
            totalExp = hero.experience;
            if(hero.level > maxLevel){
                maxLevel = hero.level;
            }

            System.out.println(Utility.YELLOW + hero.name + Utility.RESET + " | Level: " + hero.level + " | EXP: " + hero.experience + " | Gold: " + hero.gold);
        }

        System.out.println("\n" + Utility.GREEN + "Total Gold: " + totalGold + Utility.RESET);
        System.out.println(Utility.GREEN + "Total EXP: " + totalExp + Utility.RESET);
        System.out.println(Utility.GREEN + "Highest Level Reached: " + maxLevel + Utility.RESET);
    }
}