package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Please put 1 or 2 arguments.");
            return;
        }
        if (args.length == 2) {
            new PostRequest(Integer.parseInt(args[0]), args[1]);
            // Add user in game
        }
        Server server = new Server("localhost", Integer.parseInt(args[0]));
        server.createServer();
    }
}
