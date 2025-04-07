public class Weapon extends Item {
    private double damage;
    private int handsRequired;

    public Weapon(String name, int price, int requiredLevel, int uses, double damage, int handsRequired) {
        super(name, price, requiredLevel, uses);
        this.damage = damage;
        this.handsRequired = handsRequired;
    }

    public double getDamage() {
        return damage;
    }

    public int getHandsRequired() {
        return handsRequired;
    }

    @Override
    public void display() {
        System.out.println("----- Weapon Details -----");
        System.out.println("Name           : " + name);
        System.out.println("Cost           : " + price);
        System.out.println("Required Level : " + requiredLevel);
        System.out.println("Damage         : " + damage);
        System.out.println("Required Hands : " + handsRequired);
        System.out.println("Uses Left      : " + uses);
        System.out.println("--------------------------");
    }
}
