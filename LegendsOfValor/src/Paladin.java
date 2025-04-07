class Paladin extends Hero {
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

    @Override
    public double attack() {
        double weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getDamage();
        } else {
            weaponDamage = 0;
        }
        return (strength + weaponDamage)*0.5 ; //* 0.05;
    }
}
