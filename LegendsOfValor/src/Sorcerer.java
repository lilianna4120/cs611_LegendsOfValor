class Sorcerer extends Hero {
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

    public void levelUp() {
        level++;
        if(hp < getMaxHP()){
            hp = getMaxHP();
        }
        if(mp < getMaxMP()){
            mp = getMaxMP();
        }
        strength *= 1.05;
        dexterity *= 1.05;
        dexterity *= 1.05;
        agility *= 1.05;
        agility *= 1.05;
        System.out.println(name + " leveled up to " + level + "!");
    }
}

