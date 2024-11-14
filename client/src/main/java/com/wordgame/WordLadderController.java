package com.wordgame;

import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.enums.GameDifficulty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WordLadderController {
    private WordLadderGameModel Model;
    private int CurrentWordIndex = -1;

    public VBox WordContainer;

    public void initialize(GameDifficulty gameDifficulty) {
        Model = ServiceHelpers.getWordLadderGameModel(gameDifficulty);

        // TODO: Add better exception handling here.
        if (Model == null) {
            throw new NullPointerException("Word Ladder Game Model is null.");
            // TODO: Show error and navigate back to home.
        }

        // Add textbox for each word.
        var firstWordDescription = Model.GameConfig.GameWords.getFirst().getDescription();
        RenderWordTextBox(firstWordDescription);
    }

    private void RenderWordTextBox(String description) {
        // Build container that holds the entire instance of a word input.
        var indWordContainer = new VBox();
        // Add the description label above where the textbox will be.
        indWordContainer.getChildren().add(new Label(description));

        // Make a new container where the textbox and validation button will be.
        var textBoxContainer = new HBox();
        // Add the textbox for word input.
        textBoxContainer.getChildren().add(new TextField());
        // Add a button for the user to validate their word entry.
        var validationButton = new Button("Validate");
        validationButton.setOnMouseClicked(event -> {
            var isValid = ValidateWordEntry();

            if (isValid) {
                var nextWord = Model.GameConfig.GameWords.get(CurrentWordIndex + 1);
                var desc = nextWord.getDescription();
                RenderWordTextBox(desc);
            }
            else {
                // TODO: Show message that user got it wrong. Maybe keep score?
            }
        });
        textBoxContainer.getChildren().add(validationButton);

        // Add container with word input and validation button to word instance control.
        indWordContainer.getChildren().add(textBoxContainer);

        // Add the new word input instance to the game container.
        WordContainer.getChildren().add(indWordContainer);

        CurrentWordIndex++;
    }

    private boolean ValidateWordEntry() {
        var currentWord = Model.GameConfig.GameWords.get(CurrentWordIndex);
        return true;
    }
}
