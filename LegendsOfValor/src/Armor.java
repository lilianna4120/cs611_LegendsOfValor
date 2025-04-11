/*
 * Armor.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents an Armor, where each Armor has a variety of important attributes
 * that play a factor into its effect in methods in Game class.
 * Extends Item, overriding its key method: display().
 */
public class Armor extends Item {
    // private variable to get damage of armor item
    private double damageReduction;

    // constructor of armor item
    public Armor(String name, int price, int requiredLevel, int uses, double damageReduction) {
        super(name, price, requiredLevel, uses);
        this.damageReduction = damageReduction;
    }

    // accessor method of armor item
    public double getDamageReduction() {
        return damageReduction;
    }

    // to print out the armor information
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
