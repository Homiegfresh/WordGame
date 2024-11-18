package com.wordgame;

import com.wordgame.models.JumbleGameModel;
import com.wordgame.models.enums.GameDifficulty;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class JumblesController {
    /// Holds the model representing the game.
    private JumbleGameModel Model;

    @FXML
    public VBox GameContainer;

    /**
     * Called to instantiate the word ladder game.
     * @param difficulty Difficulty of the random game fetched.
     */
    public void initialize(GameDifficulty difficulty) {
        Model = ServiceHelpers.GetRandomJumblesGame(difficulty);

        if (Model == null) {
            // TODO: Implement error screen.
            return;
        }
    }

    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = ViewHelpers.GetStageFromEvent(event);
        ViewHelpers.RedirectHome(stage);
    }
}
