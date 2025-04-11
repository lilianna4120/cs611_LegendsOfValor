import java.util.List;

public class ArmorLoader extends Loader<Armor> {

    // overriden method to parse when reading armor file
    @Override
    protected Armor parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 4) {
            String name = parts[0];
            int cost = Integer.parseInt(parts[1]);
            int reqLevel = Integer.parseInt(parts[2]);
            double damageReduction = Double.parseDouble(parts[3]);
            return new Armor(name, cost, reqLevel, 10, damageReduction);
        }
        return null;
    }

    // to load armory items
    public static List<Armor> loadArmoryItems(String filename) {
        return new ArmorLoader().loadItemsFromFile(filename);
    }
}
