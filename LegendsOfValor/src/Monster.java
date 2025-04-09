public abstract class Monster extends Characters {
    protected double baseDamage;
    protected double defense;
    protected double dodgeChance;
    protected int row;
    protected int col;
    protected String nickname;

    public Monster(String name, int level, double baseDamage, double defense, double dodgeChance) {
        super(name, level);
        this.baseDamage = baseDamage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    @Override
    public double attack() {
        return baseDamage*0.1; // 0.3
    }

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

    // implement in character.java so that both hero and monster inherits it !!
    public boolean isAlive(){
        return hp > 0;
    }

    public void assignNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

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
