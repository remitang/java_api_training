package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    int[] boatSize = {1, 2, 1, 1, 1};
    String[][] boatCoordinates = {{"A1"}, {"A2", "A3"}, {"A4"}, {"A5"}, {"A6"}};
    Game game = new Game(boatSize, boatCoordinates);

    @Test
    public void gameNextAttack() {
        game.nextAttack();
        Assertions.assertEquals(game.attack[0], 1);
    }

    @Test
    public void gameShipLeft() {
        Assertions.assertTrue(game.checkShipLeft());
    }

    @Test
    public void gameCheckTargetMiss() {
        Assertions.assertEquals(game.checkTarget("B2"), "miss");
    }

    @Test
    public void gameCheckTargetHit() {
        Assertions.assertEquals(game.checkTarget("A2"), "hit");
    }

    @Test
    public void gameCheckTargetSunk() {
        Assertions.assertEquals(game.checkTarget("A1"), "sunk");
    }
}
