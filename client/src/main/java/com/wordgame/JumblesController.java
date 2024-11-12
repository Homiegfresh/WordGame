package com.wordgame;

import com.wordgame.models.JumbleGameModel;
import com.wordgame.models.enums.GameDifficulty;

public class JumblesController {
    /// Holds the model representing the game.
    private JumbleGameModel model;

    /**
     * Called to instantiate the word ladder game.
     * <p>
     *     <b><code>difficulty</code></b>: Difficulty of the game that should be played.
     * </p>
     * @param difficulty
     */
    public void initialize(GameDifficulty difficulty) {
        model = ServiceHelpers.getJumbleGameModel(difficulty);
    }
}
