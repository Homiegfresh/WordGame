package com.wordgame;

import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.WordModel;
import com.wordgame.models.enums.GameDifficulty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WordLadderController {
    private WordLadderGameModel Model;
    private int TotalGameWords = 0;
    private int CurrentWordIndex = -1;

    public VBox GameContainer;
    public Label StartingWordLabel;
    public Label FinalWordLabel;

    public void initialize(GameDifficulty gameDifficulty) {
        Model = ServiceHelpers.GetRandomWordLadderGame(gameDifficulty);

        // TODO: Add better exception handling here.
        if (Model == null) {
            throw new NullPointerException("Word Ladder Game Model is null.");
            // TODO: Show error and navigate back to home.
        }

        TotalGameWords = Model.GameConfig.GameWords.size();

        // Set the starting word label.
        StartingWordLabel.setText("Starting Word: " + Model.FirstWord);

        // Add textbox for the starting game word.
        var firstGameWord = Model.GameConfig.GameWords.getFirst();
        RenderWordTextBox(firstGameWord);

        // Set the final word label.
        FinalWordLabel.setText("Final Word: " + Model.LastWord);
    }

    /**
     *
     * @param word The word associated with the textbox to be rendered.
     */
    private void RenderWordTextBox(WordModel word) {
        // Build container that holds the entire instance of a word input.
        var indWordContainer = new VBox();
        // Add the description label above where the textbox will be.
        var wordDescriptionLabel = new Label(word.getDescription());
        wordDescriptionLabel.setWrapText(true);
        indWordContainer.getChildren().add(wordDescriptionLabel);

        // Make a new container where the textbox and validation button will be.
        var textBoxContainer = new HBox();
        // Add the textbox for word input.
        textBoxContainer.getChildren().add(new TextField());
        // Add a button for the user to validate their word entry.
        var validationButton = new Button("Validate");

        // Add click event
        validationButton.setOnMouseClicked(event -> {
            HBox container = (HBox)validationButton.getParent();
            TextField textbox = (TextField)container.getChildren().getFirst();

            var wordValue = textbox.getText();
            var isValid = ValidateWordEntry(wordValue);

            if (isValid) {
                if (CurrentWordIndex + 1 >= TotalGameWords) {
                    // They win the game!
                    return;
                }

                var nextWord = Model.GameConfig.GameWords.get(CurrentWordIndex + 1);
                RenderWordTextBox(nextWord);
            }
            else {
                // TODO: Show message that user got it wrong. Maybe keep score?
            }
        });

        // Add validation button to the container with the textbox.
        textBoxContainer.getChildren().add(validationButton);

        // Add container with word input and validation button to word instance control.
        indWordContainer.getChildren().add(textBoxContainer);

        // Add the new word input instance to the game container.
        GameContainer.getChildren().add(indWordContainer);

        // Increment the current word index.
        CurrentWordIndex++;
    }

    // Ideally this needs to talk to the server to confirm the word.
    // However, we are out of time folks... so we will check locally instead.
    private boolean ValidateWordEntry(String word) {
        return ServiceHelpers.ValidateWordLadderInput(Model.Id, word);
    }
}
