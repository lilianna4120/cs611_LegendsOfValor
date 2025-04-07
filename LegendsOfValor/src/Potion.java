public class Potion extends Item {
    private double effectAmount;
    private String effectType;

    public Potion(String name, int price, int requiredLevel, int uses, String effectType, double effectAmount) {
        super(name, price, requiredLevel, uses);
        this.effectType = effectType;
        this.effectAmount = effectAmount;
    }

    public double getEffectAmount() {
        return effectAmount;
    }

    public String getEffectType() {
        return effectType;
    }

    @Override
    public void display() {
        System.out.println("----- Potion Details -----");
        System.out.println("Name           : " + name);
        System.out.println("Cost           : " + price);
        System.out.println("Required Level : " + requiredLevel);
        System.out.println("Effect         : " + effectType + " +" + effectAmount);
        System.out.println("Uses Left      : " + uses);
        System.out.println("--------------------------");
    }
}
