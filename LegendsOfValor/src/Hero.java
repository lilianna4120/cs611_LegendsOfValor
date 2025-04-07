import java.util.ArrayList;
import java.util.List;

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

    public void levelUp() {
        level++;
        if(hp < getMaxHP()){
            hp = getMaxHP();
        }
        if(mp < getMaxMP()){
            mp = getMaxMP();
        }
        strength *= 1.05;
        dexterity *= 1.05;
        agility *= 1.05;
        System.out.println(name + " leveled up to " + level + "!");
    }

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

    public void assignNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    @Override
    public abstract double attack();

    @Override
    public void displayInfo() {
        System.out.println(Utility.CYAN + name + Utility.RESET + " (Level " + level + ")");
        System.out.println("HP: " + Utility.createBar(hp, getMaxHP(), 20, Utility.GREEN) + " " + hp + "/" + getMaxHP());
        System.out.println("MP: " + Utility.createBar(mp, getMaxMP(), 20, Utility.BLUE) + " " + mp + "/" + getMaxMP());
        System.out.println(Utility.YELLOW + "Gold: " + gold + Utility.RESET);
        System.out.println(Utility.GREEN + "Exp: " + experience + Utility.RESET);
        if(equippedWeapon != null) {
            System.out.println(Utility.CYAN + "Equipped Weapon: " + equippedWeapon.getName() + Utility.RESET);
        }
        if(equippedArmor != null) {
            System.out.println(Utility.CYAN + "Equipped Armor: " + equippedArmor.getName() + Utility.RESET);
        }
        System.out.println(Utility.CYAN + "Inventory: " + Utility.RESET);
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(Utility.CYAN + "[" + i + "] " + inventory.get(i).getName() + " (" + inventory.get(i).getUses() + " uses left)" + Utility.RESET);
        }
        System.out.println("----------------------------");
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public List<Item> getInventory(){
        return inventory;
    }

    public void equipItem(int index){
        if(index < 0 || index >= inventory.size()){
            System.out.println("Invalid index.");
            return;
        }
        Item item = inventory.get(index);
        
        if(item instanceof Weapon){
            equippedWeapon = (Weapon)item;
            System.out.println(name + " equipped weapon " + item.getName());
        }else if(item instanceof Armor){
            equippedArmor = (Armor) item;
            System.out.println(name + "equipped armor " + item.getName());
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

    public void teleport(Hero target){
        int targetCol = target.getCol();
        if(targetCol-1 >= 0){
            setPosition(target.getRow(), targetCol-1);
            System.out.println(name + " teleported adjacent to " + target.name + " to (" + getRow() + "," + getCol() + ")");
        } else {
            System.out.println("Teleport failed; no adjacent cell available.");
        }
    }

    public void recall(int nexusRow){
        setPosition(nexusRow, this.col);
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
        System.out.println(name + "'s " + stat + " increased by " + bonus + "temporarily."); // CHANGE !!
    }
}
