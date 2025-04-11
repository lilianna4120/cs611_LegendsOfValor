/*
 * Spirit.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Spirit, where each Spirit has a variety of important attributes.
 * Extends Monster.
 */
class Spirit extends Monster {
    // public constructor for spirit class
    public Spirit(String name, int level, double damage, double defense, double dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        this.baseDamage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }
}
