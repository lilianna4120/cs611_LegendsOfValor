import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class World{
    private Space[][] grid;
    private int width;
    private int height;
    private Map<String, NexusSpace> map = new HashMap<>();

    public World(int width, int height){
        this.width = width;
        this.height = height;
        // grid = new Tile[height][width]; // CHANGE IT TO TILE ??
        grid = new Space[height][width];
        generateWorld();
    }

    private void generateWorld(){
        Random rand = new Random();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(i == 0 || i == height - 1){
                    NexusSpace nexus = new NexusSpace(i, j);
                    grid[i][j] = nexus;
                    map.put(i + "," + j, nexus);
                }

                else if(j == 2 || j == 5){
                    grid[i][j] = new InaccessibleSpace(i, j);
                }else{
                    grid[i][j] = randomlyAssign(rand, i, j);
                }
            }
        }
    }

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

    public Space getSpace(int row, int col){
        return grid[row][col];
    }

    // public MarketSpace getMarketAt(Position p){
    //     String key = p.getRow() + "," + p.getCol();
    //     return marketMap.get(key);
    // }

    public void display(Party party){
        System.out.println();
    System.out.println(Utility.BLUE + "World Board:" + Utility.RESET);

    // Create a horizontal separator string for the board.
    String horizontalSeparator = "";
    for (int j = 0; j < width; j++) {
        horizontalSeparator += "+---";
    }
    horizontalSeparator += "+";


    // Print the board row by row.
    for (int i = 0; i < height; i++) {
        System.out.println(horizontalSeparator);
        // Start the row with a vertical border.
        String rowStr = "";
        for (int j = 0; j < width; j++) {
            String cellSymbol = "";
            // Check if a hero is in this cell.
            boolean heroPresent = false;
            for (Hero h : party.getHeroes()) {
                if (h.getRow() == i && h.getCol() == j && h.isAlive()) {
                    cellSymbol = h.getNickname();
                    heroPresent = true;
                    break;
                }
            }
            boolean monsterPresent = false;
            for(Monster m: party.getMonsters()){
                if(m.getRow() == i && m.getCol() == j && m.isAlive()){
                    cellSymbol = m.getNickname();
                    monsterPresent = true;
                    break;
                }
            }
            // If no hero, show the underlying cell symbol.
            if (!heroPresent && !monsterPresent) {
                cellSymbol = Character.toString(grid[i][j].getSymbol());
            }
            
            if(j == 2 || j == 5){
                InaccessibleSpace is = new InaccessibleSpace(i, j);
                cellSymbol = Character.toString(is.getSymbol());
            }
            // Build the cell string.
            rowStr += "| " + cellSymbol + " ";
        }
        rowStr += "|";
        System.out.println(rowStr);
    }
    System.out.println(horizontalSeparator);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

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