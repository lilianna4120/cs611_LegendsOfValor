/*
 * Dragon.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Dragon, where each Dragon has a variety of important attributes.
 * Extends Monster.
 */
class Dragon extends Monster {
    // constructor for dragon
    public Dragon(String name, int level, double damage, double defense, double dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        this.baseDamage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }
}
