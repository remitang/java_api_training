package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private final String url;
    private final int port;

    public Server(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void createServer(Game game) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.url, this.port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        server.createContext("/ping", new Ping());
        server.createContext("/api/game/start", new ConnectResponse(game, String.valueOf(this.port)));
        server.createContext("/api/game/fire", new PlayResponse(game));
        System.out.println("Server is listening on the port " + this.port);
    }
}
