import java.util.List;

public class PotionLoader extends Loader<Potion> {
    // overriden method to parse when reading potion file
    @Override
    protected Potion parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 5) {
            String name = parts[0];
            int cost = Integer.parseInt(parts[1]);
            int reqLevel = Integer.parseInt(parts[2]);
            double effectAmount = Double.parseDouble(parts[3]);
            String effectType = parts[4];
            return new Potion(name, cost, reqLevel, 3, effectType, effectAmount);
        }
        return null;
    }

    // to load potion items
    public static List<Potion> loadPotionItems(String filename) {
        return new PotionLoader().loadItemsFromFile(filename);
    }

}
