package com.wordgame.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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

    public GameConfigModel(ArrayNode jsonObj) {
//        if (jsonObj.has("Id")) {
//            this.Id = jsonObj.get("Id").asInt();
//        }
//
//        if (jsonObj.has("Id")) {
//            this.GameId = jsonObj.get("Id").asInt();
//        }

        jsonObj.forEach(wordNode -> {
            try {
                var wordString = wordNode.get("Word").asText();
                var wordDescription = wordNode.get("Description").asText();
                this.GameWords.add(new WordModel(wordString, wordDescription));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        if (jsonObj.has("Definition")) {
//            jsonObj.get("Definition").forEach(wordNode -> {
//                try {
//                    var wordString = wordNode.get("Word").asText();
//                    var wordDescription = wordNode.get("Description").asText();
//                    this.GameWords.add(new WordModel(wordString, wordDescription));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
    }

    /** Allows the system to add words to the game words list. */
    public void addGameWord(WordModel gameWord) {
        if (gameWord == null) return;
        GameWords.add(gameWord);
    }
}
