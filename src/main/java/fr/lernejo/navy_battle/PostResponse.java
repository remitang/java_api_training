package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Iterator;

public class PostResponse implements HttpHandler {

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
            JsonNode id = data.path("id");
            JsonNode url = data.path("url");
            JsonNode message = data.path("message");
            if (!id.isMissingNode() && !url.isMissingNode() && !message.isMissingNode()) {
                System.out.println("id : " + data.path("id").asText());
                System.out.println("url : " + data.path("url").asText());
                System.out.println("message : " + data.path("message").asText());

                sendResponse(exchange, "OK", 200);
            } else {
                sendResponse(exchange, "Bad Request", 400);
            }
                //Staff staff = mapper.readValue(new File("Request.json"), Staff.class);
            //Si ma méthode est post
            // Lire mon fichier Json en local avec Jackson
                // Déclarer object mapper => Fournir méthode ReadTree
                    // Le stocker dans un objet de type JsonNode
                // Déclarer objet type Iterator<String> myIterator = (Récupérer JsonNode)
                    // Utiliser methode .fieldName sur mon iterator
                    // Déclarer une liste et stocker tous les fieldName


        }
        else {
            sendResponse(exchange, "Not Found", 404);
        }
        exchange.close();
    }
}
