import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

/***
 * @author owner
 * The main class
 */
public class MainGame implements Serializable  {

    // Save directory
    public static String SAVE_DIRECTORY = "E:\\java\\RPG\\save";

    // Determines if game continues
    static boolean keepPlaying = true;

	/***
	 * Starts the game
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

//        // Testing
//        try {
//            Scanner scanner = new Scanner(new File("\\Weapons\\Swords\\Wooden Sword.csv"));
//            Sword sword = new Sword(scanner);
//        }
//        catch(FileNotFoundException e) {
//            System.out.println("File not found");
//            System.exit(-1);
//        }
//
//
//        System.exit(0);

		// Initialize JFrame stuff
		JFrame frame = new JFrame();
		// Introduce the game
        String initialOptions[] = {"New Game", "Load Game", "Quit"};
		int initialChoice = JOptionPane.showOptionDialog(frame, "Welcome to RPG!", "Choose an option",
                                                        0,
                                                        JOptionPane.QUESTION_MESSAGE,
                                                        null,
                                                        initialOptions,
                                                        "New Game");

        // Quit game
        if(initialChoice == 2) {
            quitGame();
        }

        // Load game
        if(initialChoice == 1) {
            // Load the player
            Player player = loadGame();
            // Start the game with the loaded player
            mainMenu(player);
        }

		// Ask what character the player wants
		String characterOptions[] = {"Warrior", "Rogue", "Sorcerer"};
		int characterClass = JOptionPane.showOptionDialog(frame, "What type of character would you like?",
				                                          "Choose a class.",
				                                          0,
				                                          JOptionPane.QUESTION_MESSAGE,
				                                          null,
				                                          characterOptions,
				                                          "Warrior");


        // Ask for character name
        String playerName = JOptionPane.showInputDialog(frame, "What is your name?");
				                     
		// Create the character
		Player player;
		switch(characterClass) {
			case 0: // Warrior
				player = new Warrior(playerName);
				break;
			case 1: // Rogue
				player = new Rogue(playerName);
				break;
			case 2: // Sorcerer
				player = new Sorcerer(playerName);
				break;
            default: // Use warrior by default
                player = new Warrior(playerName);
                break;
		}


        // Main loop
        while(keepPlaying) {
            mainMenu(player);
        }

        // End game
		quitGame();
	}

    public static void saveGame(Player player) {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_DIRECTORY + "\\" + player.getName() +".sav");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Saved game!");

        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    public static Player loadGame() {
        // Prompt name of character
        String characterName = JOptionPane.showInputDialog(null, "What is the character name?");
        Player player = null;
        try {
            // Load the character
            FileInputStream fileIn = new FileInputStream(SAVE_DIRECTORY+"\\"+characterName+".sav");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player = (Player) in.readObject();
            in.close();
            fileIn.close();
        }
        // Catch any IOException
        catch(IOException i) {
            i.printStackTrace();
        }
        // Catch any ClassNotFoundException
        catch(ClassNotFoundException c) {
            System.out.println("Player class not found");
            c.printStackTrace();
        }
        return player;
    }

	
	/***
	 * Displays the main menu
	 */
	public static void mainMenu(Player player) {

        // Action options
        String actionOptions[] = {"Fight", "Rest", "Save", "View Stats", "View Spells", "Equipment", "Inventory",
                                  "Quit"};

        // Prompt action from user
		int action = JOptionPane.showOptionDialog(null, "What would you like to do?",
                                                  "Main Menu",
                                                  0,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null,
                                                  actionOptions,
                                                  "View Stats");

        switch(action) {
            case 0: // Fight
                Battle battle = new Battle(player);
                battle.fight(player);
                break;
            case 1: // Rest
                break;
            case 2: // Save
                saveGame(player);
                break;
            case 3: // View Stats
                playerStats(player);
                break;
            case 4: // View spells
                if(!(player instanceof Sorcerer)) {
                    JOptionPane.showMessageDialog(null, "You aren't a sorcerer, you have no spells!");
                }
                else {
                    ((Sorcerer)player).displaySpells();
                }
                break;
            case 5: // Equipment
                equipmentScreen(player);
                break;
            case 6: // Inventory
                inventoryScreen(player);
                break;
            case 7: // Quit
                quitGame();
        }

        mainMenu(player);
	}


