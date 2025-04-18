/*
 * WeaponLoader.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Class that loads in a list of Weapon objects from passed in files.
 * Extends Loader and replaces the generic type with Weapon.
 */
import java.util.List;

public class WeaponLoader extends Loader<Weapon> {
    // overriden method to parse when reading weapon files
    @Override
    protected Weapon parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 5) {
            String name = parts[0];
            int cost = Integer.parseInt(parts[1]);
            int reqLevel = Integer.parseInt(parts[2]);
            double damage = Double.parseDouble(parts[3]);
            int handsRequired = Integer.parseInt(parts[4]);
            return new Weapon(name, cost, reqLevel, 7, damage, handsRequired);
        }
        return null;
    }

    // to load weapon items
    public static List<Weapon> loadWeaponItems(String filename) {
        return new WeaponLoader().loadItemsFromFile(filename);
    }
}
