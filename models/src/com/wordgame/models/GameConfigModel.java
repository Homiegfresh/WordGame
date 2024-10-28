package com.wordgame.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

/** This class holds that generic configuration object for a game. */
public class GameConfigModel {
    /** Primary key in the database. */
    public int Id;
    /** Foreign key to the game table. */
    public int GameId;
    /** List of words that are to be used in the game. */
    public List<WordModel> GameWords = new ArrayList<>();

    public GameConfigModel() {}

    public GameConfigModel(ObjectNode jsonObj) {
        if (jsonObj.has("Id")) {
            this.Id = jsonObj.get("Id").asInt();
        }

        if (jsonObj.has("GameId")) {
            this.GameId = jsonObj.get("GameId").asInt();
        }

        if (jsonObj.has("GameWords")) {
            jsonObj.get("GameWords").forEach(wordNode -> {
                try {
                    WordModel word = new ObjectMapper().treeToValue(wordNode, WordModel.class);
                    this.GameWords.add(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /** Allows the system to add words to the game words list. */
    public void addGameWord(WordModel gameWord) {
        if (gameWord == null) return;
        GameWords.add(gameWord);
    }
}
