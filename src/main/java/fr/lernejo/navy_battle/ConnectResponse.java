package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ConnectResponse implements HttpHandler {
    public final Game game;
    public final String port;

    public ConnectResponse(Game game, String port) {
        this.game = game;
        this.port = port;
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
        if (exchange.getRequestMethod().equals("POST")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode data = mapper.readTree(exchange.getRequestBody());
            JsonNode id = data.path("id");JsonNode url = data.path("url");JsonNode message = data.path("message");
            if (!id.isMissingNode() && !url.isMissingNode() && !message.isMissingNode()) {
                this.game.adversaryURL.add(data.path("url").asText());
                String body = "{\n\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\n\"url\": \"http://localhost:" + this.port + "\",\n\"message\": \"May the best code win\"\n}";
                sendResponse(exchange, body, 202);
                this.game.play();
            } else sendResponse(exchange, "Bad Request", 400);
        }
        else sendResponse(exchange, "Not Found", 404);
        exchange.close();
    }
}
