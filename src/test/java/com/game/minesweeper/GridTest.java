package com.game.minesweeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(4, 2);
    }

    @Test
    void testGridInitialization() {
        assertEquals(4, grid.getSize());
        assertEquals(2, grid.getMineCount());
        assertNotNull(grid.getCell(0, 0));
    }

    @Test
    void testPlaceMines() {
        grid.placeMines();
        int minesPlaced = 0;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.getCell(i, j).isMine()) {
                    minesPlaced++;
                }
            }
        }
        assertEquals(2, minesPlaced);
    }

    @Test
    void testRevealCell() {
        grid.placeMines();
        grid.revealCell(0, 0);
        assertTrue(grid.getCell(0, 0).isRevealed());
    }

    @Test
    void testCalculateAdjacentMines() {
        grid.placeMines();
        grid.calculateAdjacentMines();
        boolean isAdjacentMinesCalculated = false;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (!grid.getCell(i, j).isMine() && grid.getCell(i, j).getAdjacentMines() > 0) {
                    isAdjacentMinesCalculated = true;
                    break;
                }
            }
        }
        assertTrue(isAdjacentMinesCalculated);
    }
}
