package com.game.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MineSweeperGameTest {

    private MineSweeperGame game;

    @BeforeEach
    void setUp() {
        game = new MineSweeperGame(4, 2);
    }

    @Test
    void testRevealCell() {
        game.revealCell("A1");
        assertTrue(game.getGrid().getCell(0, 0).isRevealed());
    }

    @Test
    void testIsWin() {
        for (int i = 0; i < game.getGrid().getSize(); i++) {
            for (int j = 0; j < game.getGrid().getSize(); j++) {
                if (!game.getGrid().getCell(i, j).isMine()) {
                    game.getGrid().getCell(i, j).reveal();
                }
            }
        }
        assertTrue(game.isWin());
    }

    @Test
    void testIsLose() {
        game.getGrid().placeMines();
        for (int i = 0; i < game.getGrid().getSize(); i++) {
            for (int j = 0; j < game.getGrid().getSize(); j++) {
                if (game.getGrid().getCell(i, j).isMine()) {
                    game.getGrid().getCell(i, j).reveal();
                    break;
                }
            }
        }
        assertTrue(game.isLose());
    }
}
