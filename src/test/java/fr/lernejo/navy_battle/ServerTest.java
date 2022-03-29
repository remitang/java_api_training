package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerTest {

    int[] boat = {1, 2, 1, 1, 1};
    String[][] boatC = {{"A1"}, {"A2", "A3"}, {"A4"}, {"A5"}, {"A6"}};
    Game game = new Game(boat, boatC);

    @BeforeEach
    void setupServer() throws IOException {
        Server server = new Server("localhost", 9870);
        server.createServer(game);
    }

    @Test
    public void testConnection()  {
        HttpResponse<String> response = null;
        HttpRequest requestGet = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/ping"))
            .setHeader("Accept", "application/json")
            .build();
        try {
            response = HttpClient.newHttpClient().send(requestGet, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e) {
            System.err.println("Error in PING " + e);
        }
        Assertions.assertEquals( 200, response.statusCode() );
        Assertions.assertEquals( "OK", response.body() );
    }

}
