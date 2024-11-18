package com.wordgame.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/** This class contains the jumble specific configuration. */
public class JumbleGameModel extends GameModel {
    public WordModel FinalWord = new WordModel();

    public JumbleGameModel(ObjectNode jsonObj) throws JsonProcessingException {
        super();

        // Set the game ID.
        this.Id = jsonObj.get("Id").asInt();

        // Set the final word and phrase.
        if (jsonObj.has("FinalWord")) {
            var wordValue = jsonObj.get("FinalWord").get("Word").asText();
            var wordDescription = jsonObj.get("FinalWord").get("Description").asText();

            FinalWord = new WordModel(wordValue, wordDescription);
        }

        // Check if the GameConfig field is present and parse it as a nested JSON object
        if (jsonObj.has("Definition")) {
            // Assuming ObjectMapper is available or passed in from elsewhere
            ObjectMapper mapper = new ObjectMapper();

            JsonNode gameConfigNode = jsonObj.get("Definition");

            // If GameConfig is returned as a string, parse it again to an ObjectNode
            if (gameConfigNode.isTextual()) {
                gameConfigNode = mapper.readTree(gameConfigNode.asText());
            }

            if (gameConfigNode.isArray()) {
                this.GameConfig = new GameConfigModel((ArrayNode) gameConfigNode);
            } else {
                throw new IllegalArgumentException("GameConfig is not a valid JSON object");
            }
        }
    }
}
