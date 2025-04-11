import java.util.*;

public class HeroLoader extends Loader<Hero> {

    // overriden method to parse when reading hero files
    @Override
    protected Hero parseLine(String line) {
        return parseLine(line, null);
    }

    @Override
    protected Hero parseLine(String line, String type) {
        String[] parts = line.split("\\s+");
        if (parts.length < 7) return null;
        String name = parts[0];
        int mana = Integer.parseInt(parts[1]);
        int strength = Integer.parseInt(parts[2]);
        int agility = Integer.parseInt(parts[3]);
        int dexterity = Integer.parseInt(parts[4]);
        int gold = Integer.parseInt(parts[5]);
        int experience = Integer.parseInt(parts[6]);

        if(type.equalsIgnoreCase("Warriors")){
            return new Warrior(name, mana, strength, agility, dexterity, gold, experience);
        }else if(type.equalsIgnoreCase("Sorcerers")){
            return new Sorcerer(name, mana, strength, agility, dexterity, gold, experience);
        }else{
            return new Paladin(name, mana, strength, agility, dexterity, gold, experience);
        }
        
    }

    // to load hero items
    public List<Hero> loadAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Warriors.txt", "Warriors"));
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Sorcerers.txt", "Sorcerers"));
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Paladins.txt", "Paladins"));
        return heroes;
    }
}
