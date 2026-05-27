package main.java.programs.textGame;

import main.java.numbers.Random;
import main.java.strings.Input;

public class TextGame {

    Random random = new Random();
    Input input = new Input();

    // read docs for grid spaces
    Character[][] grid;
    /**
     * 3 parameters: row/y, col/x, direction (in degrees, with 0 being straight
     * forward/up and so forth)
     */
    Integer[] player = new Integer[] { 0, 0, 0 };

    String inventory = "";

    /** Keep running the game is this is false */
    boolean isGameOver = false;

    int GRID_WIDTH = 2;
    int GRID_HEIGHT = 2;
    /** Will add one of each of the characters to the grid at a random location */
    String GRID_SPACES = "sf";
    // "wasd" = movement, "i" being to open the inventory
    String ALLOWED_COMMANDS = "wasdi";
    // read docs for allowed items
    String ALLOWED_ITEMS = "";

    public TextGame() {
    }

    public void runGame() {
        initializeGame();
    }

    // #region grid
    private void generateEmptyGrid(int width, int height) {
        grid = new Character[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = '.';
            }
        }
    }

    private void fillGrid(String symbols) {
        while (!symbols.isEmpty()) {
            int randRow = random.random(0, grid.length - 1);
            int randCol = random.random(0, grid[0].length - 1);

            if (grid[randRow][randCol] == '.') {
                grid[randRow][randCol] = symbols.charAt(0);
                symbols = symbols.substring(1);
            }
        }
    }
    // #endregion
    // #region player

    /** Rotates the player by the degrees given */
    public void rotateplayer(int degrees) {
        player[2] = (player[2] + degrees); // Add the degrees
        player[2] = ((player[2] % 360) + 360) % 360; // Make sure the direction is both positive and less than 360
    }

    public void movePlayer(int spaces) {
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

    public void initializeGame() {
        System.out.println("Loading TextGame...");
        generateEmptyGrid(GRID_WIDTH, GRID_HEIGHT);
        fillGrid(GRID_SPACES);
    }

    public void printInstructions() {

    }

    public void runGameLoop() {
        while (!isGameOver) {
            String command = input.getAllowed(ALLOWED_COMMANDS);

            // run the command associated with the input
            if (command == null) {
                System.out.println("That is not a valid command, please try again");
            } else if (command.equals("w")) {
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
    }

    public void openInventory() {
        System.out.println("Inventory: ");
        for (String item : inventory.split("")) {
            System.out.print(item + " ");
        }
        System.out.println("or type anything else to exit");

        String item = input.getAllowed(inventory);

        if (!(item == null)) {
            // run the assigned code for each item
            switch (item) {
                case "hihihihihiih": // replace later with actual items
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid item");
                    break;
            }
        }
    }
    // #endregion
}