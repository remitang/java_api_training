package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PlayResponse implements HttpHandler {

    public final Game game;

    public PlayResponse(Game game) {
        this.game = game;
    }

    public void sendResponse(HttpExchange exchange, String body, int rCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String query = exchange.getRequestURI().getQuery();
            String target = query.split("=")[1];
            String consequence = this.game.checkTarget(target);
            boolean shipLeft = this.game.checkShipLeft();
            String body = "{\n\"consequence\": \"" + consequence + "\",\n\"shipLeft\": " + shipLeft+ "\n}";
            sendResponse(exchange, body, 202);
            if (shipLeft) this.game.play();
            else System.out.println("You lost the game !");
        }
        else sendResponse(exchange, "Not Found", 404);
    }
}
