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

            // At the end of every round every hero that is still alive regains 10% of their hp and 10% of their mana.
            // When a hero dies, they respawn in their specific Nexus space at the start of the next round.
            for(Hero h: party.getHeroes()){
                if(h.isAlive()){
                    h.regains();
                }else{
                    h.respawn();
                }
            }

            ArrayList<Monster> killedMonsters = new ArrayList<>();
            for(Monster m: party.getMonsters()) {
                if(!m.isAlive()) {
                    for(Hero h: party.getHeroes()) {
                        h.gain(m);
                    }
                    killedMonsters.add(m);
                }
            }
            for(Monster m: killedMonsters) {
                party.removeMonster(m);
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

        System.out.println(Utility.PURPLE + "Welcome to Legends of Valor!" + Utility.RESET);
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
                break;
            }

            chosenHeroes.add(choice);
            Hero chosenHero;
            Hero template = allHeroes.get(choice);
            if(template instanceof Warrior) {
                chosenHero = new Warrior(template.getName(), (int)template.getMP(), (int)template.getStrength(), (int)template.getAgility(), (int)template.getDexterity(), template.getGold(), template.getExperience());
            } else if(template instanceof Paladin) {
                chosenHero = new Paladin(template.getName(), (int)template.getMP(), (int)template.getStrength(), (int)template.getAgility(), (int)template.getDexterity(), template.getGold(), template.getExperience());
            } else {
                chosenHero = new Sorcerer(template.getName(), (int)template.getMP(), (int)template.getStrength(), (int)template.getAgility(), (int)template.getDexterity(), template.getGold(), template.getExperience());
            }
            String heroNickname = "H" + (heroCount+1);

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
        for(int i = 0; i < 3; i++) {
            Monster chosenMonster;
            Monster template = monstersList.get(i);
            if(template instanceof Dragon) {
                chosenMonster = new Dragon(template.getName(), template.getLevel(), template.getDamage(), template.getDefense(), template.getDodge());
            } else if(template instanceof Exoskeleton) {
                chosenMonster = new Exoskeleton(template.getName(), template.getLevel(), template.getDamage(), template.getDefense(), template.getDodge());
            } else {
                chosenMonster = new Spirit(template.getName(), template.getLevel(), template.getDamage(), template.getDefense(), template.getDodge());
            }
            monstersNum ++;
            String n = "M" + monstersNum;
            chosenMonster.assignNickname(n);
            chosenMonster.setPosition(0, i*3 + 1);
            party.addMonster(chosenMonster);
        }
    }

    private void processHeroTurn(Hero hero){
        if(hero.getRow() == 7){
            while(true) {
                System.out.print(hero.getNickname() + " is in Heroes' Nexus space. Do you want to enter a market ? (Y/N) (Any invalid inputs will considered No) ");
                String response = scanner.nextLine();
                if(response.equalsIgnoreCase("y")){
                    System.out.println("Entering a market !");
                    MarketSpace ms= new MarketSpace(hero.getRow(), hero.getCol());
                    ms.enterMarket(hero, party, world);
                    world.display(party);
                    break;
                } else if(response.equalsIgnoreCase("n")) {
                    System.out.println("Not entering market.");
                    break;
                } else if(response.equalsIgnoreCase("i")) {
                    Game.printInstructions();
                } else if(response.equalsIgnoreCase("stats")) {
                    party.displayInfo();
                } else if(response.equalsIgnoreCase("inv")) {
                    hero.printInventory();
                } else if(response.equalsIgnoreCase("map")) {
                    world.printMap(party);
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
            action = scanner.nextLine();
        
            if(action.equalsIgnoreCase("i")) {
                Game.printInstructions();
            } else if(action.equalsIgnoreCase("stats")) {
                party.displayInfo();
            } else if(action.equalsIgnoreCase("inv")) {
                hero.printInventory();
            } else if(action.equalsIgnoreCase("map")) {
                world.printMap(party);
            } else if(action.equalsIgnoreCase("q")) {
                System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                System.exit(0);
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
                while(true) {
                    System.out.print("Enter direction (W/A/S/D) to remove obstacle: ");
                    String direction = scanner.nextLine().trim();
                    if(direction.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(direction.equalsIgnoreCase("stats")) {
                        party.displayInfo();
                    } else if(direction.equalsIgnoreCase("inv")) {
                        hero.printInventory();
                    } else if(direction.equalsIgnoreCase("map")) {
                        world.printMap(party);
                    } else if(direction.equalsIgnoreCase("q")) {
                        System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                        System.exit(0);
                    } else {
                        boolean removed = MovementUtil.removeObstacle(hero, direction, world);
                        if (!removed) {
                            System.out.println("Could not remove obstacle. Turn skipped.");
                        }
                        break;
                    }
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
        String dir = "";
        while(true) {
            System.out.print("Enter W/A/S/D to move: "); 
            dir = scanner.nextLine().trim().toLowerCase();
            if(dir.equalsIgnoreCase("i")) {
                Game.printInstructions();
            } else if(dir.equalsIgnoreCase("stats")) {
                party.displayInfo();
            } else if(dir.equalsIgnoreCase("inv")) {
                hero.printInventory();
            } else if(dir.equalsIgnoreCase("map")) {
                world.printMap(party);
            } else if(dir.equalsIgnoreCase("q")) {
                System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                System.exit(0);
            } else {
                break;
            }
        }
        
        // int r = hero.getRow();
        // int c = hero.getCol();
        if(!MovementUtil.moveHero(hero, dir, party, world)){
            System.out.println(Utility.YELLOW + "You can't move there ! Skipping turn." + Utility.RESET);
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

        while(true) {
            if(potions.size() <= 0){
                System.out.println(Utility.YELLOW + "There's no potion " + hero.getNickname() + " can use ..." + Utility.RESET);
                break;
            }

            System.out.println("Enter the index of one of the above potions to use it (or type 'exit' to select none)");
       
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) {
                System.out.println("You've opted to exit. Turn is over");
                break;
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
        // add spell type based damage
        Monster monster = getMonsterInRange(hero);
        if(monster == null){
            System.out.println("There's no monster in attack range !");
            return;
        }

        ArrayList<Item> spells = new ArrayList<>();
        for (Item item : hero.getInventory()) {
            if (item instanceof Spell && item.isUsable()) {
                System.out.print("[" + spells.size() + "]");
                item.display();
                spells.add(item);
            }
        }

        while(true) {
            if(spells.size() <= 0){
                System.out.println(Utility.YELLOW + "There's no spell " + hero.getNickname() + " can cast ..." + Utility.RESET);
                break;
            }
            System.out.println("Enter the index of one of the above spells to cast it (or type 'exit' to select none)");
       
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) {
                System.out.println("You've opted to exit. Turn is over");
                break;
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
                try {
                    int idx = Integer.parseInt(input);
                    if(idx >= 0 && idx < spells.size()){
                        Spell spell = (Spell)spells.get(idx);
                        if (hero.mp >= spell.getManaCost()) {
                            hero.mp -= spell.getManaCost();
                            double spellDamage = spell.getDamage() + (hero.dexterity / 10000.0) * spell.getDamage();
                            System.out.println(Utility.CYAN + hero.getNickname() + " casts " + spell.getName() + " dealing " + spellDamage + " damage." + Utility.RESET);
                            monster.takeDamage(spellDamage);
                            // take additional effect depending on spell type
                            if(spell.getSpellType().equalsIgnoreCase("Ice")){
                                monster.takeDamage(spellDamage*0.5);
                                System.out.println(Utility.BLUE + hero.getNickname() + " takes additional " + (spellDamage*0.5) + " attack damage to " + monster.getNickname() + " since it is Ice Spell !" + Utility.RESET);
                            }else if(spell.getSpellType().equalsIgnoreCase("Fire")){
                                monster.takeDefense(spellDamage*0.5);
                                System.out.println(Utility.BLUE + hero.getNickname() + " takes additional " + (spellDamage*0.5) + " defense damage to " + monster.getNickname() + " since it is Fire Spell !" + Utility.RESET);
                            }else if(spell.getSpellType().equalsIgnoreCase("Lightning")){
                                monster.takeDodgeChange(spellDamage*0.1);
                                System.out.println(Utility.BLUE + hero.getNickname() + " takes additional " + (spellDamage*0.1) + " dodge change damage to " + monster.getNickname() + " since it is Lightning Spell !" + Utility.RESET);
                            }
                            if (!monster.isAlive()) {
                                System.out.println(Utility.GREEN + monster.getNickname() + " is defeated!"+ Utility.RESET);
                            }
                            spell.use();
                        } else {
                            System.out.println(Utility.YELLOW + "Not enough MP to cast " + spell.getName() + Utility.RESET);
                        }
                        break;
                    } else {
                        System.out.println(Utility.RED + "Invalid spell index." + Utility.RESET);
                    }
                } catch(NumberFormatException e) {
                    System.out.println(Utility.RED + "Invalid input. Please enter a valid integer." + Utility.RESET);
                }
            }
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

        while(true) {
            if(items.size() <= 0){
                System.out.println(Utility.YELLOW + "There's no Weapon or Armor " + hero.getNickname() + " can use ..." + Utility.RESET);
                break;
            }
            System.out.println("Enter the index of one of the above items to equip it (or type 'exit' to select none)");
       
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) {
                System.out.println("You've opted to exit. Turn is over");
                break;
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
        Hero target;
        while(true) {
            System.out.print("Enter target hero to teleport adjacent to: ");
            String targetNickname = scanner.nextLine().trim().toUpperCase();
            target = party.getHerobyNickname(targetNickname);
            if(targetNickname.equalsIgnoreCase("i")) {
                Game.printInstructions();
            } else if(targetNickname.equalsIgnoreCase("stats")) {
                party.displayInfo();
            } else if(targetNickname.equalsIgnoreCase("inv")) {
                hero.printInventory();
            } else if(targetNickname.equalsIgnoreCase("map")) {
                world.printMap(party);
            } else if(targetNickname.equalsIgnoreCase("q")) {
                System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                System.exit(0);
            } else if (target == null) {
                System.out.println(Utility.YELLOW + "Target hero not found. Response should be H1, H2, or H3." + Utility.RESET);
            } else if(targetNickname.equalsIgnoreCase(hero.getNickname()) || hero.getLane() == target.getLane()){
                System.out.println(Utility.YELLOW + "You have to move to different lane !" + Utility.RESET);
            } else {
                break;
            }
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
        // if(party.getDeadHeros().size() == 3){
        //     System.out.println(Utility.YELLOW + "All the heroes are dead. Heroes lose ... " + Utility.RESET);
        //     return true;
        // }
        return false;
    }
    
    public static void printInstructions(){
        System.out.println();
        System.out.println(Utility.YELLOW_BOLD_BRIGHT + "Instructions: \n" + Utility.RESET);
        System.out.println(Utility.YELLOW + "Make your way through the gameboard to the Monsters' Nexus.");
        System.out.println("Your goal is to arrive at the Monsters' Nexus before they arrive at yours.");
        System.out.println("But you'll have to work your way through the board and battle Monsters to get there.");
        System.out.println("Killed Heroes will respawn at their Nexus and additional Monsters will spwan every few rounds.\n");
        System.out.println(Utility.YELLOW_BOLD_BRIGHT + "Board Spaces:" + Utility.RESET);
        System.out.println(Utility.PURPLE + Utility.PURPLE_BACKGROUND_BRIGHT + "NEXUS" + Utility.RESET + ":\t\tserves as respawn + recall space for heroes/monsters. heroes can use any hero nexus as a market");
        System.out.println(Utility.WHITE + Utility.BLACK_BACKGROUND + "INACCESSIBLE" + Utility.RESET + ":\tno player can ever enter");
        System.out.println(Utility.RED + "OBSTACLE" + Utility.RESET + ":\theroes must remove before heroes or monsters can enter space");
        System.out.println(Utility.BLUE + "PLAIN" + Utility.RESET + ":\t\tno special attribute");
        System.out.println(Utility.GREEN + "BUSH" + Utility.RESET + ":\t\tincreases hero's dexterity while on it ");
        System.out.println(Utility.CYAN + "CAVE" + Utility.RESET + ":\t\tincreases hero's agility while on it");
        System.out.println(Utility.YELLOW + "KOULOU" + Utility.RESET + ":\t\tincreases hero's strength while on it\n");
        System.out.println(Utility.YELLOW_BOLD_BRIGHT + "General Commands:");
        System.out.println(Utility.RESET + "(to be used at any point during gameplay)" + Utility.YELLOW);
        System.out.println("Enter I to view instructions about the game.");
        System.out.println("Enter STATS to view hero and monster statistics.");
        System.out.println("Enter INV to see the inventory of the hero whose turn it is.");
        System.out.println("Enter MAP to display the world map.");
        System.out.println("Enter Q to quit the game." + Utility.RESET);
        System.out.println();
        System.out.println(Utility.GREEN + "Enter X to exit Instructions" + Utility.RESET);
        Scanner scan = new Scanner(System.in);
        while(true) {
            String response = scan.next();
            if(response.equalsIgnoreCase("x")) {
                break;
            } else {
                System.out.println(Utility.RED + "Invalid resposne" + Utility.RESET);
                System.out.println("You must exit Instructions before enterring any other commands.");
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