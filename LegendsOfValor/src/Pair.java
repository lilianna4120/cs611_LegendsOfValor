public class Pair {
    private Hero hero;
    private Monster monster;

    public Pair(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    public boolean isFinished() {
        return !hero.isAlive() || !monster.isAlive();
    }
    
    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
}
