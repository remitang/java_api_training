package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoatTest {

    @Test
    public void bigIntPort() {
        int size = 1;
        String[] coordinate = {"A1"};
        Boat boat = new Boat(size, coordinate);
        Assertions.assertTrue(boat.testCoordinate("A1"));
        Assertions.assertFalse(boat.testCoordinate("B1"));
    }
}
