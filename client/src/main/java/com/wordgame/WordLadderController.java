package com.wordgame;

import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.enums.GameDifficulty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WordLadderController {
    private WordLadderGameModel model;

    public VBox DebugContainer;

    public void initialize(GameDifficulty gameDifficulty) {
        model = ServiceHelpers.getWordLadderGameModel(gameDifficulty);

        DebugContainer.getChildren().add(new Label("--- DEBUG MENU ---"));
        DebugContainer.getChildren().add(new Label("First Name: " + model.FirstWord));
        DebugContainer.getChildren().add(new Label("Last Name: " + model.LastWord));
    }
}
