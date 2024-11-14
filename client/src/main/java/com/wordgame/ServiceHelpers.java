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
    public static WordLadderGameModel GetRandomWordLadderGame(GameDifficulty difficulty) {
        // TODO Testing: add difficulty back after.
        System.out.println(difficulty);
        var url = "http://localhost:8080/api/WordLadder/RandomGame?difficulty=" + difficulty.ordinal();
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

    /**
     * Validates the input word for a specific Word Ladder game.
     *
     * <p>This method makes a HTTP request to the service using {@code RestTemplate}.
     * It checks if the input word is valid for the given game ID. If the request is successful,
     * it returns {@code true} or {@code false} based on the service response. If an error occurs
     * or the response is unsuccessful, it returns {@code false}.</p>
     *
     * @param gameId The ID of the Word Ladder game.
     * @param word The word to validate.
     * @return {@code true} if the word is valid for the given game, or {@code false} if the request fails or the word is invalid.
     */
    public static boolean ValidateWordLadderInput(int gameId, String word) {
        var url = "http://localhost:8080/api/WordLadder/ValidateInput?gameId=" + gameId + "&word=" + word;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves a random Jumble game model from the service with the game difficulty.
     *
     * <p>This method makes a HTTP request out to the service using {@code RestTemplate}.
     * It parses the response into a {@code JumbleGameModel} if the request is successful.
     * If an error occurs or the response is unsuccessful, it returns {@code null}.</p>
     *
     * @param difficulty The difficulty level for the game ({@code GameDifficulty}) enum.
     * @return A {@code JumbleGameModel} representing the fetched game if successful, or {@code null} if the request fails.
     */
    public static JumbleGameModel GetRandomJumblesGame(GameDifficulty difficulty) {
        // Testing: add difficulty back after.
        System.out.println(difficulty);
        var url = "http://localhost:8080/api/Jumbles/RandomGame?difficulty=" + 4;
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
