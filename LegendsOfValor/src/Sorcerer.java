/*
 * Sorcerer.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Sorcerer, where each Sorcerer has a variety of important attributes.
 * Extends Hero and overrides attack() method.
 */
class Sorcerer extends Hero {
    // public constructor for sorcerer class
    public Sorcerer(String name, int mp, int strength, int agility, int dexterity, int gold, int experience) {
        super(name);
        this.mp = mp;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.experience = experience;
        this.hp = getMaxHP();
    }
    
    // when sorcerer attacks
    @Override
    public double attack() {
        double weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getDamage();
        } else {
            weaponDamage = 0;
        }

        return (dexterity + weaponDamage)*0.05;
    }

    // sorcerers are favored on dextrity and agility
    public void levelUp() {
        level++;
        if(hp < getMaxHP()){
            hp = getMaxHP();
        }
        if(mp < getMaxMP()){
            mp = getMaxMP();
        }
        strength *= 1.05;
        agility *= 1.1;
        dexterity *= 1.1;

        System.out.println(name + " leveled up to " + level + "!");
    }
}

