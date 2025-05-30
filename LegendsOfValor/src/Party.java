/*
 * Party.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that represents a Party containing the Heroes and Monsters in the Game.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Party {
    // private variables for party class
    private List<Hero> heroes;
    private List<Monster> monsters;
    private List<Hero> deadHeroes;

    // public constructor for party class
    public Party() {
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
        deadHeroes = new ArrayList<>();
    }

    // to add or remove party members
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

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    // accessor method of party class
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

    // display party information; includes all characters
    public void displayInfo(){
        System.out.println(Utility.CYAN + "Heroes Information:" + Utility.RESET);
        for(Hero h: heroes){
            h.displayInfo();
        }

        System.out.println(Utility.CYAN + "Monsters Information:" + Utility.RESET);
        for(Monster m: monsters){
            m.displayInfo();
        }
        System.out.println();
        System.out.println(Utility.GREEN + "Enter X to exit Stats" + Utility.RESET);
        Scanner scan = new Scanner(System.in);
        while(true) {
            String response = scan.next();
            if(response.equalsIgnoreCase("x")) {
                break;
            } else {
                System.out.println(Utility.RED + "Invalid resposne" + Utility.RESET);
                System.out.println("You must exit Stats before enterring any other commands.");
            }
        }
    }

    // check if all the heroes or monsters are dead
    public boolean heroesDefeated(){
        for(Hero h: heroes){
            if(h.isAlive()){
                return false;
            }
        }
        return true;
    }

    // public boolean monstersDefeated(){
    //     for(Monster m: monsters){
    //         if(m.isAlive()){
    //             return false;
    //         }
    //     }
    //     return true;
    // }
}