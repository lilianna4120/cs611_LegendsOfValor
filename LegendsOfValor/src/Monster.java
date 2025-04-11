/*
 * Monster.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Abstract class that represents a Monster, where each Monster has a variety of important attributes and methods.
 * Extends Character and overrides various methods.
 */
public abstract class Monster extends Characters {
    // protected varaibles for monster class
    protected double baseDamage;
    protected double defense;
    protected double dodgeChance;
    protected int row;
    protected int col;
    protected String nickname;

    // public constructor for monster class
    public Monster(String name, int level, double baseDamage, double defense, double dodgeChance) {
        super(name, level);
        this.baseDamage = baseDamage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    // when monster attacks
    @Override
    public double attack() {
        return baseDamage*0.2; // 0.3
    }

    // when monsters get attacked
    @Override
    public void takeDamage(double damage){
        double actualDamage = damage - defense*0.4;
        if(actualDamage < 0){
            actualDamage = 0;
        }
        hp -= actualDamage;
        if(hp < 0){
            hp = 0;
        }
    }

    // take additional effect depending on spell type; fire spell
    public void takeDefense(double defense){
        double newDefense = this.defense - defense;
        if(newDefense < 0){
            newDefense = 0;
        }
    }

    // take additional effect depending on spell type; lightning spell
    public void takeDodgeChange(double dodgeChange) {
        double newDodgeChance = this.dodgeChance - dodgeChange;
        if(newDodgeChance < 0){
            newDodgeChance = 0;
        }
    }

    // implement in character.java so that both hero and monster inherits it !!
    public boolean isAlive(){
        return hp > 0;
    }

    // accessor methods of monster class
    public String getNickname(){
        return nickname;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public double getDamage() {
        return this.baseDamage;
    }

    public double getDodge() {
        return this.dodgeChance;
    }

    public double getDefense() {
        return this.defense;
    }

    // mutator methods of monster class
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    // display monster information
    @Override
    public void displayInfo() {
        System.out.println();
        System.out.println("----- Monster Info -----");
        System.out.println("Name:     " + name);
        System.out.println("Nickname: " + nickname);
        System.out.println("Level:    " + level);
        System.out.println("HP:       " + hp + "/" + getMaxHP());
        System.out.println("Damage:   " + baseDamage);
        System.out.println("Defense:  " + defense);
        System.out.println("Dodge:    " + dodgeChance + "%");
        System.out.println("------------------------");
    }
}
