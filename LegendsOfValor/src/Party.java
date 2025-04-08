import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<Hero> heroes;
    private List<Monster> monsters;
    private List<Hero> deadHeroes;

    public Party() {
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
        deadHeroes = new ArrayList<>();
    }

    public void addHero(Hero hero){
        if (heroes.size() < 3) {
            heroes.add(hero);
        } else {
            System.out.println(Utility.RED + "Maximum 3 heroes allowed." + Utility.RESET);
        }
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public List<Monster> getMonsters(){
        return monsters;
    }

    public Hero getHerobyNickname(String nickname){
        for(Hero h: heroes){
            if(h.getNickname().equalsIgnoreCase(nickname)){
                return h;
            }
        }
        return null;
    }

    public List<Hero> getDeadHeros(){
        return deadHeroes;
    }

    public void addDeadHero(Hero hero){
        deadHeroes.add(hero);
    }

    // public void move(int width, int height, int newRow, int newCol, World world){
    //     if(newRow < 0 || newRow >= height || newCol < 0 || newCol >= width){
    //         System.out.println(Utility.RED + "Invalid Input; you cannot move outside of the board !" + Utility.RESET);
    //         return;
    //     }

    //     Position newPos = new Position(newRow, newCol);
    //     if (world.getTile(newPos) instanceof InaccessibleTile) {
    //         System.out.println(Utility.RED + "That spot is not accessible !" + Utility.RESET);
    //         return;
    //     }

    //     position.setRow(newRow);
    //     position.setCol(newCol);
    //     System.out.println(Utility.YELLOW + "Party moved to (" + (newRow+1) + ", " + (newCol+1) + ")" + Utility.RESET);
    // }

    public void displayInfo(){
        System.out.println(Utility.CYAN + "Heroes Information:" + Utility.RESET);
        for(Hero h: heroes){
            h.displayInfo();
        }

        System.out.println(Utility.CYAN + "Monsters Information:" + Utility.RESET);
        for(Monster m: monsters){
            m.displayInfo();
        }
    }

    public boolean heroesDefeated(){
        for(Hero h: heroes){
            if(h.isAlive()){
                return false;
            }
        }
        return true;
    }

    public boolean mosntersDefeated(){
        for(Monster m: monsters){
            if(m.isAlive()){
                return false;
            }
        }
        return true;
    }

    // public void setRandomPosition(World world) {
    //     Random rand = new Random();
    //     int width = world.getWidth();
    //     int height = world.getHeight();
    //     while (true) {
    //         int r = rand.nextInt(height);
    //         int c = rand.nextInt(width);
    //         Position newPos = new Position(r, c);
    //         if (!(world.getTile(newPos) instanceof InaccessibleTile)) {
    //             this.position.setRow(r);
    //             this.position.setCol(c);
    //             break;
    //         }
    //     }
    // }

}