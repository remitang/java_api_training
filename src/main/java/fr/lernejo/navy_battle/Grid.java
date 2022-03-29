package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.List;


public class Grid {
    public final ArrayList<int[]> allPosition;
    public final int[][] grid = new int[10][10];

    public Grid(ArrayList<int[]> allPosition) {
        this.allPosition = allPosition;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                this.grid[i][j] = 1;
        }
        for (int[] position : this.allPosition) {
            this.grid[position[0]][position[1]] = 3;
        }
    }

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

    public void update(String cell, String mode) {
        int x = CoordinateToPosition(cell)[0];
        int y = CoordinateToPosition(cell)[1];
        if (this.grid[x][y] != 2) {
            if (mode.equals("hit") || mode.equals("sunk")) {
                if (this.grid[x][y] == 3)
                    this.grid[x][y] -= 1;
                else
                    this.grid[x][y] += 1;
            } else {
                this.grid[x][y] -= 1;
            }
        }
    }

    public void message(String mode) {
        if (mode.equals("hit"))
            System.out.println("You hit a boat !");
        else if (mode.equals("sunk"))
            System.out.println("The boat is sunk !");
        else
            System.out.println("You missed your shot !");
    }

    public void displayGrid() {
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        System.out.print("  ");
        for (int i = 0; i < 10; i++)
            System.out.print(alpha[i] + " ");
        System.out.print("\n");
        for (int i = 0; i < 10; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < 10; j++) {
                if (this.grid[i][j] == 0) System.out.print("* ");
                else if (this.grid[i][j] == 1) System.out.print(". ");
                else if (this.grid[i][j] == 2) System.out.print("X ");
                else System.out.print("O ");
            }
            System.out.print("\n");
        }
    }
}
