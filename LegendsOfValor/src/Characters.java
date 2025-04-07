public abstract class Characters {
    protected String name;
    protected int level;
    protected double hp;

    public Characters(String name, int level) {
        this.name = name;
        this.level = level;
        this.hp = getMaxHP();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(double dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public double getMaxHP() {
        return level * 100;
    }

    public abstract double attack();
    public abstract void displayInfo();
}
