// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
// import java.util.Random; 

// public class Battle {
//     private Party party;
//     private List<Monster> monsters;
//     private Scanner scanner;

//     public Battle(Hero hero, List<Monster> monsters) {
//         this.party = party;
//         this.monsters = new ArrayList<>();
//         this.scanner = new Scanner(System.in);
//     }
    
//     public void startBattle() {
//         int pairCount = party.getHeroes().size();
//         List<Pair> pairs = new ArrayList<>();

//         for (int i = 0; i < pairCount; i++) {
//             Hero hero = party.getHeroes().get(i);
//             Monster monster = monsters.get(i);
//             pairs.add(new Pair(hero, monster));
//             System.out.println(Utility.YELLOW + "Pair " + (i + 1) + ": " + hero.getName() + " vs. " + monster.getName() + Utility.RESET);
//         }

//         while (!allPairsFinished(pairs)) {
//             for (Pair pair : pairs) {
//                 if (!pair.isFinished()) {
//                     processHeroTurn(pair.getHero(), pair.getMonster());
//                     if (pair.isFinished()) {
//                         System.out.println(Utility.YELLOW + "Pair ended: " + pair.getHero().getName() + " vs. " + pair.getMonster().getName() + Utility.RESET);
//                         continue;
//                     }
//                     processMonsterTurn(pair.getMonster(), pair.getHero());
//                 }
//             }
//         }

//         if (party.isDefeated()) {
//             System.out.println(Utility.RED + "All heroes have been defeated. Game Over!" + Utility.RESET);
//         } else {
//             System.out.println(Utility.YELLOW + "Battle finished. Rewarding heroes:" + Utility.RESET);
//             int monstMaxLevel = 0;
//             for(Monster m: monsters){
//                 if(m.getLevel() > monstMaxLevel){
//                     monstMaxLevel = m.getLevel();
//                 }
//             }
//             rewardHeroes(monstMaxLevel);
//         }
//     }

//     private boolean allPairsFinished(List<Pair> pairs) {
//         for (Pair pair : pairs) {
//             if (!pair.isFinished()) {
//                 return false;
//             }
//         }
//         return true;
//     }

//     private void processHeroTurn(Hero hero, Monster monster) {
//         if (!hero.isAlive()) {
//             System.out.println(hero.getName() + " is unable to act.");
//             return;
//         }
//         System.out.println(Utility.GREEN + "\n" + hero.getName() + "'s turn. Choose action:"+ Utility.RESET);
//         System.out.println("1. Attack   2. Cast Spell   3. Use Potion   4. Show Stats   Q. Quit");
//         String action = scanner.nextLine().trim().toLowerCase();
//         if (action.equals("q")) {
//             System.out.println(Utility.RED + "Quitting the battle and game..." + Utility.RESET);
//             System.exit(0);
//         }
//         if (action.equals("4")) {
//             for(Hero h: party.getHeroes()){
//                 h.displayInfo();
//             }
//             for(Monster m: monsters){
//                 m.displayInfo();
//             }
//             System.out.println("Re-enter action for " + hero.getName() + ":");
//             action = scanner.nextLine().trim().toLowerCase();
//             if (action.equals("q")) {
//                 System.out.println(Utility.RED + "Quitting the battle and game..." + Utility.RESET);
//                 System.exit(0);
//             }
//         }
//         switch (action) {
//             case "1":
//                 double damage = hero.attack();
//                 System.out.println(Utility.CYAN + hero.getName() + " attacks " + monster.getName() + " for " + damage + " damage." + Utility.RESET);
//                 monster.takeDamage(damage);
//                 if (!monster.isAlive()) {
//                     System.out.println(Utility.GREEN + monster.getName() + " is defeated!"+ Utility.RESET);
//                 }
//                 break;
//             case "2": 
//                 Spell spellToCast = null;
//                 for (Item item : hero.getInventory()) {
//                     if (item instanceof Spell && item.isUsable()) {
//                         spellToCast = (Spell) item;
//                         break;
//                     }
//                 }
//                 if (spellToCast != null) {
//                     if (hero.mp >= spellToCast.getManaCost()) {
//                         hero.mp -= spellToCast.getManaCost();
//                         double spellDamage = spellToCast.getDamage() + (hero.dexterity / 10000.0) * spellToCast.getDamage();
//                         System.out.println(Utility.CYAN + hero.getName() + " casts " + spellToCast.getName() + " dealing " + spellDamage + " damage." + Utility.RESET);
//                         monster.takeDamage(spellDamage);
//                         if (!monster.isAlive()) {
//                             System.out.println(Utility.GREEN + monster.getName() + " is defeated!"+ Utility.RESET);
//                         }
//                         spellToCast.use();
//                     } else {
//                         System.out.println(Utility.YELLOW + "Not enough MP to cast " + spellToCast.getName() + Utility.RESET);
//                     }
//                 } else {
//                     System.out.println(Utility.YELLOW + "No spell available." + Utility.RESET);
//                 }
//                 break;
//             case "3":
//                 Potion potionToUse = null;
//                 for (Item item : hero.getInventory()) {
//                     if (item instanceof Potion && item.isUsable()) {
//                         potionToUse = (Potion) item;
//                         break;
//                     }
//                 }
//                 if (potionToUse != null) {
//                     hero.usePotion(potionToUse);
//                 } else {
//                     System.out.println(Utility.YELLOW + "No potion available." + Utility.RESET);
//                 }
//                 break;
//             default:
//                 System.out.println(Utility.YELLOW + "Invalid action. Turn skipped." + Utility.RESET);
//                 break;
//         }
//     }

//     private void processMonsterTurn(Monster monster, Hero hero) {
//         if (monster.isAlive() && hero.isAlive()) {
//             double mDamage = monster.attack();
//             System.out.println(Utility.YELLOW + monster.getName() + " attacks " + hero.getName() + " for " + mDamage + " damage." + Utility.RESET);
//             hero.takeDamage(mDamage);
//             if (!hero.isAlive()) {
//                 System.out.println(hero.getName() + " has been defeated!");
//             }
//         }
//     }

//     private void rewardHeroes(int monstMaxLevel) {
//         int expGain = 2*monstMaxLevel;
//         int goldGain = 100*monstMaxLevel;
//         for (Hero hero : party.getHeroes()) {
//             if (hero.isAlive()) {
//                 hero.experience += expGain;
//                 hero.gold += goldGain;
//                 System.out.println(Utility.BLUE + hero.getName() + " gains " + expGain + " EXP and " + goldGain + " gold !"+ Utility.RESET);
//                 if (hero.experience >= hero.level * 10) {
//                     hero.levelUp();
//                 }
//             } else {
//                 hero.hp = hero.getMaxHP() / 2;
//                 hero.mp = hero.getMaxMP() / 2;
//                 System.out.println(Utility.BLUE + hero.getName() + " is revived with half HP and MP."+ Utility.RESET);
//             }
//         }
//     }
// }
