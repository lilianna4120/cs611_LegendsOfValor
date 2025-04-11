import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class World{
    // private variables for world class
    private Space[][] grid;
    private int width;
    private int height;
    private Map<String, NexusSpace> map = new HashMap<>();

    // public constructor for world class
    public World(int width, int height){
        this.width = width;
        this.height = height;
        // grid = new Tile[height][width]; // CHANGE IT TO TILE ??
        grid = new Space[height][width];
        generateWorld();
    }

    // generate the world
    private void generateWorld(){
        Random rand = new Random();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(i == 0 || i == height - 1){
                    NexusSpace nexus = new NexusSpace(i, j);
                    grid[i][j] = new NexusSpace(i, j);
                    map.put(i + "," + j, nexus);
                } else{
                    grid[i][j] = randomlyAssign(rand, i, j);
                }

                if(j == 2 || j == 5){
                    grid[i][j] = new InaccessibleSpace(i, j);
                }
            }
        }
    }

    // generate the random space
    private Space randomlyAssign(Random rand, int row, int col){
        int r = rand.nextInt(100);
        if(r < 20){
            return new ObstacleSpace(row, col);
        }else if (r < 40) {
            return new BushSpace(row, col);
        } else if (r < 60) {
            return new CaveSpace(row, col);
        } else if (r < 80) {
            return new KoulouSpace(row, col);
        } else {
            return new PlainSpace(row, col);
        }
    }

    // accessor method for world class
    public Space getSpace(int row, int col){
        return grid[row][col];
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    // display the world
    public void display(Party party){
        System.out.println();
        System.out.println(Utility.BLUE + "World Map:" + Utility.RESET);

        System.out.println(Utility.BLACK_BACKGROUND + "                                                                                          " + Utility.RESET);
        for(int i = 0; i < 8; i++) {
            for(int k = 0; k < 5; k++) {
                System.out.print(Utility.BLACK_BACKGROUND + " " + Utility.RESET);
                for(int j = 0; j < 8; j ++) {
                    String c = Character.toString(grid[i][j].getSymbol());
                    String color = "";
                    if(c.equals("N")) {
                        System.out.print(Utility.PURPLE_BACKGROUND_BRIGHT);
                        color = Utility.PURPLE;
                    } 
                    else if(c.equals("B")) {
                        color = Utility.GREEN;
                    }
                    else if(c.equals("X")) {
                        color = Utility.WHITE;
                        System.out.print(Utility.BLACK_BACKGROUND);
                    }
                    else if(c.equals("K")) {
                        color = Utility.YELLOW;
                    }
                    else if(c.equals("C")) {
                        color = Utility.CYAN;
                    }
                    else if(c.equals("O")) {
                        color = Utility.RED;
                    }
                    else if(c.equals("P")) {
                        color = Utility.BLUE;
                    }
                    System.out.print(color);
                    if(k == 0 || k == 4) {
                        System.out.print(" " + c + " - " + c + " - " + c + " ");
                    }
                    else if(k == 1 || k == 3) {
                        System.out.print(" |       | ");
                    }
                    else {
                        boolean heroPresent = false;
                        String hero = "";
                        for (Hero h : party.getHeroes()) {
                            if (h.getRow() == i && h.getCol() == j && h.isAlive()) {
                                hero = h.getNickname();
                                heroPresent = true;
                                break;
                            }
                        }
                        boolean monsterPresent = false;
                        String monster = "";
                        for(Monster m: party.getMonsters()){
                            if(m.getRow() == i && m.getCol() == j && m.isAlive()){
                                monster = m.getNickname();
                                monsterPresent = true;
                                break;
                            }
                        }
                        if(heroPresent && monsterPresent) {
                            System.out.print(" " + c + " " + Utility.BLACK_BOLD_BRIGHT + hero + " " + monster + color + " " + c + " ");
                        }
                        else if(heroPresent) {
                            System.out.print(" " + c + "  " + Utility.BLACK_BOLD_BRIGHT + hero + color + "   " + c + " ");
                        }
                        else if(monsterPresent) {
                            System.out.print(" " + c + "   " + Utility.BLACK_BOLD_BRIGHT + monster + color + "  " + c + " ");
                        }
                        else {
                            System.out.print(" " + c + "       " + c + " ");
                        }
                    }
                    System.out.print(Utility.RESET);
                }
                System.out.println(Utility.BLACK_BACKGROUND + " " + Utility.RESET);
            }
        }
        System.out.println(Utility.BLACK_BACKGROUND + "                                                                                          " + Utility.RESET);
    }

    // when printing map is requested
    public void printMap(Party party) {
        System.out.println(Utility.CYAN + "Printing The World Map: " + Utility.RESET);
        this.display(party);
        System.out.println();
        System.out.println(Utility.GREEN + "Enter X to exit Map" + Utility.RESET);
        Scanner scan = new Scanner(System.in);
        while(true) {
            String response = scan.next();
            if(response.equalsIgnoreCase("x")) {
                break;
            } else {
                System.out.println(Utility.RED + "Invalid resposne" + Utility.RESET);
                System.out.println("You must exit Map before enterring any other commands.");
            }
        }
    }
}