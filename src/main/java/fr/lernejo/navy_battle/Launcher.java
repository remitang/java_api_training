package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Please put 1 or 2 arguments."); return;
        }
        int[] boatSize = {5, 4, 3, 3, 2};
        String[][] boatCoordinates = {{"A3", "A4", "A5", "A6", "A7"}, {"C4", "D4", "E4", "F4"}, {"E1", "E2", "E3"}, {"B7", "C7", "D7"}, {"C1", "C2"}};
        Game game = new Game(boatSize, boatCoordinates);
        if (args.length == 2) {
            new ConnectRequest(Integer.parseInt(args[0]), args[1]);
            game.adversaryURL.add(args[1]);
        }
        Server server = new Server("localhost", Integer.parseInt(args[0]));
        server.createServer(game);
    }
}
