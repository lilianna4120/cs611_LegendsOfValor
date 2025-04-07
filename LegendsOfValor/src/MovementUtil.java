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
            System.out.println("Invalid input; you can't move out of bounds.");
            return false;
        }
        
        // Check that the target cell is accessible.
        if (!world.getSpace(newRow, newCol).isAccessible()) {
            System.out.println("Invalid input; the cell you are trying to go is not accessible.");
            return false;
        }
        
        // Check that the target cell is not already occupied by another hero.
        for (Hero h : party.getHeroes()) {
            if (h != hero && h.isAlive() && h.getRow() == newRow && h.getCol() == newCol) {
                System.out.println("Invalid input; the cell you are tring to go is already occupied by " + h.getName() + ".");
                return false;
            }
        }
        
        // Check the adjacent cell in the direction of movement for a monster.
        // This prevents a hero from moving behind a monster without defeating it first.
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
            if (m.isAlive() && m.getRow() == adjacentRow && m.getCol() == adjacentCol) {
                System.out.println("A monster (" + m.getName() + ") is blocking the way. You must defeat it first.");
                return false;
            }
        }
        
        // All checks passed; update the hero's position.
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
}
