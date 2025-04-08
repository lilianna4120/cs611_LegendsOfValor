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
            world.display(party);
            round++;
            System.out.println(Utility.YELLOW + "\nRound" + round + Utility.RESET);

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




            
        //         case "i":
        //             printInstructions();
        //             break;
        //         case "stats":
        //             party.displayInfo();
        //             break;
        //         case "map":
        //             world.display(party.getPosition());
        //             break;
        //         case "inv":
        //             for (int i = 0; i < party.getHeroes().size(); i++) {
        //                 Hero hero = party.getHeroes().get(i);
        //                 System.out.println(Utility.YELLOW + "[" + i + "]" + hero.getName() + "'s Inventory:" + Utility.RESET);
                        
        //                 for (int j = 0; j < hero.inventory.size(); j++) {
        //                     System.out.println(Utility.GREEN + "  [" + j + "] " + hero.inventory.get(j).getName() + Utility.RESET);
        //                 }
                        
        //                 int idx = 0;
        //                 while (true) {
        //                     System.out.println("Invalid Input; Enter index to equip item or -1 to skip:");
        //                     String index = scanner.nextLine();
        //                     try {
        //                         idx = Integer.parseInt(index);
        //                         if (idx == -1) {
        //                             break;
        //                         }
        //                         if (idx >= 0 && idx < hero.inventory.size()) {
        //                             break;
        //                         } else {
        //                             System.out.println("Invalid index; Enter a number between 0 and " + (hero.inventory.size() - 1) + ", or -1 to skip.");
        //                         }
        //                     } catch (NumberFormatException e) {
        //                         System.out.println("Invalid input; Please enter a valid integer.");
        //                     }
        //                 }
        //                 if (idx != -1) {
        //                     Item item = hero.inventory.get(idx);
        //                     if (item instanceof Potion) {
        //                         hero.usePotion((Potion)item);
        //                     } else {
        //                         hero.equipItem(idx);
        //                     }
        //                 }
        //             }
        //             break;
        //         case "m":
        //             if(world.getTile(party.getPosition()) instanceof MarketTile){
        //                 MarketSpace market = world.getMarketAt(party.getPosition());
        //                 market.enterMarket(party);
        //             }else{
        //                 System.out.println(Utility.RED + "You can only enter the market when you are on a market space !" + Utility.RESET);
        //             }
        //             break;
        //         case "q":
        //             quit = true;
        //             break;
        //         default:
        //             System.out.println(Utility.RED + "Invalid input; Enter W/A/S/D to move, I for instruction, STATS for characters' statistics, M for market, INV for inventory, MAP for the map, and Q to quit ! " + Utility.RESET);
        //             break;
        //     }
        }

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
            System.out.println(chosenHero.getNickname() + " assigned ("  + (world.getHeight()-1) + "," + col + ")");
            System.out.println("\n");
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
        System.out.println("\n" + hero.getNickname() + "'s turn. ");
        if(hero.getPosition()[0] == 7) {
            System.out.print("You are in Heroes' Nexus space. Do you want to enter a market ? (Y/N)");
            String response = scanner.nextLine();
            while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                System.out.println("Invalid response. Enter Y/N");
                response = scanner.nextLine();
            }
            if(response.equalsIgnoreCase("y")) {
                Space space = this.world.getSpace(hero.getPosition()[0], hero.getPosition()[1]);
                space.onEnter(hero);
            }
        }
        
        System.out.println("Choose an action:");
        System.out.println("1. Move  2. Attack  3. Use Potion  4. Teleport  5. Recall  6. Cast Spell 7. Change Weapon/Armor Q to quit ");
        String action = scanner.nextLine().trim().toLowerCase();
        
        if(action.equals("q")){
            System.out.println("Quitting the game ... ");
            System.exit(0);
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
                heroCastSpell(hero);
                break;
            case "7":
                heroChangeWeaponOrArmor(hero);
                break;
            default:
                System.out.println("Invalid action; Your turn has been skipped.");
                break;
        }
    }

    private void heroMove(Hero hero){

        System.out.print("Enter W/A/S/D to move !"); 
            String dir = scanner.nextLine().trim().toLowerCase();
            // int r = hero.getRow();
            // int c = hero.getCol();
            if(!MovementUtil.moveHero(hero, dir, party, world)){
                System.out.println(Utility.YELLOW + "You can't move there !" + Utility.RESET);
            }
            // switch(input){
            //     case "w":
            //         party.move(world.getWidth(), world.getHeight(), r-1, c, world);
            //         if(world.getTile(party.getPosition()) instanceof CommonTile){
            //             if(Dice.roll()){
            //                 System.out.println(Utility.YELLOW + "Battle begins ! " + Utility.RESET);
            //                 Battle battle = new Battle(party);
            //                 battle.startBattle();
            //                 if(party.isDefeated()){
            //                     System.out.println(Utility.RED + "Your heroes have been defeated. Game over." + Utility.RESET);
            //                     quit = true;
            //                 }
            //             }
            //         }
            //         break;
            //     case "a":
            //         party.move(world.getWidth(), world.getHeight(), r, c-1, world);
            //         if(world.getTile(party.getPosition()) instanceof CommonTile){
            //             if(Dice.roll()){
            //                 System.out.println(Utility.YELLOW + "Battle begins ! " + Utility.RESET);
            //                 Battle battle = new Battle(party);
            //                 battle.startBattle();
            //                 if(party.isDefeated()){
            //                     System.out.println(Utility.RED + "Your heroes have been defeated. Game over." + Utility.RESET);
            //                     quit = true;
            //                 }
            //             }
            //         }
            //         break;
            //     case "s":
            //         party.move(world.getWidth(), world.getHeight(), r+1, c, world);
            //         if(world.getTile(party.getPosition()) instanceof CommonTile){
            //             if(Dice.roll()){
            //                 System.out.println(Utility.RED + "Battle begins ! " + Utility.RESET);
            //                 Battle battle = new Battle(party);
            //                 battle.startBattle();
            //                 if(party.isDefeated()){
            //                     System.out.println(Utility.RED + "Your heroes have been defeated. Game over." + Utility.RESET);
            //                     quit = true;
            //                 }
            //             }
            //         }
            //         break;
            //     case "d":
            //         party.move(world.getWidth(), world.getHeight(), r, c+1, world);
            //         if(world.getTile(party.getPosition()) instanceof CommonTile){
            //             if(Dice.roll()){
            //                 System.out.println(Utility.YELLOW + "Battle begins ! " + Utility.RESET);
            //                 Battle battle = new Battle(party);
            //                 battle.startBattle();
            //                 if(party.isDefeated()){
            //                     System.out.println(Utility.RED + "Your heroes have been defeated. Game over." + Utility.RESET);
            //                     quit = true;
            //                 }
            //             }
            //         }
            //         break;
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
                System.out.println(Utility.CYAN + hero.getName() + " casts " + spellToCast.getName() + " dealing " + spellDamage + " damage." + Utility.RESET);
                monster.takeDamage(spellDamage);
                if (!monster.isAlive()) {
                    System.out.println(Utility.GREEN + monster.getName() + " is defeated!"+ Utility.RESET);
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
        String targetName = scanner.nextLine().trim();
        Hero target = party.getHerobyName(targetName);
        if (target == null) {
            System.out.println("Target hero not found.");
            return;
        }
        hero.teleport(target);
    }

    private void processMonsterTurn(Monster monster){
        Hero target = getHeroInRange(monster);
        if (target != null) {
            double damage = monster.attack();
            System.out.println(monster.getNickname() + " attacks " + target.getNickname() + " for " + damage + " damage.");
            target.takeDamage(damage);
            if (!target.isAlive()) {
                System.out.println(target.getNickname() + " is defeated!");
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
                System.out.println(Utility.GREEN + hero.getNickname() + " has reached the Monsters' Nexus! Heroes win!" + Utility.RESET);
                return true;
            }
        }
        // Check monsters:
        for (Monster monster : party.getMonsters()) {
            if (monster.getRow() == world.getHeight() - 1) {
                System.out.println(Utility.RED + monster.getNickname() + " has reached the Heroes' Nexus! Heroes lose!" + Utility.RESET);
                return true;
            }
        }
        return false;
    }
    
    private void printInstructions(){
        System.out.println();
        System.out.println(Utility.YELLOW + "Instructions: ");
        System.out.println("Enter W to move up, A to move left, D to move right, and S to move down.");
        System.out.println("Enter I to view instructions about the game.");
        System.out.println("Enter STATS to view hero and monster statistics.");
        System.out.println("When on heroes' Nexus, which is N on the board, you'll have the option to buy or sell items at the Market.");
        System.out.println("Enter INV to see the inventory full of items you can equip for each hero.");
        System.out.println("Enter MAP to display the world map.");
        System.out.println("You can't enter Inaccessible Tile, which is X on the board.");
        System.out.println("Press Q to quit the game at any time." + Utility.RESET);
        System.out.println();
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