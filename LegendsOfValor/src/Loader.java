import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Loader<T> {
    // to load general items
    protected List<T> loadItemsFromFile(String filename) {
        List<T> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                T item = parseLine(scanner.nextLine().trim());
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load " + filename);
        }
        return items;
    }
    
    // overloaded loader that accepts an extra type parameter
    protected List<T> loadItemsFromFile(String filename, String type) {
        List<T> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                T item = parseLine(scanner.nextLine().trim(), type);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load " + filename);
        }
        return items;
    }
    
    // abstract method for parsing a line (without extra info)
    protected abstract T parseLine(String line);
    
    // overloaded parseLine method that takes a type parameter
    protected T parseLine(String line, String type) {
        return parseLine(line);
    }
}
