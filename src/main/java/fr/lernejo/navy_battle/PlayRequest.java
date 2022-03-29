package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PlayRequest {
    public final String adversaryUrl;
    public final String cell;

    public PlayRequest(String adversaryUrl, String cell) {
        this.adversaryUrl = adversaryUrl;
        this.cell = cell;
    }

    public JsonNode fire() throws JsonProcessingException {
        HttpResponse<String> response = null;
        HttpRequest requestGet = HttpRequest.newBuilder()
            .uri(URI.create(this.adversaryUrl + "/api/game/fire?cell=" + this.cell))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .build();
        try {
            response = HttpClient.newHttpClient().send(requestGet, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e) {
            System.err.println("Error in response of Fire request: " + e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.body());
    }
}
