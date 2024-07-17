package com.game.minesweeper;

import java.util.Random;
import java.util.stream.IntStream;

public class Grid {
    private final int size;
    private final int mineCount;
    private final Cell[][] cells;
    private int minesPlaced;

    public Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
        this.minesPlaced = 0;
    }

    public int getSize() {
        return size;
    }

    public int getMineCount() {
        return mineCount;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void placeMines() {
        Random random = new Random();
        while (minesPlaced < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    public void revealCell(int row, int col) {
        if (!cells[row][col].isRevealed()) {
            cells[row][col].reveal();
            if (cells[row][col].getAdjacentMines() == 0) {
                revealAdjacentCells(row, col);
            }
        }
    }

    public void calculateAdjacentMines() {
        IntStream.range(0, size).forEach(row ->
                IntStream.range(0, size).forEach(col -> {
                    if (!cells[row][col].isMine()) {
                        long mineCount = IntStream.rangeClosed(-1, 1)
                                .boxed()
                                .flatMap(i -> IntStream.rangeClosed(-1, 1)
                                        .mapToObj(j -> new int[]{row + i, col + j}))
                                .filter(pos -> isValidCell(pos[0], pos[1]))
                                .filter(pos -> cells[pos[0]][pos[1]].isMine())
                                .count();
                        cells[row][col].setAdjacentMines((int) mineCount);
                    }
                })
        );
    }


    private void revealAdjacentCells(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValidCell(row + i, col + j) && !cells[row + i][col + j].isRevealed()) {
                    revealCell(row + i, col + j);
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
