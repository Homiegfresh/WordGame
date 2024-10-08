package com.wordgame.models;

/** This class contains the word ladder specific configuration. */
public class WordLadderGameModel extends GameModel {
    /** This word is the starting word for the game. */
    private WordModel FirstWord = new WordModel();

    /** This word is the ending word for the game. */
    private WordModel LastWord = new WordModel();

    // region Getters
    public WordModel getFirstWord() { return FirstWord; }

    public WordModel getLastWord() { return LastWord; }
    // endregion

    // region Setters
    public void setFirstWord(WordModel firstWord) { FirstWord = firstWord; }

    public void setLastWord(WordModel lastWord) { LastWord = lastWord; }

    public void setFirstWord(String word, String description) {
        FirstWord = new WordModel(word, description);
    }

    public void setLastWord(String word, String description) {
        LastWord = new WordModel(word, description);
    }
    // endregion
}
