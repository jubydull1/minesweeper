package com.game.minesweeper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputHandlerTest {

    @Test
    void testParseGridSizeValid() {
        assertEquals(4, UserInputHandler.parseGridSize("4"));
    }

    @Test
    void testParseGridSizeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> UserInputHandler.parseGridSize("0"));
    }

    @Test
    void testParseMineCountValid() {
        assertEquals(2, UserInputHandler.parseMineCount("2", 4));
    }

    @Test
    void testParseMineCountInvalid() {
        assertThrows(IllegalArgumentException.class, () -> UserInputHandler.parseMineCount("10", 4));
    }

    @Test
    void testParseCellPositionValid() {
        int[] expected = {0, 0};
        assertArrayEquals(expected, UserInputHandler.parseCellPosition("A1"));
    }

    @Test
    void testParseCellPositionInvalid() {
        assertThrows(IllegalArgumentException.class, () -> UserInputHandler.parseCellPosition("Z1"));
    }
}
