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
     * player. Each space will be a description of that space, being able to follow
     * "you are standing in" or "up ahead you see" and so forth
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

    final int GRID_WIDTH = 10;
    final int GRID_HEIGHT = 10;
    final String LIMITED_GRID_SPACES = "sfkd";
    /** Will add one of each of the characters to the grid at a random location */
    final String UNLIMITED_GRID_SPACES = "ctemn";
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

    private void fillGrid(String limitedSymbols, String unlimitedSymbols) {
        // fill the grid with one of each of the limited symbols
        while (!limitedSymbols.isEmpty()) {
            int randRow = random.random(0, grid.length - 1);
            int randCol = random.random(0, grid[0].length - 1);

            if (grid[randRow][randCol].equals(".")) {
                grid[randRow][randCol] = String.valueOf(limitedSymbols.charAt(0));
                limitedSymbols = limitedSymbols.substring(1);
            }
        }

        // fill the grid with random instances of the unlimited symbols
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col].equals(".")) {
                    int randSymbol = random.random(0, unlimitedSymbols.length() - 1);
                    grid[row][col] = String.valueOf(unlimitedSymbols.charAt(randSymbol));
                }
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

    private Integer[] getSpaceCoordinates(int direction) {
        // Get the direction to check, making sure it is between 0 and 360
        int directionToCheck = ((player[2] + direction) % 360 + 360) % 360;
        int row = player[0];
        int col = player[1];
        if (directionToCheck == 0) {
            row -= 1;
        } else if (directionToCheck == 90) {
            col += 1;
        } else if (directionToCheck == 180) {
            row += 1;
        } else if (directionToCheck == 270) {
            col -= 1;
        }
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return null; // Out of bounds
        }
        return new Integer[] { row, col };
    }

    // #endregion
    // #region game init and run

    private void initializeGame() {
        generateEmptyGrid(GRID_WIDTH, GRID_HEIGHT);
        fillGrid(LIMITED_GRID_SPACES, UNLIMITED_GRID_SPACES);
        initializeSpacesDescriptions();
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
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                switch (grid[row][col]) {
                    case "s":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "f":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "d":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "c":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "t":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "e":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "m":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    case "n":
                        gridSpaceDescriptions[row][col] = 
                        break;
                    default:
                        // If the symbol is none of the above, it is an item
                        gridSpaceDescriptions[row][col] = 
                        break;
                }
            }
        }

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
        String location = gridSpaceDescriptions[player[0]][player[1]];
        Integer[] up = getSpaceCoordinates(0);
        Integer[] right = getSpaceCoordinates(90);
        Integer[] down = getSpaceCoordinates(180);
        Integer[] left = getSpaceCoordinates(270);

        println("You are standing in " + location + ".");
        println("Up ahead you see " + gridSpaceDescriptions[up[0]][up[1]] + ".");
        println("To your right you see " + gridSpaceDescriptions[right[0]][right[1]] + ".");
        println("Behind you you see " + gridSpaceDescriptions[down[0]][down[1]] + ".");
        println("To your left you see " + gridSpaceDescriptions[left[0]][left[1]] + ".");

    }

    private String generateRandomSetting() {
        String[] settings = new String[] {
                "a dense forest, with towering trees and green, leafy foliage up in the sky",
                "a tall, grassy hill",
                "a muddy swamp, with murky water and the occasional weedy patch",
                "some broken, crumbling ruins, covered with vines and moss",
                "a clear, blue stream"
        };
        int randomNum = random.random(0, settings.length - 1);
        return settings[randomNum];
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