    /**
     * Displays equipment
     * @param player The player object
     */
    public static void equipmentScreen(Player player) {
        // Prompt user for action
        String[] actionOptions = {"Weapon", "Chest Armor", "Helmet", "Amulet"};
        int action = JOptionPane.showOptionDialog(null, "What equipment would you like to view?",
                                                  "Equipment Menu",
                                                  0,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null,
                                                  actionOptions,
                                                  "Weapon");

        switch(action) {
            case 0: // Weapon
                String equippedWeaponString = "Equipped: " + player.equippedWeapon.weaponName + "\n";
                equippedWeaponString += "Damage: " + player.equippedWeapon.minDamage + "-" +
                        player.equippedWeapon.maxDamage + "\n";
                if(player.equippedWeapon instanceof Sword) {
                    equippedWeaponString += "+Strength: " + ((Sword) player.equippedWeapon).strength +"\n";
                    equippedWeaponString += "+Crit Damage: " + ((Sword) player.equippedWeapon).critDamage +"\n";
                }
                else if(player.equippedWeapon instanceof Bow) {
                    equippedWeaponString += "+Dexterity: " + ((Bow) player.equippedWeapon).dexterity +"\n";
                    equippedWeaponString += "+Crit chance: " + ((Bow) player.equippedWeapon).critChance +"\n";
                }
                else if(player.equippedWeapon instanceof Staff) {
                    equippedWeaponString += "+Intelligence: " + ((Staff) player.equippedWeapon).intelligence +"\n";
                }

                JOptionPane.showMessageDialog(null, equippedWeaponString);

                break;
            case 1: // Chest Armor
                break;
            case 2: // Helmet
                break;
            case 3: // Amulet
                break;
        }

    }

    /**
     * Displays inventory
     * @param player The player object
     */
    public static void inventoryScreen(Player player) {
        String inventoryString = "";
        for(Item i : player.inventory) {
            if(i instanceof Potion) {
                inventoryString += "Potion: " + "Life: " + ((Potion) i).getHealAmount() +
                        ", Mana: " + ((Potion) i).getManaAmount() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, inventoryString);
    }

    /**
     * Displays player stats
     * @param player The player object
     */
    public static void playerStats(Player player) {
        // Show crit as percent
        NumberFormat percentFormat = NumberFormat.getPercentInstance();


        String statsString = "";
        statsString += "Name: " + player.getName() + "\n";
        statsString += "Level: " + player.getLevel() + "\n";
        statsString += "Life: " + player.getLife() + "/" + player.getMaxLife() + "\n";
        statsString += "Mana: " + player.getMana() + "/" + player.getMaxMana() + "\n";
        statsString += "Experience: " + player.getExp() + "\n";
        statsString += "Gold: " + player.getGold() + "\n";
        statsString += "Strength: " + player.getStrength() + "\n";
        statsString += "Dexterity: " + player.getDexterity() + "\n";
        statsString += "Intelligence: " + player.getIntelligence() + "\n";
        statsString += "Vitality: " + player.getVitality() + "\n";
        statsString += "Attack Rating: " + player.getAttackRating() + "\n";
        statsString += "Defense Rating: " + player.getDefenseRating() + "\n";
        statsString += "Armor: " + player.getArmor() + "\n";
        statsString += "Magic Resist: " + player.getMagicResist() + "\n";
        statsString += "Crit Chance: " + percentFormat.format(player.getCritChance()) + "\n";
        statsString += "Crit Damage: " + percentFormat.format(player.getCritDamage()) + "\n";

        JOptionPane.showMessageDialog(null, statsString);
    }
	
	/***
	 * Ends the game
	 */
	public static void quitGame() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Thanks for playing!");
		System.exit(0);
	}
}

