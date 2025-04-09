import java.util.ArrayList;
import java.util.HashMap;

public class MovementUtil {
    public static boolean moveHero(Hero hero, String direction, Party party, World world) {
        int currentRow = hero.getRow();
        int currentCol = hero.getCol();
        int newRow = currentRow;
        int newCol = currentCol;
        
        switch (direction.toUpperCase()) {
            case "W":
                newRow = currentRow - 1;
                break;
            case "S":
                newRow = currentRow + 1;
                break;
            case "D":
                newCol = currentCol + 1;
                break;
            case "A":
                newCol = currentCol - 1;
                break;
            default:
                System.out.println("Invalid input; please enter W/S/A/D to move.");
                return false;
        }
        
        if (newRow < 0 || newRow >= world.getHeight() || newCol < 0 || newCol >= world.getWidth()) {
            System.out.println(Utility.YELLOW + "Invalid input; you can't move out of bounds." + Utility.RESET);
            return false;
        }
        
        if (!world.getSpace(newRow, newCol).isAccessible()) {
            System.out.println(Utility.YELLOW + "Invalid input; the cell you are trying to go is not accessible." + Utility.RESET);
            return false;
        }

        if(!world.getSpace(newRow, newCol).isNotOccupiedbyHero(party.getHeroes())){
            System.out.println(Utility.YELLOW + "You can not move there; it is occupied by other hero !" + Utility.RESET);
            return false;
        }
        
        // for (Hero h : party.getHeroes()) {
        //     if (h != hero && h.isAlive() && h.getRow() == newRow && h.getCol() == newCol) {
        //         System.out.println("Invalid input; the cell you are tring to go is already occupied by " + h.getNickname() + ".");
        //         return false;
        //     }
        // }
        
        int adjacentRow = currentRow;
        int adjacentCol = currentCol;
        switch (direction.toUpperCase()) {
            case "W":
                adjacentRow = currentRow - 1;
                break;
            case "S":
                adjacentRow = currentRow + 1;
                break;
            case "D":
                adjacentCol = currentCol + 1;
                break;
            case "A":
                adjacentCol = currentCol - 1;
                break;
        }
        for (Monster m : party.getMonsters()) {
            if(m.getRow() == adjacentRow && m.getCol() == adjacentCol){
                continue;
            }else if (m.isAlive() && m.getRow() == adjacentRow && m.getCol() == adjacentCol) {
                System.out.println("A monster (" + m.getName() + ") is blocking the way. You must defeat it first.");
                return false;
            }
        }
        
        hero.setPosition(newRow, newCol);
        System.out.println(hero.getName() + " moved to (" + newRow + ", " + newCol + ").");
        return true;
    }
    
    public static boolean moveMonster(Monster monster, String direction, World world) {
        int currentRow = monster.getRow();
        int currentCol = monster.getCol();
        int newRow = currentRow;
        int newCol = currentCol;
        
        switch (direction.toUpperCase()) {
            case "W":
                newRow = currentRow - 1;
                break;
            case "S":
                newRow = currentRow + 1;
                break;
            case "D":
                newCol = currentCol + 1;
                break;
            case "A":
                newCol = currentCol - 1;
                break;
            default:
                System.out.println("Invalid input; please enter W/S/A/D to move.");
                return false;
        }
        
        if (newRow < 0 || newRow >= world.getHeight() || newCol < 0 || newCol >= world.getWidth()) {
            return false;
        }
        if (!world.getSpace(newRow, newCol).isAccessible()) {
            return false;
        }
        
        monster.setPosition(newRow, newCol);
        System.out.println(monster.getName() + " moves to (" + newRow + ", " + newCol + ").");
        return true;
    }

    public static boolean removeObstacle(Hero hero, String direction, World world) {
        int currentRow = hero.getRow();
        int currentCol = hero.getCol();
        int targetRow = currentRow;
        int targetCol = currentCol;
        
        switch(direction.toUpperCase()) {
            case "W":
                targetRow = currentRow - 1;
                break;
            case "S":
                targetRow = currentRow + 1;
                break;
            case "D":
                targetCol = currentCol + 1;
                break;
            case "A":
                targetCol = currentCol - 1;
                break;
            default:
                System.out.println("Invalid input; please enter W/S/A/D to move");
                return false;
        }
        
        if (targetRow < 0 || targetRow >= world.getHeight() ||
            targetCol < 0 || targetCol >= world.getWidth()) {
            System.out.println("Target cell is out of bounds.");
            return false;
        }
        
        Space targetSpace = world.getSpace(targetRow, targetCol);
        if (targetSpace instanceof ObstacleSpace) {
            ObstacleSpace obstacle = (ObstacleSpace) targetSpace;
            if (!obstacle.isAccessible()) {
                obstacle.removeObstacle();
                return true;
            } else {
                System.out.println("The obstacle has already been removed.");
                return false;
            }
        } else {
            System.out.println("There is no obstacle in that direction.");
            return false;
        }
    }

    public static HashMap<Integer, String> getTelportableSpaces(Hero target, World world, Party party){
        ArrayList<String> availableSpaces = new ArrayList<>();
        HashMap<Integer, String> availableSpacesWOnull = new HashMap<Integer, String>();

        int targetRow = target.getRow();
        int targetCol = target.getCol();

        if(targetRow + 1 < world.getHeight() && world.getSpace(targetRow+1, targetCol).isNotOccupiedbyHero(party.getHeroes())){
            String grid = "(" + (targetRow + 1) + ", " + targetCol + ")";
            availableSpaces.add(grid);
        }

        if(targetCol-1 >= 0 && world.getSpace(targetRow, targetCol-1).isNotOccupiedbyHero(party.getHeroes())){
            String grid = "(" + targetRow + ", " + (targetCol - 1) + ")";
            availableSpaces.add(grid);
        }

        if(targetCol+1 < world.getHeight() && world.getSpace(targetRow, targetCol+1).isNotOccupiedbyHero(party.getHeroes())){
            String grid = "(" + targetRow + ", " + (targetCol + 1) + ")";
            availableSpaces.add(grid);
        }

        int count = 1;
        System.out.println("Choose from the followng available spaces: ");
        for(int i = 0; i < availableSpaces.size(); i++){
            if(availableSpaces.get(i) != null){
                System.out.print(count + ": ");
                System.out.println(availableSpaces.get(i));
                availableSpacesWOnull.put(count, availableSpaces.get(i));
                count++;
            }
        }

        return availableSpacesWOnull;
    }
}
