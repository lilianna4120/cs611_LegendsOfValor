/*
 * Paladin.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Paladin, where each Paladin has a variety of important attributes.
 * Extends Hero and overrides attack() method.
 */
class Paladin extends Hero {
    // public constructor for paladin method
    public Paladin(String name, int mp, int strength, int agility, int dexterity, int gold, int experience) {
        super(name);
        this.mp = mp;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.experience = experience;
        this.hp = getMaxHP();
    }

    // when paladin attacks
    @Override
    public double attack() {
        double weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getDamage();
        } else {
            weaponDamage = 0;
        }
        return (strength + weaponDamage)*0.05 ;
    }

    // paladins are favored on strength and dexterity
    public void levelUp() {
        level++;
        if(hp < getMaxHP()){
            hp = getMaxHP();
        }
        if(mp < getMaxMP()){
            mp = getMaxMP();
        }
        strength *= 1.1;
        dexterity *= 1.1;
        agility *= 1.05;
        System.out.println(name + " leveled up to " + level + "!");
    }
}
