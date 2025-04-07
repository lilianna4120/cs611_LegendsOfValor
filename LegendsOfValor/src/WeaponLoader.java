import java.util.List;

public class WeaponLoader extends Loader<Weapon> {
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

    public static List<Weapon> loadWeaponItems(String filename) {
        return new WeaponLoader().loadItemsFromFile(filename);
    }
}
