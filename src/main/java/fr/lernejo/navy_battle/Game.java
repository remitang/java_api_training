package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Game {
    public final ArrayList<Boat> boatList = new ArrayList<>();
    public final int[] boatLife = new int[5];
    public final int[] attack = {0};

    public final ArrayList<String> adversaryURL = new ArrayList<>();

    public final Grid adversaryGrid = new Grid(new ArrayList<>());
    public final Grid myGrid;

    public String nextAttack() {
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String x = alpha[this.attack[0] % 10];
        String y = String.valueOf(this.attack[0] / 10 + 1);
        this.attack[0] = this.attack[0] + 1;
        return x + y;
    }

    public String checkTarget(String coordinate) {
        for (int i = 0; i < 5; i++) {
            Boat boat = this.boatList.get(i);
            for (int j = 0; j < boat.size; j++) {
                if (boat.testCoordinate(coordinate)) {
                    this.boatLife[i] = this.boatLife[i] - 1;
                    if (this.boatLife[i] == 0)
                        return "sunk";
                    else {
                        return "hit";
                    }
                }
            }
        }
        return "miss";
    }

    public boolean checkShipLeft() {
        for (Integer boatLife : this.boatLife) {
            if (boatLife != 0)
                return true;
        }
        return false;
    }

    public Game(int[] boatSize, String[][] boatCoordinates) {
        for (int i = 0; i < boatSize.length; i++) {
            this.boatList.add(new Boat(boatSize[i], boatCoordinates[i]));
            this.boatLife[i] = boatSize[i];
        }
        ArrayList<int[]> allPosition = new ArrayList<>();
        for (Boat boat : this.boatList) {
            for (int i = 0; i < boat.size; i++) {
                allPosition.add(new int[]{boat.positions[i][0], boat.positions[i][1]});
            }
        }
        this.myGrid = new Grid(allPosition);
    }

    public void play() throws JsonProcessingException {
        String cell = this.nextAttack();
        PlayRequest fire = new PlayRequest(this.adversaryURL.get(0), cell);
        JsonNode data = fire.fire();
        String consequence = data.path("consequence").asText();
        boolean shipLeft = data.path("shipLeft").asBoolean();
        this.adversaryGrid.update(cell, consequence);
        this.adversaryGrid.message(consequence);
        if (!shipLeft)
            System.out.println("You won the game !");
        System.out.println("Adversary Grid");
        this.adversaryGrid.displayGrid();
    }
}
