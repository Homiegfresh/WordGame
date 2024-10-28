package com.wordgame.models;

/** This class contains the word ladder specific configuration. */
public class WordLadderGameModel extends GameModel {
    /** This word is the starting word for the game. */
    public String FirstWord;

    /** This word is the ending word for the game. */
    public String LastWord;

    public WordLadderGameModel(ObjectNode jsonObj) throws JsonProcessingException {
        super();

        this.Id = jsonObj.get("Id").asInt();
        this.FirstWord = jsonObj.get("FirstWord").asText();
        this.LastWord = jsonObj.get("LastWord").asText();

        // Check if the GameConfig field is present and parse it as a nested JSON object
        if (jsonObj.has("GameConfig")) {
            // Assuming ObjectMapper is available or passed in from elsewhere
            ObjectMapper mapper = new ObjectMapper();

            JsonNode gameConfigNode = jsonObj.get("GameConfig");

            // If GameConfig is returned as a string, parse it again to an ObjectNode
            if (gameConfigNode.isTextual()) {
                gameConfigNode = mapper.readTree(gameConfigNode.asText());
            }

            if (gameConfigNode.isObject()) {
                this.GameConfig = new GameConfigModel((ObjectNode) gameConfigNode);
            } else {
                throw new IllegalArgumentException("GameConfig is not a valid JSON object");
            }
        }
    }
}
