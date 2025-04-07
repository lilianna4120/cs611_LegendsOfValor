public class MarketTile extends Tile {
    // where the market is in the world map
    // for an easier access
    private final String key;

    public MarketTile(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public char getSymbol() {
        return 'M';
    }
}
