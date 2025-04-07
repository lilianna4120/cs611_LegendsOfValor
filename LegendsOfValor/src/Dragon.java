class Dragon extends Monster {
    public Dragon(String name, int level, double damage, double defense, double dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        this.baseDamage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }
}
