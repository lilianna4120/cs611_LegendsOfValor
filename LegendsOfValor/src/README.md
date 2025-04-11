# CS611-Assignment < 5 >

## Legends of Valor

---

### Student Information
- **Name:**  Lily Jihyun Son, Grace Elias
- **Email:**  lilyson@bu.edu; gelias@bu.edu

---

## Files

List of source code files included in this project:

Main.java                   - The entry point of the program.
Game.java                   – Main class (entry point), handles game loop and user input. It also sets up the party of hereoes, print instructions, and display summary when the game ends. Class that contains all necessary logic and methods to play a Game of Legends of Valor.
World.java                  – Represents the game world (grid of tiles).
Party.java                  – Represents the group of heroes and monsters.
Characters.java             - Contains the abstract Characters class which provides the base functionality for all game characters(heroes and monsters). Attack and displayInfo function is abstract class since they are different by monsters and heroes.
Hero.java and subclasses    – Base class for heroes with derived classes: Warrior, Sorcerer, Paladin. Contains the abstract Hero class with methods for leveling up, displaying information, inventory management, and item usage. Attack method is overridden in subclasses.
Monster.java and subclasses – Base class for monsters with derived classes: Dragon, Exoskeleton, Spirit. Contains the abstract Monster class with methods for attacking, taking damage, and displaying information. 
Space.java and subclasses   - Contains the abstract Space class with definitions for location, accessibility, event handling, and display methods. (BushSpace.java, CaveSpace.java, InaccessibleSpace.java, KoulouSpace.java, NexusSpace.java, ObstacleSpace.java, and PlainSpace.java)
Space.java                  - Contains the abstract Space class with definitions for location, accessibility, event handling, and display methods.
Item.java and subclasses    – Contains the abstract Item class that provides the base properties and methods for game items. (Weapon.java, Armor.java, Spell.java, Potion.java)
Loader.java and subclasses  - An abstract generic class that provides file-loading and parsing functionalities. (ArmorLoader.java, HeroLoader.java, MonsterLoader.java, PotionLoader.java, SpellLoader.java, and WeaponLoader.java)
Utility.java                -  Provides helper methods for creating progress bars and managing colored output for enhanced user interface displays.
MovementUtil.java           -  contains methods for types of movements of heroes and monsters can make
Position.java               -  represents a position of an object in the World.

---

## Notes

This project showcases object-oriented programming concepts such as inheritance and polymorphism.  
Key design decisions and optimizations include:

- Inheritance & Code Reusability: Common functionalities are abstracted in `Hero.java`, `Monster.java`, `Item.java`, `Loader.java`, `Characters.java`, `Loader.java`, and `Space.java`.
- Separation: Each game has its own dedicated files, improving modularity.
- Scalability: New heroes, monsters, items, loader for each class, and space types can be easily extended from respective superclass.
- For the custom world, maximum size is 10x10
- I thought about subdividing Potion, Spells, Weapon and Armor, but since the approach that I took did not have that many shared functions, I thought there wouldn't be that much advantages doing that so I decided to not subdivide those methods.
- When asking for user inputs, the index usually starts at 0, and -1 means cancel or exit
- All the txt files about items, monsters, and heroes has to be in the same directory as java files to run this game.

Game Controls (instructions are given all the time):
- W/w: Move up
- A/a: Move left
- S/s: Move down
- D/d: Move right
- STATS/stats: Display statistics of heroes and monsters
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
Available Heroes:
--- HEROES INFORMATION ---

Welcome to Legends of Valor!
Instructions are to follow, but
You'll now choose 3 of the above heroes to add to your party.
Select hero index to add to your party: 1
H1 assigned (7,0)
Select hero index to add to your party: 2
H2 assigned (7,3)
Select hero index to add to your party: 3
H3 assigned (7,6)
Finished setting up the party !

--- INSTRUCTIONS ---
Round 1
--- WORLD MAP ---
H1 is in Heroes' Nexus space. Do you want to enter a market ? (Y/N) (Any invalid inputs will considered No) n
Not entering market.

H1's turn. Choose an action:
1. Move  2. Attack  3. Use Potion  4. Teleport  5. Recall  6. Remove Obstacle  7. Cast Spell 8. Change Weapon/Armor Q. Quit 
1. Move  2. Attack  3. Use Potion  4. Teleport  5. Recall  6. Remove Obstacle  7. Cast Spell 8. Change Weapon/Armor Q. Quit 
1
Enter W/A/S/D to move: w

--- CORRESPONDING ACTIONS HAPPENS ---

H1 is in Heroes' Nexus space. Do you want to enter a market ? (Y/N) (Any invalid inputs will considered No) y

Entering a market !
Welcome to the Market!

Market Menu: 1. Buy  2. Sell  3. Repair  4. Exit
1
--- AVAILBLE ITEMS ---
Enter the index of the item to buy (or type 'exit' to cancel): exit

H1's turn. Choose an action:
1. Move  2. Attack  3. Use Potion  4. Teleport  5. Recall  6. Remove Obstacle  7. Cast Spell 8. Change Weapon/Armor Q. Quit 
q
Quitting the game ...

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
- Checked behavior when characters move out of bounds or move to inaccessible space
- Checked behavior with negative numbers or zero
- Checked behavior with special characters
- Tried to check for all the cases that could happen while playing that game including the ones in the instruction. However, since there are too many probabilities and possibilities, there might be ones that I was not able 

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