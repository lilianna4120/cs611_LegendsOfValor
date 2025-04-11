public class Spell extends Item {
    // private variables for spell class
    private double damage;
    private double manaCost;
    private String spellType;

    // public constructor for spell class
    public Spell(String name, int price, int requiredLevel, int uses, double damage, double manaCost, String spellType) {
        super(name, price, requiredLevel, uses);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
    }

    // accessor methods of spell class
    public double getDamage() {
        return damage;
    }

    public double getManaCost() {
        return manaCost;
    }

    // mutator methods of spell class
    public void setType(String type){
        this.spellType = type;
    }

    public String getSpellType() {
        return spellType;
    }

    // display information about spell item
    @Override
    public void display() {
        System.out.println("----- Spell Details -----");
        System.out.println("Name           : " + name);
        System.out.println("Spell Type     : " + spellType);
        System.out.println("Cost           : " + price);
        System.out.println("Required Level : " + requiredLevel);
        System.out.println("Damage         : " + damage);
        System.out.println("Mana Cost      : " + manaCost);
        System.out.println("Uses Left      : " + uses);
        System.out.println("-------------------------");
    }
}
