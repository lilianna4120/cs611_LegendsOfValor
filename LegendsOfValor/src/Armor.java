public class Armor extends Item {
    private double damageReduction;

    public Armor(String name, int price, int requiredLevel, int uses, double damageReduction) {
        super(name, price, requiredLevel, uses);
        this.damageReduction = damageReduction;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    @Override
    public void display() {
        System.out.println("----- Armor Details -----");
        System.out.println("Name           : " + name);
        System.out.println("Cost           : " + price);
        System.out.println("Required Level : " + requiredLevel);
        System.out.println("Damage Reduction: " + damageReduction);
        System.out.println("Uses Left      : " + uses);
        System.out.println("-------------------------");
    }
}
