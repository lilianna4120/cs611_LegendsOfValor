import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Hero extends Characters {
    protected double mp;
    protected double strength;
    protected double dexterity;
    protected double agility;
    protected int experience;
    protected int gold;
    protected List<Item> inventory;
    protected Weapon equippedWeapon;
    protected Armor equippedArmor;
    protected int row;
    protected int col;
    protected String nickname;

    public Hero(String name) {
        super(name, 1);
        this.experience = 0;
        this.mp = 100;
        this.gold = 1000;
        inventory = new ArrayList<>();
    }

    public double getMaxMP() {
        return level * 100;
    }

    public abstract void levelUp();

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int[] getPosition() {
        return new int[] {this.row, this.col};
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return this.gold;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return this.experience;
    }

    public void assignNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    public double getHP(){
        return hp;
    }

    public double getMP(){
        return mp;
    }

    public void setHP(Double newHP){
        this.hp = newHP;
    }

    public void setMP(Double newMP){
        this.mp = newMP;
    }

    public double getStrength() {
        return this.strength;
    }

    public double getAgility() {
        return this.agility;
    }

    public double getDexterity() {
        return this.dexterity;
    }

    @Override
    public abstract double attack();

    public void gain(Monster m) {
        this.setGold(this.getGold() + (m.getLevel() * 500)/3);
        this.setExperience(this.getExperience() + (2*m.getLevel()));
        if(this.getExperience() / 10 >= this.getLevel()) {
            this.levelUp();
        }
    }

    @Override
    public void displayInfo() {
        System.out.println();
        System.out.println("-------- Hero Info --------");
        System.out.println(nickname + ": " + Utility.CYAN + name + Utility.RESET + " (Level " + level + ")");
        System.out.println("HP: " + Utility.createBar(hp, getMaxHP(), 20, Utility.GREEN) + " " + hp + "/" + getMaxHP());
        System.out.println("MP: " + Utility.createBar(mp, getMaxMP(), 20, Utility.PURPLE) + " " + mp + "/" + getMaxMP());
        System.out.println(Utility.BLUE + "Strength: " + strength + Utility.RESET);
        System.out.println(Utility.BLUE + "Dexterity: " + dexterity + Utility.RESET);
        System.out.println(Utility.BLUE + "Agility: " + agility + Utility.RESET);
        System.out.println(Utility.YELLOW + "Gold: " + gold + Utility.RESET);
        System.out.println(Utility.GREEN + "Exp: " + experience + Utility.RESET);
        if(equippedWeapon != null) {
            System.out.println(Utility.PURPLE + "Equipped Weapon: " + equippedWeapon.getName() + Utility.RESET);
        }
        if(equippedArmor != null) {
            System.out.println(Utility.PURPLE + "Equipped Armor: " + equippedArmor.getName() + Utility.RESET);
        }
        System.out.println(Utility.CYAN + "Inventory: " + Utility.RESET);
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(Utility.CYAN + "[" + i + "] " + inventory.get(i).getName() + " (" + inventory.get(i).getUses() + " uses left)" + Utility.RESET);
        }
        System.out.println("---------------------------");
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public List<Item> getInventory(){
        return inventory;
    }

    public void printInventory() {
        System.out.println(Utility.YELLOW + this.getNickname() + "'s Inventory: " + Utility.RESET);
        for(int j = 0; j < this.inventory.size(); j++){
            System.out.println(Utility.GREEN + "  [" + j + "] " + this.inventory.get(j).getName() + Utility.RESET);
        }
        System.out.println();
        System.out.println(Utility.GREEN + "Enter X to exit Inventory" + Utility.RESET);
        Scanner scan = new Scanner(System.in);
        while(true) {
            String response = scan.next();
            if(response.equalsIgnoreCase("x")) {
                break;
            } else {
                System.out.println(Utility.RED + "Invalid resposne" + Utility.RESET);
                System.out.println("You must exit Inventory before enterring any other commands.");
            }
        }
    }

    public void equipItem(Item item){        
        if(item instanceof Weapon){
            equippedWeapon = (Weapon)item;
            System.out.println(nickname + " equipped weapon " + item.getName());
        }else if(item instanceof Armor){
            equippedArmor = (Armor) item;
            System.out.println(nickname + "equipped armor " + item.getName());
        }else{
            System.out.println("This item cannot be equipped");
        }
    }

    public void usePotion(Potion potion) {
        String effect = potion.getEffectType().toLowerCase();
        double amount = potion.getEffectAmount();
            
        String[] effects = effect.split("/");
            
        for (String e : effects) {
            switch (e.trim()) {
                case "health":
                    this.hp += amount;
                    break;
                case "mana":
                    this.mp += amount;
                    break;
                case "strength":
                    this.strength += amount;
                    break;
                case "dexterity":
                    this.dexterity += amount;
                    break;
                case "agility":
                    this.agility += amount;
                    break;
                case "defense":
                    this.strength += amount;
                    break;
            }
        }
        potion.use();
        System.out.println(this.name + " used " + potion.getName() + " and gained:");
        for (String e : effects) {
            System.out.println(" + " + amount + " " + e.trim());
        }
    }

    public void teleport(Hero hero, Hero target, World world, Party party){
        HashMap<Integer, String> availableSpaces = new HashMap<Integer, String>();
        availableSpaces = MovementUtil.getTelportableSpaces(target, world, party);
        Scanner scanner = new Scanner(System.in);

        if(availableSpaces.size() == 0){
            System.out.println("You can't teleport near " + target.getNickname());
            System.out.println("Turn ending");
        }else{
            int index = -1;
            String idx;
            while (true) {
                while(true) {
                    MovementUtil.printTeleportableSpaces(availableSpaces);
                    idx = scanner.nextLine().trim();
                    if(idx.equalsIgnoreCase("exit")) {
                        System.out.println("Turn ending. No teleportation.");
                        return;
                    } else if(idx.equalsIgnoreCase("i")) {
                        Game.printInstructions();
                    } else if(idx.equalsIgnoreCase("stats")) {
                        party.displayInfo();
                    } else if(idx.equalsIgnoreCase("inv")) {
                        hero.printInventory();
                    } else if(idx.equalsIgnoreCase("map")) {
                        world.printMap(party);
                    } else if(idx.equalsIgnoreCase("q")) {
                        System.out.println(Utility.RED + "Quitting the game ... " + Utility.RESET);
                        System.exit(0);
                    } else {
                        break;
                    }
                }
                try {
                    index = Integer.parseInt(idx);
                    if (index >= 0 && index <= availableSpaces.size()) {
                        break;
                    } else {
                        System.out.println(Utility.YELLOW + "Invalid input; Please enter an integer between 0 and " + availableSpaces.size() + " !" + Utility.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Utility.YELLOW + "Invalid input; Please enter a valid integer." + Utility.RESET);
                }
            }

            String newRowStr = availableSpaces.get(index).substring(1, 2);
            int newRow = Integer.parseInt(newRowStr);
            String newColStr = availableSpaces.get(index).substring(4, 5);
            int newCol = Integer.parseInt(newColStr);

            hero.setPosition(newRow, newCol);
        }
        
    }

    public void recall(int nexusRow){
        String heroIntStr = nickname.substring(1);
        int heroInt = Integer. parseInt(heroIntStr);
        int col = 7;
        if(heroInt == 1){
            col = 0;
        }else if(heroInt == 2){
            col = 3;
        }else if(heroInt == 3){
            col = 6;
        }
        setPosition(nexusRow, col);
        System.out.println(name + " recalled to Nexus at (" + getRow() + "," + getCol() + ")");
    }

    public void applyBonus(String stat, int bonus){
        if(stat == "dexterity"){
            this.dexterity += bonus;
        }else if(stat == "agility"){
            this.agility += bonus;
        } else if(stat == "strength"){
            this.strength += bonus;
        }
        System.out.println(name + "'s " + stat + " increased by " + bonus + " while the hero is in that space.");
    }

    public int getLane(){
        int lane = 0;
        if(this.col == 0 || this.col == 1){
            lane = 1;
        }else if(this.col == 3 || this.col == 4){
            lane = 2;
        }else if(this.col == 6 || this.col == 7){
            lane = 3;
        }
        return lane;
    }

    public void regains(){
        double newHP = hp * 1.1;
        double newMP = mp * 1.1;
        setHP(newHP);
        setMP(newMP);
        System.out.println(Utility.BLUE + nickname + " regains to " + hp + " HP and " + mp + " MP !" + Utility.RESET);
    }

    public void respawn(){
        this.hp = getMaxHP() / 2.0;
        this.mp = getMaxMP() / 2.0;
        int row = 7;
        int col = 0;
        String heroNum = nickname.substring(1);
        int heroNumInt = Integer.parseInt(heroNum);
        if(heroNumInt == 1){
            col = 0;
        }else if(heroNumInt == 2){
            col = 3;
        }else{
            col = 6;
        }
        setPosition(row, col);
        System.out.println(Utility.BLUE + nickname + " has respawned at (" + row + "," + col + ") with " + hp + " HP and " + mp + " MP !" + Utility.RESET);
    }
}
