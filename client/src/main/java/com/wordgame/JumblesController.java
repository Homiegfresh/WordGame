package com.wordgame;

import com.wordgame.models.JumbleGameModel;
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

import java.util.*;

public class JumblesController {
    /// Holds the model representing the game.
    private JumbleGameModel Model;
    private String USER_FINAL_WORD = "";
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
        //Displays every word but final
        for (var word:Model.GameConfig.GameWords) {
        RenderWordTextBox(word);
        }
    }

    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = ViewHelpers.GetStageFromEvent(event);
        ViewHelpers.RedirectHome(stage);
    }
    private void RenderWordTextBox(WordModel word) {
        final var description = word.Description;
        String scrambled;
        String scramble = word.Word.toLowerCase();
        do {
            ArrayList<Character> chars = new ArrayList<Character>(scramble.length());
            for (char letter : scramble.toCharArray()) {
                chars.add(letter);
            }
            Collections.shuffle(chars);
            char[] shuffled = new char[chars.size()];
            for (int i = 0; i < shuffled.length; i++) {
                shuffled[i] = chars.get(i);
            }
            scrambled = new String(shuffled);
        } while (scrambled.equals(scramble));

        // Build container that holds the entire instance of a word input.
        var wordContainer = new VBox();
        wordContainer.setAlignment(Pos.TOP_CENTER);
        var wordDescriptionLabel = new Label(description);
        var wordLetters = new Label(scrambled);

        // This prevents the UI from cutting off the text of the description.
        wordDescriptionLabel.setWrapText(true);

        // Add the description label above where the textbox will be.
        wordContainer.getChildren().add(wordDescriptionLabel);
        wordContainer.getChildren().add(wordLetters);
        // Make a new container where the textbox and validation button will be.
        var textBoxContainer = new HBox();
        textBoxContainer.setAlignment(Pos.TOP_CENTER);
        textBoxContainer.getChildren().add(new TextField());
        wordContainer.getChildren().add(textBoxContainer);

        int theLetter = ImportantLetter(word);;
        var validationButton = BuildValidationButton(theLetter);

        // Add validation button to the container with the textbox.
        textBoxContainer.getChildren().add(validationButton);

        GameContainer.getChildren().add(wordContainer);
    }

    private Button BuildValidationButton(int theLetter) {
        var validationButton = new Button("Validate");

        validationButton.setOnMouseClicked(event -> {
            HBox container = (HBox)validationButton.getParent();
            TextField textbox = (TextField)container.getChildren().getFirst();

            var wordValue = textbox.getText();
            ArrayList<Character> chars = new ArrayList<Character>(wordValue.length());
            for (char letter : wordValue.toCharArray()) {
                chars.add(letter);
            }
            char letter = chars.get(theLetter);
            USER_FINAL_WORD = USER_FINAL_WORD + letter;
            
        });
        return validationButton;
    }

    private int ImportantLetter(WordModel word) {
        String checkWord = word.Word;
        int lettersOver = 0;
        ArrayList<Character> chars = new ArrayList<Character>(checkWord.length());
        for (char letter : checkWord.toCharArray()) {
            chars.add(letter);
        }
        for (char letter : checkWord.toCharArray()) {
            if (Character.isUpperCase(chars.get(lettersOver))) {
            return lettersOver;
            }
            lettersOver = 1 + lettersOver;
        }
        return 0;
    }
}

