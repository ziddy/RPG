/**
 * The sorcerer class
 */
public class Sorcerer extends Player {

    // Sorcerer constants
    public static final int SORCERER_BASE_STR = 10;
    public static final int SORCERER_BASE_DEX = 10;
    public static final int SORCERER_BASE_INT = 25;
    public static final int SORCERER_BASE_VIT = 10;
    public static final double SORCERER_BASE_CRIT_CHANCE = 0;
    public static final double SORCERER_BASE_CRIT_DAMAGE = 0;
    public static final double SORCERER__BASE_DOUBLE_CHANCE = 0.01;

    // Sorcerer stat gains
    public static final int SORCERER_STR_GAIN = 1;
    public static final int SORCERER_DEX_GAIN = 1;
    public static final int SORCERER_INT_GAIN = 4;
    public static final int SORCERER_VIT_GAIN = 2;

    /**
     * Sets base stats
     */
    public Sorcerer() {
        this.strength = SORCERER_BASE_STR;
        this.dexterity = SORCERER_BASE_DEX;
        this.intelligence = SORCERER_BASE_INT;
        this.vitality = SORCERER_BASE_VIT;

        // Calculate stats
        calcStats();
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
}
