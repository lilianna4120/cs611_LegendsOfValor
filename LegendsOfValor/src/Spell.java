public class Spell extends Item {
    private double damage;
    private double manaCost;
    private String spellType;

    public Spell(String name, int price, int requiredLevel, int uses, double damage, double manaCost, String spellType) {
        super(name, price, requiredLevel, uses);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
    }

    public double getDamage() {
        return damage;
    }

    public double getManaCost() {
        return manaCost;
    }

    public void setType(String type){
        this.spellType = type;
    }

    public String getSpellType() {
        return spellType;
    }

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
