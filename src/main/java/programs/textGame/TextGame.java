package main.java.programs.textGame;

import main.java.numbers.Random;
import main.java.strings.Input;
import main.java.strings.StringTools;

public class TextGame {

    Random random = new Random();
    Input input = new Input();
    StringTools strings = new StringTools();

    // read docs for grid spaces
    String[][] grid;
    /**
     * The descriptions of each grid space, used in printing the surroundings of the
     * player
     */
    String[][] gridSpaceDescriptions = new String[][] {};

    /**
     * 3 parameters: row/y, col/x, direction (in degrees, with 0 being straight
     * forward/up and so forth)
     */
    Integer[] player = new Integer[] { 0, 0, 0 };
    String inventory = "";
    int money = 0;
    int turns = 0;
    int strength = 1;

    /**
     * When the game is being initialized, enemies will be on the board. The enemies
     * will be added to this with 1. x cordinate, 2. y cordinate, 3. strength
     */
    String[][] enemies = new String[][] {};

    /** Keep running the game is this is false */
    boolean isGameOver = false;

    final int GRID_WIDTH = 2;
    final int GRID_HEIGHT = 2;
    /** Will add one of each of the characters to the grid at a random location */
    final String GRID_SPACES = "sfkdcctee";
    // "wasd" = movement, "i" being to open the inventory
    final String ALLOWED_COMMANDS = "wasdi";

    public TextGame() {
    }

    public void runGame() {
        initializeGame();
        while (!isGameOver) {
            runGameLoop();
        }
    }

    // #region grid

    private void generateEmptyGrid(int width, int height) {
        grid = new String[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = ".";
            }
        }
    }

    private void fillGrid(String symbols) {
        while (!symbols.isEmpty()) {
            int randRow = random.random(0, grid.length - 1);
            int randCol = random.random(0, grid[0].length - 1);

            if (grid[randRow][randCol].equals(".")) {
                grid[randRow][randCol] = String.valueOf(symbols.charAt(0));
                symbols = symbols.substring(1);
            }
        }
    }

    // #endregion
    // #region player

    /** Rotates the player by the degrees given */
    private void rotateplayer(int degrees) {
        player[2] = (player[2] + degrees); // Add the degrees
        player[2] = ((player[2] % 360) + 360) % 360; // Make sure the direction is both positive and less than 360
    }

    private void movePlayer(int spaces) {
        int direction = player[2];
        if (direction == 0) {
            player[0] -= spaces;
        } else if (direction == 90) {
            player[1] += spaces;
        } else if (direction == 180) {
            player[0] += spaces;
        } else if (direction == 270) {
            player[1] -= spaces;
        }
    }

    // #endregion
    // #region game init and run

    private void initializeGame() {
        generateEmptyGrid(GRID_WIDTH, GRID_HEIGHT);
        fillGrid(GRID_SPACES);
        printInstructions();
    }

    private void printInstructions() {
        println("Welcome to the Text Adventure Game!");
        println("In this game, you will explore a mysterious world filled with secrets and challenges.");
        println("Use the following commands to navigate and interact with the world:");
        println("- 'w': Move forward");
        println("- 'a': Move left");
        println("- 's': Move backward");
        println("- 'd': Move right");
        println("- 'i': Open inventory");
        println("Good luck on your adventure!");
        println();
    }

    private void initializeSpacesDescriptions() {
    }

    private void runGameLoop() {

        printLocation();

        String command = input.getAllowed(ALLOWED_COMMANDS);
        // run the command associated with the input
        if (command.equals("w")) {
            rotateplayer(0);
            movePlayer(1);
        } else if (command.equals("a")) {
            rotateplayer(-90);
            movePlayer(1);
        } else if (command.equals("s")) {
            rotateplayer(180);
            movePlayer(1);
        } else if (command.equals("d")) {
            rotateplayer(90);
            movePlayer(1);
        } else if (command.equals("i")) {
            openInventory();
        }
    }

    private void printLocation() {
        String location = grid[player[0]][player[1]];

    }

    // #endregion
    // #region items and inventory

    private void openInventory() {
        printInventoryItems();

        println("or type anything else to exit");

        String item = input.getAllowed(inventory);

        if (!(item.equals(null))) {
            // run the assigned code for each item
            switch (item) {
                case "k": // replace later with actual items
                    println("The golden key shines brightly in the sunlight.");
                    break;
                default:
                    println("Exiting...");
                    break;
            }
        }
    }

    private void printInventoryItems() {
        if (inventory.isEmpty()) {
            println("Your inventory is empty.");
        } else {
            for (String item : inventory.split("")) {
                switch (item) {
                    case "k":
                        print("Key ");
                        break;

                    default:
                        break;
                }
            }
        }
    }

    private void addItem(String item) {
        inventory += item;
    }

    private void removeItem(String item) {
        outerLoop: for (String letter : inventory.split("")) {
            if (letter.equals(item)) {
                inventory = inventory.replaceFirst(item, "");
                break outerLoop;
            }
        }
    }

    // #endregion

    private void println() {
        System.out.println();
    }

    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }
}