package com.game.minesweeper;

public class MineSweeperGame {
    private final Grid grid;

    public MineSweeperGame(int size, int mineCount) {
        this.grid = new Grid(size, mineCount);
        grid.placeMines();
        grid.calculateAdjacentMines();
    }

    public Grid getGrid() {
        return grid;
    }

    public void revealCell(String position) {
        int[] cellPos = UserInputHandler.parseCellPosition(position);
        grid.revealCell(cellPos[0], cellPos[1]);
    }

    public boolean isWin() {
        for (int row = 0; row < grid.getSize(); row++) {
            for (int col = 0; col < grid.getSize(); col++) {
                Cell cell = grid.getCell(row, col);
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLose() {
        for (int row = 0; row < grid.getSize(); row++) {
            for (int col = 0; col < grid.getSize(); col++) {
                if (grid.getCell(row, col).isMine() && grid.getCell(row, col).isRevealed()) {
                    return true;
                }
            }
        }
        return false;
    }
}
