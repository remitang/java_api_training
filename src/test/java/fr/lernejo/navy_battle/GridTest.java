package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {

    int[] boatSize = {1, 2, 1, 1, 1};
    String[][] boatCoordinates = {{"A1"}, {"A2", "A3"}, {"A4"}, {"A5"}, {"A6"}};
    Game game = new Game(boatSize, boatCoordinates);
    Grid testGrid = game.myGrid;

    @Test
    public void testBoatPosition() {
        Assertions.assertEquals(testGrid.grid[5][5], 1);
        Assertions.assertEquals(testGrid.grid[0][0], 3);
    }

    @Test
    public void testUpdatePosition() {
        testGrid.update("A1", "hit");
        Assertions.assertEquals(testGrid.grid[0][0], 2);
    }

}
