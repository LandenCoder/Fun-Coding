package main.java.programs.textGame;

import java.util.concurrent.ThreadLocalRandom;

public class TextGame {

    /** In the grid, "." are empty spaces, "s" are start, "f" are end */
    Character[][] grid;

    public TextGame() {
    }

    public void runGame() {
        generateEmptyGrid(1, 4);
        fillGrid("sf");

        for (Character[] row : grid) {
            for (Character cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

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
            int randRow = ThreadLocalRandom.current().nextInt(0, grid.length);
            int randCol = ThreadLocalRandom.current().nextInt(0, grid[0].length);

            if (grid[randRow][randCol] == '.') {
                grid[randRow][randCol] = symbols.charAt(0);
                symbols = symbols.substring(1);
            }
        }
    }

}
