package com.game.minesweeper;

public class UserInputHandler {

    public static int parseGridSize(String input) {
        try {
            int size = Integer.parseInt(input);
            if (size < 2 || size > 10) {
                throw new IllegalArgumentException("Grid size must be between 2 and 10.");
            }
            return size;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid grid size.");
        }
    }

    public static int parseMineCount(String input, int gridSize) {
        try {
            int count = Integer.parseInt(input);
            int maxMines = (int) (0.35 * gridSize * gridSize);
            if (count < 1 || count > maxMines) {
                throw new IllegalArgumentException("Mine count must be between 1 and " + maxMines + ".");
            }
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid mine count.");
        }
    }

    public static int[] parseCellPosition(String input) {
        if (input.length() < 2) {
            throw new IllegalArgumentException("Invalid cell position.");
        }
        char rowChar = input.charAt(0);
        int col;
        try {
            col = Integer.parseInt(input.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid cell position.");
        }
        int row = rowChar - 'A';
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            throw new IllegalArgumentException("Invalid cell position.");
        }
        return new int[]{row, col};
    }
}
