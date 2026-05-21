package main.java.programs.textGame;

public class TextGame {

    /** In the grid, "." are empty spaces, "s" are start, "f" are end */
    Character[][] grid;

    public TextGame() {
    }

    public void runGame() {
        generateGrid(2, 2);
        for (Character[] row : grid) {
            for (Character cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private void generateGrid(int width, int height) {
        grid = new Character[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = '.';
            }
        }
    }
}