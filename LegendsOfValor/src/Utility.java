public class Utility {
    public static final String RESET  = "\u001B[0m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String CYAN   = "\u001B[36m";

    public static String createBar(double current, double max, int length, String color) {
        int filled = (int) ((current / max) * length);
        if (filled > length) {
            filled = length;
        }
        int empty = length - filled;
        StringBuilder bar = new StringBuilder();
        bar.append(color);
        for (int i = 0; i < filled; i++) {
            bar.append("|");
        }
        bar.append(RESET);
        for (int i = 0; i < empty; i++) {
            bar.append(".");
        }
        return bar.toString();
    }
}