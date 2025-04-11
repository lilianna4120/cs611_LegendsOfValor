# Game Design

# Overview

This project implements a role-playing game “Legends of Valor” in Java.
The game simulates a world represented as a grid of spaces (bush, cave, koulou, inaccessible, nexus, obstacle, plain) in which a a party of 3 heroes independently performs a variety of actions.

Heroes gain experience and gold when they defeat monsters and level up. The design emphasizes scalability, extendibility, and good object-oriented practices.
It is designed using Object-Oriented Programming principles to provide modularity and ease of extension and this document describes the major components, their responsibilities, and how they interact.

A separate UML diagram has been included in the submission.

--------

# Design Evaluation
We opted to build our Legends of Valor game off of Lily's Monsters and Heroes. Her code provided a better base to build off of. Her abstract superclasses and their subclasses allowed us to easily create new functionality for our `Character`s, `Item`s, etc. And her abstract `Space` allowed us to easily extend to additional `Space` subclasses for the new types of spaces included in Legends of Valor. And her use of `Loader`s follows the factory pattern and provided and clean way to read in all text files, which could easily be extended if we were given additional heroes, monsters, or items. 

# Class Structure

Abstract Classes:
- Characters.java: Contains the abstract Characters class which provides the base functionality for all game characters.
    - public abstract double attack(); public abstract void displayInfo();;

- Hero.java: Base class for all hero types. Encapsulates shared attributes like name, mana, strength, dexterity, agility, money, experience, level, and inventory.
    - public abstract double attack(); public void displayInfo()

- Monster.java: Base class for all monster types. Contains stats like name, level, damage, defense, and dodge chance.
    - public double attack(); public void takeDamage(double damage); public void displayInfo()

- Item.java: Parent class for all inventory items. Stores name, price, required level, uses, etc.
    - public abstract void display()

- Space.java: Represents a space on the map. Allows heroes to enter or interact with the space.
    - public abstract boolean isAccessible(); public abstract void display()

- Loader.java: An abstract generic class that provides file-loading and parsing functionalities.
    - protected List<T> loadItemsFromFile(String filename); protected List<T> loadItemsFromFile(String filename, String type); protected abstract T parseLine(String line); protected T parseLine(String line, String type)

Concrete Game Classes:
- Heroes: Warrior.java, Sorcerer.java, Paladin.java
- Monsters: Dragon.java, Spirit.java, Exoskeleton.java
- Items: Weapon.java, Armor.java, Potion.java, Spell.java
- Space: BushSpace.java, CaveSpace.java, InaccessibleSpace.java, KoulouSpace.java, NexusSpace.java, ObstacleSpace.java, PlainSpace.java
- Tile: MarketTile.java, CommonTile.java, InaccessibleTile.java
- Loader.java: HeroLoader.java, MonsterLoader.java, ArmorLoader.java, PotionLoader.java, SpellLoader.java, WeaponLoader.java

Supporting Classes
- Main.java: Provides a starting point to `Game`.
- Game.java: Includes all main logic and functions necessary to play Legends of Valor.
- Party.java: A group of `Hero` objects that act together. Responsible for movement, turn coordination, and revival.
- World.java: Contains 2D array consisting of `Space` object and methods to display it. 
- MarketSpace.java: Creates list of items to be sold to `Hero` and provides all functionality of a market.
- Position.java: Holds the row and column associated with an element's location within `World`.
- MovementUtil.java: Handles various types of movement actions for `Hero` and `Monster`.
- Utility.java: Provides programmer with String constants for a variety of colors.

--------

# Scalability and Extendibility

- The game is designed around superclasses such as `Hero`, `Monster`, `Item`, `Loader`, `Space`, etc. , which encapsulate shared behavior and data.
- Game elements like different hero and monster types, or item subclasses, can be added with minimal code changes by simply extending the appropriate superclass.
- The use of loader classes (`HeroLoader`, `MonsterLoader`, `ArmorLoader`, `PotionLoader`, `SpellLoader`, `WeaponLoader`) separates file reading logic from game logic, promoting clean separation of concerns.

--------

# Inheritance Hierarchy
This inheritance hierarchy minimizes code duplication and enables polymorphic behavior

- `Character` is an abstract class extended by `Hero` and `Monster`, each implementing attack() and displayInfo() differently and adding their own unique attributes and methods. 
- `Hero` is an abstract class extended by `Warrior`, `Sorcerer`, and `Paladin`, each implementing unique attribute scaling and specialization.
- `Monster` is extended by `Dragon`, `Spirit`, and `Exoskeleton`, encapsulating specialized behavior.
- `Item` is extended by `Weapon`, `Armor`, `Potion`, and `Spell`, each implementing behavior specific to their type while sharing common interfaces.
- `Space` is extended by `BushSpace.java`, `CaveSpace.java`, `InaccessibleSpace.java`, `KoulouSpace.java`, `NexusSpace.java`, `ObstacleSpace.java`, and `PlainSpace.java`, each implementing abstract methods to suit their own needs. 
- `Loader` is extended by `HeroLoader.java`, `MonsterLoader.java`, `ArmorLoader.java`, `PotionLoader.java`, `SpellLoader.java`, and `WeaponLoader.java`, each overriding abstract methods anc crating their own loading method.


--------

# Map and Space Abstraction

- `World` contains a grid-based board which is modeled using a 2D array of `Space` objects.
- `Space` is an abstract class extended by `BushSpace.java`, `CaveSpace.java`, `InaccessibleSpace.java`, `KoulouSpace.java`, `NexusSpace.java`, `ObstacleSpace.java`, and `PlainSpace.java`, allowing specialized behavior for each Space type.

--------

# Composition and Encapsulation

- Each `Hero` or `Monster` object contains its own stats, inventory, and methods for attacking, taking damage, and leveling up.
- The `Party` class acts as a team composition manager and encapsulates a two separate lists of heroes with shared methods like healing and reviving as well as a list of monsters with shared methods.
- The `MovementUtil` class manages various movements of `Hero`es throughout `World`'s grid and encapsulates all turn-based move logic, enabling modular testing and future enhancement.

--------

# Design Patterns

- Factory Pattern: File loaders like `HeroLoader` and `MonsterLoader` act as factories that instantiate subclasses based on file contents.

--------

# Maintainability and Reusability

- Adding a new item or character class does not require touching battle logic or other code other than that certain character class.
- The modular design allows components like `Space`, `Party`, `Character`, and `Item` to be reused in different types of games or future expansions.
- The code is organized such that classes have a single responsibility, and behaviors are delegated to the appropriate objects.

--------

The design of this framework relies on a well-structured hierarchy where common game logic is abstracted into base classes and specialized behaviors are implemented in subclasses. By using inheritance, composition and helper methods, the design reamins both scalable and extendible. New features can be added without rewriting or breaking existing code, making it easy to scale and adapt for future extensions.
