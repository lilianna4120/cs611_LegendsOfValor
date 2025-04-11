/*
 * Exoskeleton.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents an Exoskeleton, where each Exoskeleton has a variety of important attributes.
 * Extends Monster.
 */
class Exoskeleton extends Monster {
    // constructor for exoskeleton
    public Exoskeleton(String name, int level, double damage, double defense, double dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        this.baseDamage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }
}