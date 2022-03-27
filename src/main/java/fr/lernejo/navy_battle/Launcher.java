package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        Server server = new Server("localhost", 9876);
        server.createServer();
    }
}
