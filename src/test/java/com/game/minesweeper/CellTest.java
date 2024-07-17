package com.game.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell();
    }

    @Test
    void testDefaultValues() {
        assertFalse(cell.isMine());
        assertFalse(cell.isRevealed());
        assertEquals(0, cell.getAdjacentMines());
    }

    @Test
    void testSetMine() {
        cell.setMine(true);
        assertTrue(cell.isMine());
    }

    @Test
    void testReveal() {
        cell.reveal();
        assertTrue(cell.isRevealed());
    }

    @Test
    void testSetAdjacentMines() {
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }
}
