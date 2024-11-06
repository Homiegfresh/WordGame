package com.wordgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wordgame.models.JumbleGameModel;
import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.enums.GameDifficulty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/// This utility class helps with the client making service calls.
public class ServiceHelpers {
    /// IMPORTANT: This class should never be instantiated. However, this can only be done from inside this class.
    /// This is meant to be a static class.
    private ServiceHelpers() {
        throw new UnsupportedOperationException("Cannot instantiate service helper class.");
    }

    /**
     * Retrieves a random Word Ladder game model from the service with the game difficulty.
     *
     * <p>This method makes a HTTP request out to the service using {@code RestTemplate}.
     * It parses the response into a {@code WordLadderGameModel} if the request
     * is successful. If an error occurs or the response is unsuccessful, it returns {@code null}.</p>
     *
     * @param difficulty The difficulty level for the game ({@code GameDifficulty}) enum.
     * @return A {@code WordLadderGameModel} representing the fetched game if successful, or {@code null} if the request fails.
     */
    public static WordLadderGameModel getWordLadderGameModel(GameDifficulty difficulty) {
        // TODO Testing: add difficulty back after.
        System.out.println(difficulty);
        String url = "http://localhost:8080/api/WordLadder/RandomGame?difficulty=" + 4;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                var objectMapper = new ObjectMapper();
                var resp = response.getBody();
                var jsonObj = (ObjectNode)objectMapper.readTree(resp);

                return new WordLadderGameModel(jsonObj);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static JumbleGameModel getJumbleGameModel(GameDifficulty difficulty) {
        // Testing: add difficulty back after.
        System.out.println(difficulty);
        String url = "http://localhost:8080/api/Jumbles/RandomGame?difficulty=" + 4;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                var objectMapper = new ObjectMapper();
                var resp = response.getBody();
                var jsonObj = (ObjectNode)objectMapper.readTree(resp);

                return new JumbleGameModel();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
