package com.game.minesweeper;

import java.util.Scanner;

public class MineSweeperApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        int gridSize = 0;
        int mineCount = 0;

        while (gridSize == 0) {
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            try {
                gridSize = UserInputHandler.parseGridSize(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (mineCount == 0) {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            try {
                mineCount = UserInputHandler.parseMineCount(scanner.nextLine(), gridSize);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        MineSweeperGame game = new MineSweeperGame(gridSize, mineCount);
        boolean gameOver = false;

        while (!gameOver) {
            printGrid(game.getGrid());
            System.out.println("Select a square to reveal (e.g. A1): ");
            try {
                String position = scanner.nextLine();
                game.revealCell(position);
                if (game.isLose()) {
                    System.out.println("Oh no, you detonated a mine! Game over.");
                    gameOver = true;
                } else if (game.isWin()) {
                    System.out.println("Congratulations, you have won the game!");
                    gameOver = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printGrid(Grid grid) {
        System.out.print("  ");
        for (int i = 1; i <= grid.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row < grid.getSize(); row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < grid.getSize(); col++) {
                Cell cell = grid.getCell(row, col);
                if (cell.isRevealed()) {
                    if (cell.isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(cell.getAdjacentMines() + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
}
