classDiagram
direction BT
class Armor {
  + Armor(String, int, int, int, double) 
  - double damageReduction
  + getDamageReduction() double
  + display() void
}
class ArmorLoader {
  + ArmorLoader() 
  # parseLine(String) Armor
  + loadArmoryItems(String) List~Armor~
}
class BushSpace {
  + BushSpace(int, int) 
  - int bonus
  + getSymbol() char
  + onEnter(Hero) void
  + isAccessible() boolean
  + display() void
  + onExit(Hero) void
}
class CaveSpace {
  + CaveSpace(int, int) 
  - int bonus
  + onEnter(Hero) void
  + onExit(Hero) void
  + getSymbol() char
  + display() void
  + isAccessible() boolean
}
class Characters {
  + Characters(String, int) 
  # int level
  # String name
  # double hp
  + getMaxHP() double
  + attack() double
  + getName() String
  + getHp() double
  + getLevel() int
  + displayInfo() void
  + isAlive() boolean
  + setHp(double) void
  + takeDamage(double) void
}
class Dragon {
  + Dragon(String, int, double, double, double) 
}
class Exoskeleton {
  + Exoskeleton(String, int, double, double, double) 
}
class Game {
  + Game() 
  - Scanner scanner
  - int round
  - Party party
  - World world
  - int monstersNum
  + start() void
  - heroMove(Hero) void
  - heroUsePotion(Hero) void
  - processMonsterTurn(Monster) void
  - processHeroTurn(Hero) void
  - checkWinCondition() boolean
  + setupParty() void
  - heroCastSpell(Hero) void
  - heroAttack(Hero) void
  - heroTeleport(Hero) void
  - getHeroInRange(Monster) Hero?
  - isInRange(Hero, Monster) boolean
  + printInstructions() void
  - heroChangeWeaponOrArmor(Hero) void
  + spawnMonsters() void
  - getMonsterInRange(Hero) Monster?
}
class Hero {
  + Hero(String) 
  # double strength
  # double agility
  # int gold
  # List~Item~ inventory
  # int experience
  # Weapon equippedWeapon
  # double dexterity
  # String nickname
  # Armor equippedArmor
  # int row
  # int col
  # double mp
  + setNickname(String) void
  + attack() double
  + getMaxMP() double
  + getCol() int
  + getPosition() int[]
  + getRow() int
  + recall(int) void
  + getExperience() int
  + setPosition(int, int) void
  + setMP(Double) void
  + removeBonus(String, int) void
  + printInventory() void
  + regains() void
  + teleport(Hero, Hero, World, Party) void
  + levelUp() void
  + getDexterity() double
  + equipItem(Item) void
  + getGold() int
  + getLane() int
  + usePotion(Potion) void
  + respawn() void
  + getNickname() String
  + getStrength() double
  + setGold(int) void
  + displayInfo() void
  + setExperience(int) void
  + gain(Monster) void
  + applyBonus(String, int) void
  + addItem(Item) void
  + getMP() double
  + setHP(Double) void
  + getInventory() List~Item~
  + getAgility() double
}
class HeroLoader {
  + HeroLoader() 
  # parseLine(String, String) Hero
  + loadAllHeroes() List~Hero~
  # parseLine(String) Hero
}
class InaccessibleSpace {
  + InaccessibleSpace(int, int) 
  + onEnter(Hero) void
  + isAccessible() boolean
  + display() void
  + onExit(Hero) void
  + getSymbol() char
}
class Item {
  + Item(String, int, int, int) 
  # int uses
  # int maxUses
  # String name
  # int requiredLevel
  # int price
  + getName() String
  + getPrice() int
  + isUsable() boolean
  + display() void
  + getUses() int
  + use() void
  + repair() void
  + getRequiredLevel() int
}
class KoulouSpace {
  + KoulouSpace(int, int) 
  - int bonus
  + display() void
  + getSymbol() char
  + onEnter(Hero) void
  + isAccessible() boolean
  + onExit(Hero) void
}
class Loader~T~ {
  + Loader() 
  # loadItemsFromFile(String) List~T~
  # parseLine(String, String) T
  # loadItemsFromFile(String, String) List~T~
  # parseLine(String) T
}
class Main {
  + Main() 
  + main(String[]) void
}
class MarketSpace {
  + MarketSpace(int, int) 
  - List~Item~ itemsForSale
  + showItems() void
  + enterMarket(Hero, Party, World) void
}
class Monster {
  + Monster(String, int, double, double, double) 
  # int col
  # double defense
  # int row
  # double baseDamage
  # double dodgeChance
  # String nickname
  + getNickname() String
  + displayInfo() void
  + getRow() int
  + setPosition(int, int) void
  + attack() double
  + takeDefense(double) void
  + takeDodgeChange(double) void
  + setNickname(String) void
  + isAlive() boolean
  + getDodge() double
  + getCol() int
  + getDefense() double
  + takeDamage(double) void
  + getDamage() double
}
class MonsterLoader {
  + MonsterLoader() 
  # parseLine(String, String) Monster
  # parseLine(String) Monster
  + loadAllMonsters() List~Monster~
  + generateThreeMonsters(int) List~Monster~
}
class MovementUtil {
  + MovementUtil() 
  + moveMonster(Monster, String, Party, World) boolean
  + getTelportableSpaces(Hero, World, Party) HashMap~Integer, String~
  + removeObstacle(Hero, String, World) boolean
  + moveHero(Hero, String, Party, World) boolean
  + printTeleportableSpaces(HashMap~Integer, String~) void
}
class NexusSpace {
  + NexusSpace(int, int) 
  + onExit(Hero) void
  + getSymbol() char
  + display() void
  + onEnter(Hero) void
  + isAccessible() boolean
}
class ObstacleSpace {
  + ObstacleSpace(int, int) 
  - boolean cleared
  + onExit(Hero) void
  + isAccessible() boolean
  + onEnter(Hero) void
  + display() void
  + removeObstacle() void
  + getSymbol() char
}
class Paladin {
  + Paladin(String, int, int, int, int, int, int) 
  + attack() double
  + levelUp() void
}
class Party {
  + Party() 
  - List~Monster~ monsters
  - List~Hero~ deadHeroes
  - List~Hero~ heroes
  + removeMonster(Monster) void
  + getMonsters() List~Monster~
  + displayInfo() void
  + addMonster(Monster) void
  + getHeroes() List~Hero~
  + getHerobyNickname(String) Hero
  + addHero(Hero) void
  + heroesDefeated() boolean
  + addDeadHero(Hero) void
  + getDeadHeros() List~Hero~
}
class PlainSpace {
  + PlainSpace(int, int) 
  + display() void
  + getSymbol() char
  + isAccessible() boolean
  + onEnter(Hero) void
  + onExit(Hero) void
}
class Position {
  + Position(int, int) 
  - int row
  - int col
  + getCol() int
  + getRow() int
  + setRow(int) void
  + setCol(int) void
}
class Potion {
  + Potion(String, int, int, int, String, double) 
  - String effectType
  - double effectAmount
  + getEffectType() String
  + display() void
  + getEffectAmount() double
}
class PotionLoader {
  + PotionLoader() 
  # parseLine(String) Potion
  + loadPotionItems(String) List~Potion~
}
class Sorcerer {
  + Sorcerer(String, int, int, int, int, int, int) 
  + levelUp() void
  + attack() double
}
class Space {
  + Space(int, int) 
  # int col
  # int row
  + display() void
  + isNotOccupiedbyHero(List~Hero~) boolean
  + isAccessible() boolean
  + getSymbol() char
  + isNotOccupiedbyMonster(List~Monster~) boolean
  + onExit(Hero) void
  + onEnter(Hero) void
}
class Spell {
  + Spell(String, int, int, int, double, double, String) 
  - String spellType
  - double manaCost
  - double damage
  + getSpellType() String
  + setType(String) void
  + display() void
  + getDamage() double
  + getManaCost() double
}
class SpellLoader {
  + SpellLoader() 
  # parseLine(String) Spell
  # parseLine(String, String) Spell
  + loadSpellItems(String, String) List~Spell~
}
class Spirit {
  + Spirit(String, int, double, double, double) 
}
class Utility {
  + Utility() 
  + String RED_BACKGROUND
  + String PURPLE
  + String YELLOW_BOLD_BRIGHT
  + String PURPLE_BACKGROUND
  + String BLUE_BACKGROUND
  + String PURPLE_BOLD_BRIGHT
  + String BLACK_BOLD_BRIGHT
  + String WHITE
  + String RED
  + String CYAN_BOLD_BRIGHT
  + String CYAN
  + String GREEN_BACKGROUND
  + String RED_BOLD_BRIGHT
  + String BLUE_BOLD_BRIGHT
  + String YELLOW
  + String CYAN_BACKGROUND
  + String GREEN_BOLD_BRIGHT
  + String BLACK_BACKGROUND
  + String RESET
  + String PURPLE_BACKGROUND_BRIGHT
  + String BLUE
  + String GREEN
  + String WHITE_BOLD_BRIGHT
  + String WHITE_BACKGROUND
  + String YELLOW_BACKGROUND
  + createBar(double, double, int, String) String
}
class Warrior {
  + Warrior(String, int, int, int, int, int, int) 
  + levelUp() void
  + attack() double
}
class Weapon {
  + Weapon(String, int, int, int, double, int) 
  - double damage
  - int handsRequired
  + getDamage() double
  + display() void
  + getHandsRequired() int
}
class WeaponLoader {
  + WeaponLoader() 
  # parseLine(String) Weapon
  + loadWeaponItems(String) List~Weapon~
}
class World {
  + World(int, int) 
  - Map~String, NexusSpace~ map
  - Space[][] grid
  - int height
  - int width
  + getSpace(int, int) Space
  + printMap(Party) void
  + getWidth() int
  + getHeight() int
  - randomlyAssign(Random, int, int) Space
  - generateWorld() void
  + display(Party) void
}

Armor  -->  Item 
ArmorLoader  -->  Loader~T~ 
BushSpace  -->  Space 
CaveSpace  -->  Space 
Dragon  -->  Monster 
Exoskeleton  -->  Monster 
Hero  -->  Characters 
HeroLoader  -->  Loader~T~ 
InaccessibleSpace  -->  Space 
KoulouSpace  -->  Space 
Monster  -->  Characters 
MonsterLoader  -->  Loader~T~ 
NexusSpace  -->  Space 
ObstacleSpace  -->  Space 
Paladin  -->  Hero 
PlainSpace  -->  Space 
Potion  -->  Item 
PotionLoader  -->  Loader~T~ 
Sorcerer  -->  Hero 
Spell  -->  Item 
SpellLoader  -->  Loader~T~ 
Spirit  -->  Monster 
Warrior  -->  Hero 
Weapon  -->  Item 
WeaponLoader  -->  Loader~T~ 
