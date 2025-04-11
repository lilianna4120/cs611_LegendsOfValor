import java.util.List;

public class SpellLoader extends Loader<Spell> {
    // overriden method to parse when reading spell files
    @Override
    protected Spell parseLine(String line) {
        return parseLine(line, null);
    }

    @Override
    protected Spell parseLine(String line, String spellType) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 5) {
            String name = parts[0];
            int cost = Integer.parseInt(parts[1]);
            int reqLevel = Integer.parseInt(parts[2]);
            double damage = Double.parseDouble(parts[3]);
            double manaCost = Double.parseDouble(parts[4]);
            return new Spell(name, cost, reqLevel, 5, damage, manaCost, spellType);
        }
        return null;
    }

    // to load spell items
    public static List<Spell> loadSpellItems(String filename, String spellType) {
        return new SpellLoader().loadItemsFromFile(filename, spellType);
    }
}

