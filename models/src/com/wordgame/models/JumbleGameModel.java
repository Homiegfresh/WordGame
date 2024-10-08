package com.wordgame.models;

/** This class contains the jumble specific configuration. */
public class JumbleGameModel extends GameModel {
    private WordModel LastWord = new WordModel();

    public WordModel getLastWord() { return LastWord; }

    // region Setters
    public void setLastWord(WordModel lastWord) { LastWord = lastWord; }
    public void setLastWord(String word, String description) { LastWord = new WordModel(word, description); }
    // endregion
}
