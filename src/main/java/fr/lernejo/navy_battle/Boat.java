package fr.lernejo.navy_battle;

import java.util.Arrays;
import java.util.List;

public class Boat {
    public final int size;
    public final int[][] positions;

    public int[] CoordinateToPosition(String coordinate) {
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int x, y;
        if (coordinate.length() == 2) {
            x = Integer.parseInt(coordinate.substring(1, 2)) - 1;
        }
        else {
            x = Integer.parseInt(coordinate.substring(1, 2)) - 1;
        }
        y = List.of(alpha).indexOf(String.valueOf(coordinate.charAt(0)));
        return new int[]{x, y};
    }

    public boolean testCoordinate(String coordinate) {
        int[] testPosition = CoordinateToPosition(coordinate);
        for (int[] position : this.positions) {
            if (Arrays.equals(position, testPosition))
                return true;
        }
        return false;
    }

    public Boat(int size, String[] coordinates) {
        this.size = size;
        this.positions = new int[this.size][];
        for (int i = 0; i < coordinates.length; i++) {
            this.positions[i] = CoordinateToPosition(coordinates[i]);
        }
    }

}
