package com.wordgame;

import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.WordModel;
import com.wordgame.models.enums.GameDifficulty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordLadderController {
    private WordLadderGameModel Model;
    private int TotalGameWords = 0;
    private int CurrentWordIndex = -1;

    public Button HomeButton;
    public VBox GameContainer;
    public Label StartingWordLabel;
    public Label FinalWordLabel;


    /**
     * This function initializes the game by fetching a game and rendering the UI for it.
     * @param gameDifficulty The difficulty of the random game to be retrieved.
     */
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

    /// Redirects the user to the home screen upon clicking the home button.
    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = (Stage) HomeButton.getScene().getWindow();
        ViewHelpers.RedirectHome(stage);
    }

    /**
     * @param word The word associated with the textbox to be rendered.
     */
    private void RenderWordTextBox(WordModel word) {
        final var description = word.Description;

        // Build container that holds the entire instance of a word input.
        var wordContainer = new VBox();
        wordContainer.setAlignment(Pos.TOP_CENTER);
        var wordDescriptionLabel = new Label(description);

        // This prevents the UI from cutting off the text of the description.
        wordDescriptionLabel.setWrapText(true);

        // Add the description label above where the textbox will be.
        wordContainer.getChildren().add(wordDescriptionLabel);

        // Make a new container where the textbox and validation button will be.
        var textBoxContainer = new HBox();
        textBoxContainer.setAlignment(Pos.TOP_CENTER);
        textBoxContainer.getChildren().add(new TextField());
        wordContainer.getChildren().add(textBoxContainer);
            
        // Add a button for the user to validate their word entry.
        var validationButton = BuildValidationButton();

        // Add validation button to the container with the textbox.
        textBoxContainer.getChildren().add(validationButton);

        // Add the new word input instance to the game container.
        GameContainer.getChildren().add(wordContainer);

        // Increment the current word index.
        CurrentWordIndex++;
    }

    /**
     * This function is used to validate that the user is solving the correct word.
     * @param word The word the user submitted for validation.
     * @return If the user is solving the correct word.
     */
    private boolean IsSolvingCurrentWord(String word) {
        var wordCount = Model.GameConfig.GameWords.size();
        for (var i = 0; i < wordCount; i++) {
            // Skip if we are checking the current word.
            // We still need to check that they are not solving a future word.
            if (i == CurrentWordIndex) continue;

            // Get the value of the current configuration word we are checking.
            var gameConfigWordValue = Model.GameConfig.GameWords.get(i).Word;

            // If there is a match they are not solving the current word.
            if (word.equals(gameConfigWordValue)) return false;
        }

        return true;
    }

    /// Builds the validation button that will be included with the user input.
    private Button BuildValidationButton() {
        var validationButton = new Button("Validate");

        validationButton.setOnMouseClicked(event -> {
            HBox container = (HBox)validationButton.getParent();
            TextField textbox = (TextField)container.getChildren().getFirst();
            String wordValue = textbox.getText();

            var isSolvingCurrentWord = IsSolvingCurrentWord(wordValue);
            if (!isSolvingCurrentWord) {
                return;
            }

            var isValid = ValidateWordEntry(wordValue);
            if (isValid) {
                if (CurrentWordIndex + 1 >= TotalGameWords) {
                    var stage = (Stage)validationButton.getScene().getWindow();
                    ViewHelpers.Navigate(stage, "WinScreen.fxml", null);
                    return;
                }

                var nextWord = Model.GameConfig.GameWords.get(CurrentWordIndex + 1);
                RenderWordTextBox(nextWord);
                validationButton.setVisible(false);
            }
            else {
                // TODO: Show message that user got it wrong. Maybe keep score?
            }
        });
        
        return validationButton;
    }

    /// Makes a call to the server to validate the word.
    private boolean ValidateWordEntry(String word) {
        return ServiceHelpers.ValidateWordLadderInput(Model.Id, word);
    }
}
