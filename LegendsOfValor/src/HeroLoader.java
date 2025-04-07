import java.util.*;

public class HeroLoader extends Loader<Hero> {
    @Override
    protected Hero parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length < 7) return null;
        String name = parts[0];
        int mana = Integer.parseInt(parts[1]);
        int strength = Integer.parseInt(parts[2]);
        int agility = Integer.parseInt(parts[3]);
        int dexterity = Integer.parseInt(parts[4]);
        int gold = Integer.parseInt(parts[5]);
        int experience = Integer.parseInt(parts[6]);

        return new Warrior(name, mana, strength, agility, dexterity, gold, experience);
    }

    public List<Hero> loadAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Warriors.txt"));
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Sorcerers.txt"));
        heroes.addAll(loadItemsFromFile("LegendsOfValor/src/Paladins.txt"));
        return heroes;
    }
}
