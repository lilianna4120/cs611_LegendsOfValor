/*
 * MonsterLoader.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that loads in a list of Monster objects from specified files.
 * Extends Loader and replaces the generic type with Monster.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterLoader extends Loader<Monster> {

    // overriden method to parse when reading monster files
    protected Monster parseLine(String line, String type) {
        String[] parts = line.split("\\s+");
        if (parts.length < 5) return null;
        
        String name = parts[0];
        int level = Integer.parseInt(parts[1]);
        double damage = Double.parseDouble(parts[2]);
        double defense = Double.parseDouble(parts[3]);
        double dodge = Double.parseDouble(parts[4]);
        
        if ("Dragon".equalsIgnoreCase(type)) {
            return new Dragon(name, level, damage, defense, dodge);
        } else if ("Exoskeleton".equalsIgnoreCase(type)) {
            return new Exoskeleton(name, level, damage, defense, dodge);
        } else if ("Spirit".equalsIgnoreCase(type)) {
            return new Spirit(name, level, damage, defense, dodge);
        }
        return null;
    }
    
    // override the default parseLine function
    @Override
    protected Monster parseLine(String line) {
        return null;
    }

    // to load weapon items
    public List<Monster> loadAllMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.addAll(loadItemsFromFile("Dragons.txt", "Dragon"));
        monsters.addAll(loadItemsFromFile("Exoskeletons.txt", "Exoskeleton"));
        monsters.addAll(loadItemsFromFile("Spirits.txt", "Spirit"));
        return monsters;
    }

    // to generate monster in each lane
    public static List<Monster> generateThreeMonsters(int level){
        MonsterLoader mLoader = new MonsterLoader();
        List<Monster> allMonsters = mLoader.loadAllMonsters();
        List<Monster> threeMonsters = new ArrayList<>();

        int monsterSize = allMonsters.size();
        int i = 0;
        while(i < 3) {
            Random rand = new Random();
            int randint = rand.nextInt(monsterSize);
            Monster m = allMonsters.get(randint);
            // Monster base = availableMonsters.get(i);
            // // Use base.getClass() to determine the type.
            // String newName = base.getName();
            // Monster m = generateMonster(newName, level);
            // threeMonsters.add(m);
            if(m.getLevel() == level) {
                threeMonsters.add(m);
                i++;
            }
        }
        return threeMonsters;
    }
}
