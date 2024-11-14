package com.wordgame;

import com.wordgame.models.JumbleGameModel;
import com.wordgame.models.enums.GameDifficulty;

public class JumblesController {
    /// Holds the model representing the game.
    private JumbleGameModel model;

    /**
     * Called to instantiate the word ladder game.
     * @param difficulty Difficulty of the random game fetched.
     */
    public void initialize(GameDifficulty difficulty) {
        model = ServiceHelpers.GetRandomJumblesGame(difficulty);
    }
}
