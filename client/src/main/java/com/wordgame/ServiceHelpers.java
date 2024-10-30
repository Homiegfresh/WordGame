package com.wordgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.enums.GameDifficulty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ServiceHelpers {
    private ServiceHelpers() {
        throw new UnsupportedOperationException("Cannot instantiate service helper class.");
    }

    public static WordLadderGameModel getWordLadderGameModel(GameDifficulty difficulty) {
        // Testing: add difficulty back after.
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
}
