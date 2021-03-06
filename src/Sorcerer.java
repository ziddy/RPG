import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The sorcerer class
 */
public class Sorcerer extends Player implements Serializable {

    // Sorcerer constants
    public static final int SORCERER_BASE_STR = 2;
    public static final int SORCERER_BASE_DEX = 1;
    public static final int SORCERER_BASE_INT = 5;
    public static final int SORCERER_BASE_VIT = 3;
    public static final double SORCERER_BASE_CRIT_CHANCE = 0;
    public static final double SORCERER_BASE_CRIT_DAMAGE = 0;
    public static final double SORCERER_BASE_DOUBLE_CHANCE = 0.01;

    // Sorcerer stat gains
    public static final int SORCERER_STR_GAIN = 1;
    public static final int SORCERER_DEX_GAIN = 1;
    public static final int SORCERER_INT_GAIN = 4;
    public static final int SORCERER_VIT_GAIN = 2;

    // Sorcerer starting weapon
    public static final int SORCERER_STARTING_WEAPON_MIN_DAMAGE = 1;
    public static final int SORCERER_STARTING_WEAPON_MAX_DAMAGE = 2;
    public static final int SORCERER_STARTING_WEAPON_INTELLIGENCE = 3;
    public static final String SORCERER_STARING_WEAPON_NAME = "Wooden Staff";

    /**
     * Sorcerer has chance to double-cast
     */
    private double doubleChance;

    /**
     * Sorcerer's spells
     */
    protected ArrayList<Spell> spells;

    /**
     * Sets base stats
     */
    public Sorcerer(String playerName) {
        // Call super constructor
        super();

        // Initialize stats
        this.name = playerName;
        this.strength = SORCERER_BASE_STR;
        this.dexterity = SORCERER_BASE_DEX;
        this.intelligence = SORCERER_BASE_INT;
        this.vitality = SORCERER_BASE_VIT;
        this.critChance = SORCERER_BASE_CRIT_CHANCE;
        this.critDamage = SORCERER_BASE_CRIT_DAMAGE;
        this.doubleChance = SORCERER_BASE_DOUBLE_CHANCE;
        this.characterClass = "Sorcerer";

        // Calculate stats
        calcStats();

        // Set life and mana to full
        life = maxLife;
        mana = maxMana;

        // Set up spell list - start with Magic Missile
        this.spells = new ArrayList<Spell>();
        this.spells.add(new MagicMissile());

        // Starting weapon
        Staff startingStaff = new Staff(SORCERER_STARTING_WEAPON_MIN_DAMAGE, SORCERER_STARTING_WEAPON_MAX_DAMAGE,
                SORCERER_STARTING_WEAPON_INTELLIGENCE, SORCERER_STARING_WEAPON_NAME);
        this.equipment.add(startingStaff);
        this.equippedWeapon = startingStaff;

        // Start with a few mana potions
        this.inventory.add(new Potion(0, 5));
        this.inventory.add(new Potion(0, 5));
    }

    /**
     * Increases stats when leveling up.
     */
    public void levelUp() {
        this.level++;
        this.strength += SORCERER_STR_GAIN;
        this.dexterity +=SORCERER_DEX_GAIN;
        this.intelligence += SORCERER_INT_GAIN;
        this.vitality += SORCERER_VIT_GAIN;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void displaySpells() {
        // Display spells here
        String spellString = "";
        for(Spell s : this.spells) {
            spellString += s.getSpellName() + ": Level " + s.getSpellLevel() +", " +
                    s.getMinDamage() + "-" + s.getMaxDamage() + " damage, " + s.getManaCost() + " mana.";
        }
        JOptionPane.showMessageDialog(null, spellString);
    }
}
