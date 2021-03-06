import java.io.Serializable;
import java.util.ArrayList;

/***
 * This class stores the attributes, items, etc. of the player
 * @author owner
 */
public class Player implements Serializable {
    // Starting gold
    public static final int STARTING_GOLD = 100;

    // Exp for each level
    public static final int LEVEL_EXP[] = {0, 10, 20, 40, 80, 160, 320};

    /**
     * Player name
     */
    protected String name;
    /**
     * Strength affects physical damage and armor
     */
	protected int strength;
    /**
     * Dexterity affects damage, accuracy, and evasion
     */
	protected int dexterity;
    /**
     * Intelligence affects spell damage, MP, and magic resist
     */
	protected int intelligence;
    /**
     * Vitality affects life
     */
	protected int vitality;
    /**
     * Life is based on vitality and other things
     */
	protected int life;
    /**
     * Player's maximum life
     */
    protected int maxLife;
    /**
     * Mana is based on intelligence and other things
     */
    protected int mana;
    /**
     * Player's maximum mana.
     */
    protected int maxMana;
    /**
     * Accuracy is based on dexterity and other things
     */
	protected int attackRating;
    /**
     * Evasion rating is based on dexterity and other things
     */
	protected int defenseRating;
    /**
     * Armor decreases physical damage
     */
	protected int armor;
    /**
     * Magic resist decreases magic damage
     */
	protected int magicResist;
    /**
     * Base damage
     */
	protected int baseDamage;
    /**
     * Base crit chance
     */
    protected double critChance;
    /**
     * base crit damage
     */
    protected double critDamage;
    /**
     * Experience points
     */
	protected int exp;
    /**
     * Character level
     */
	protected int level;
    /**
     * Amount of gold the character has
     */
	protected int gold;
    /**
     * The classes are Warrior, Rogue, and Sorcerer
     */
	protected String characterClass;
    /**
     * Player's inventory
     */
	protected ArrayList<Item> inventory;
    /**
     * Player's equippable items
     */
    protected ArrayList<Equipment> equipment;
    /**
     * Player's amulet
     */
	protected Amulet equippedAmulet;
    /**
     * Player's weapon
     */
	protected Weapon equippedWeapon;
    /**
     * Player's equipped chest armor
     */
    protected ChestArmor chestArmor;
    /**
     * Player's helmet
     */
    protected Helmet equippedHelmet;

    /**
     * Constructor
     */
	public Player() {
		// Set exp and level to 0 and 1
		this.exp = 0;
		this.level = 1;
		
		// Starting gold
		this.gold = STARTING_GOLD;

        // Initialize the equipment ArrayList
        equipment = new ArrayList<Equipment>();

        // Initialize the inventory Arraylist
        inventory = new ArrayList<Item>();

	}
	
	/***
	 * This method calculates the derived stats (accuracy, evasion, etc.) from the base stats (str, agi, etc)
	 * 
	 */
	public void calcStats() {
		// Formula for max life
		maxLife = 10 + 5*vitality;
        // Formula for max mana
        maxMana = 20 + 10*intelligence;
		// Formula for attack rating
		attackRating = 100 + 10*dexterity;
		// Formula for defense rating
		defenseRating = 100 + 5*dexterity;
		// Formula for armor
		armor = 1 + 2*strength;
		// Formula for magic resist
		magicResist = 1 + 2*intelligence;
		// Formula for base damage
		if(characterClass == "Warrior") {
            baseDamage = 5 + 2*strength;
        }
        else if(characterClass == "Rogue") {
            baseDamage = 3 + 2*dexterity;
        }
        else {
            baseDamage = 2 + 1*intelligence;
        }
	}

    /**
     * Levels up the player (overriden by subclasses)
     */
    public void levelUp() {

    }


    /***
     * Getter for life
     * @return The player's current life
     */
    public int getLife() {
        return this.life;
    }

    /***
     * Setter for life
     * @param  life The player's new life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Getter for strength
     * @return The player's current strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Setter for strength
     * @param strength The player's new strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Getter for dexterity
     * @return The player's current dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    /**
     * Getter for intelligence
     * @return The player's current intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    /**
     * Getter for vitality
     * @return The player's current vitality
     */
    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }
    /**
     * Getter for mana
     * @return The player's current mana
     */
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    /**
     * Getter for attack rating
     * @return The player's current attack rating
     */
    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }
    /**
     * Getter for evasion rating
     * @return The player's current evasion rating
     */
    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        this.defenseRating = defenseRating;
    }
    /**
     * Getter for armor
     * @return The player's current armor
     */
    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    /**
     * Getter for magic resist
     * @return The player's current magic resist
     */
    public int getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist = magicResist;
    }
    /**
     * Getter for base damage
     * @return The player's current base damage
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    /**
     * Getter for crit chance
     * @return The player's current crit chance
    */
    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }
    /**
     * Getter for crit damage
     * @return The player's current crit damage
     */
    public double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }
    /**
     * Getter for experience
     * @return The player's current experience
     */
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    /**
     * Getter for gold
     * @return The player's current gold
     */
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    /**
     * Getter for level
     * @return The player's current level
     */
    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}