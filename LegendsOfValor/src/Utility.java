public class Utility {
    public static final String RESET  = "\u001B[0m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String CYAN   = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

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