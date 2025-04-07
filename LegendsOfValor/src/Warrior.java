class Warrior extends Hero {
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
    
    @Override
    public double attack() {
        double weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getDamage();
        } else {
            weaponDamage = 0;
        }

        double bonus = 0;
        // for one-handed weapons, if used with both hands  can add bonus damage.
        if (equippedWeapon != null && equippedWeapon.getHandsRequired() == 1) {
            bonus = weaponDamage*0.1; // add a bonus
        }

        double totalAttack = (strength + weaponDamage + bonus);

        return totalAttack*0.5;
    }
}
