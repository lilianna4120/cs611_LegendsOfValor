/*
 * Warrior.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Warrior, where each Warrior has a variety of important attributes.
 * Extends Hero and overrides attack() method.
 */
class Warrior extends Hero {
    // public constructor for warrior class
    public Warrior(String name, int mp, int strength, int agility, int dexterity, int gold, int experience) {
        super(name);
        this.mp = mp;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.experience = experience;
        this.hp = getMaxHP();
    }
    
    // when warrior attacks
    @Override
    public double attack() {
        double weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getDamage();
        } else {
            weaponDamage = 0;
        }

        double bonus = 0;
        // for one-handed weapons, if used with both hands can add bonus damage.
        if (equippedWeapon != null && equippedWeapon.getHandsRequired() == 1) {
            bonus = weaponDamage*0.1; // add a bonus
        }

        double totalAttack = (strength + weaponDamage + bonus);

        return totalAttack*0.05;
    }

    // warriors are favored on strength and agaility
    public void levelUp() {
        level++;
        if(hp < getMaxHP()){
            hp = getMaxHP();
        }
        if(mp < getMaxMP()){
            mp = getMaxMP();
        }

        strength *= 1.1;
        dexterity *= 1.05;
        agility *= 1.1;

        System.out.println(name + " leveled up to " + level + "!");
    }
}
