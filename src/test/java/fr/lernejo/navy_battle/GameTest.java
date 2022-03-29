package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    int[] boatSize = {1};
    String[][] boatCoordinates = {{"A3"}};
    Game game = new Game(boatSize, boatCoordinates);

    @Test
    public void gameNextAttack() {
        game.nextAttack();
        Assertions.assertEquals(game.attack[0], 1);
    }
}
