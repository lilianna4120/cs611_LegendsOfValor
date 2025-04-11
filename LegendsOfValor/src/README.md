# CS611-Assignment < 5 >

## Legends of Valor

---

### Student Information
- **Name:**  Lily Jihyun Son
- **Email:**  lilyson@bu.edu
- **Student ID:**  U37941259

---

## Files

List of source code files included in this project:

Main.java                - The entry point of the program.
Game.java                – Main class (entry point), handles game loop and user input. It also sets up the party of hereoes, print instructions, and display summary when the game ends.
World.java               – Represents the game world (grid of tiles).
Tile.java and subclasses (CommonTile.java, MarketTile.java, InaccessibleTile.java) – Represents a space in the grid .
Party.java               – Represents the group of heroes.
Characters.java          - Contains the abstract Characters class which provides the base functionality for all game characters. Attack and displayInfo function is abstract class since they are different by monsters and heroes.
Hero.java and subclasses – Base class for heroes with derived classes: Warrior, Sorcerer, Paladin. Contains the abstract Hero class with methods for leveling up, displaying information, inventory management, and item usage. Attack method is overridden in subclasses.
Monster.java and subclasses – Base class for monsters with derived classes: Dragon, Exoskeleton, Spirit. Contains the abstract Monster class with methods for attacking, taking damage, and displaying information. 
Space.java               - Contains the abstract Space class with definitions for location, accessibility, event handling, and display methods.
MarketSpace.java         – Represents a market; handles buying, selling, repairing items.
Item.java and subclasses (Weapon.java, Armor.java, Spell.java, Potion.java) – Contains the abstract Item class that provides the base properties and methods for game items.
Battle.java              – Handles battle logic between heroes and monsters.
Loader.java              - An abstract generic class that provides file-loading and parsing functionalities.
ItemLoader.java          - Contains static methods to load different item types.
Pair.java                - Represents a combat encounter between a hero and a monster.
Dice.java                - Provides a random chance mechanism useful for various game events.
Utility.java             -  Provides helper methods for creating progress bars and managing colored output for enhanced user interface displays.

---

## Notes

This project showcases object-oriented programming concepts such as inheritance and polymorphism.  
Key design decisions and optimizations include:

- Inheritance & Code Reusability: Common functionalities are abstracted in `Hero.java`, `Monster.java`, `Item.java`, `Loader.java`, and `Space.java`.
- Separation: Each game has its own dedicated files, improving modularity.
- Scalability: New New heroes, monsters, items, loader for each class, space can be extended from each superclass.
- For the custom world, maximum size is 10x10
- I thought about subdividing Potion, Spells, Weapon and Armor, but since the approach that I took did not have that many shared functions, I thought there wouldn't be that much advantages doing that so I decided to not subdivide those methods.
- For the battle, when the heroes' level is low, it is more likely that the battle will end in one round. However, if the heroes' level gets higher, it is more likely that the battle will be longer. 
- When asking for user inputs, the index usually starts at 0, and -1 means cancel or exit
- All the txt files about items, monsters, and heroes has to be in the same directory as java files to run this game.

Game Controls (instructions are given all the time):
- W/w: Move up
- A/a: Move left
- S/s: Move down
- D/d: Move right
- STATS/stats: Display statistics of heroes and monsters
- M/m: Enter market (if on a market tile)
- INV/inv: Display inventory. Heroes could equip items
- MAP/map: display World map
- Q/q: Quit game

---

## How to Compile and Run

First, cd into the directory in which the Main.java is, then follow the following steps.

- Compile the code using: javac *.java
- Run the program: java Main

Once it runs, follow the instructions that are given.

---

## Input/Output Example
Since the example became so long, I will put shortened example:
Let's play Monsters and Heroes !
First, select your World (if you press any other invalid input, it will automatically choose the default world): 
1. Default world (8x8)
2. Custom World
1
Available Heroes:
Select hero index to add to your party (max 3). Enter -1 to finish: 0
Select hero index to add to your party (max 3). Enter -1 to finish: 1
Select hero index to add to your party (max 3). Enter -1 to finish: -1
Party setup complete!
 -- INSTRUCTION -- 
 -- WORLD MAP -- 
Enter W/A/S/D to move, I for instruction, STATS for characters' statistics, M for market, INV for inventory, MAP for the map, and Q to quit ! 
Party moved to (5, 2)
Battle begins ! 
Gaerdal_Ironhand's turn. Choose action:
1. Attack   2. Cast Spell   3. Use Potion   4. Show Stats   Q. Quit
1
 -- BATTLE GOES ON -- 
Enter W/A/S/D to move, I for instruction, STATS for characters' statistics, M for market, INV for inventory, MAP for the map, and Q to quit ! m
Welcome to the Market!

Market Menu: 1. Buy  2. Sell  3. Repair  4. Exit
 -- BUY/SELL/REPAIR ITEMS -- 

Enter W/A/S/D to move, I for instruction, STATS for characters' statistics, M for market, INV for inventory, MAP for the map, and Q to quit ! inv
[0]Gaerdal_Ironhand's Inventory:
Invalid Input; Enter index to equip item or -1 to skip:
-1

---

## Dependencies and Requirements

List any required software, libraries, or configurations.
- Uses standard Java libraries such as java.util.Scanner, java.util.Random, java.util.ArrayList, java.util.List, etc.;
- Those are all imported in each java file so don't need any external libraries.

---

## Known Issues or Bugs

Document any known bugs, limitations, or unfinished features.
 - Battle ends quickly (either monster or hero wins) when the hero's level is low. When it gets higher, the battle goes for multiple rounds. I tried to try multiple damage/attack range to solve this, but this is the one I found most optimal.
 - Luckily, when I tested all the edges cases(maybe not all the edge cases since there are so many user inputs, but all the ones I tested), I couldn't find one, but please let me know if there is so that I can fix it for the future assignments. Thank you!

---

## Testing Strategy

- Tested with small and large input sizes for integer inputs
- Tested all the edges cases: inputting characters or strings when I have to type integer and vice versa
- Checked behavior with negative numbers or zero
- Checked behavior with special characters
- Tried to check for all the cases that could happen while playing that game including the ones below. However, since there are too many probabilities and possibilities, there might be ones that I was not able 

---

## References and Attribution

List any external sources, books, tutorials, or online resources you used.

- Used the sources below to understand how scanner and inheritance works in Java.
- Also used the sources below to understand how Integer.parseInt() method work and use exceptions.
- Also used to present teminal in different colors
- Additional online resources were consulted to refine object-oriented design and input validation techniques.

Sources:
https://www.w3schools.com/java/java_user_input.asp
https://www.geeksforgeeks.org/inheritance-in-java/
https://www.tpointtech.com/java-integer-parseint-method
https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
