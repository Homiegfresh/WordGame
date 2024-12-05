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
import javafx.stage.Stage;

import java.util.*;

public class JumblesController {
    /// Holds the model representing the game.
    private JumbleGameModel Model;
    private String UserMadeFinal = "";
    private ArrayList<TextField> WordTextboxes =  new ArrayList<>();
    private List<Integer> TheLettersOver = new ArrayList<Integer>();
    private boolean DisplayError = false;
    private boolean DisplayLetters = false;
    private Label wordLetters;

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
        var root = new HBox();
        var validationButton = BuildValidationButton();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(validationButton);
        GameContainer.getChildren().add(root);
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

        scrambled = Scrambler(scramble, scramble);
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

        var userInputTextbox = new TextField();
        WordTextboxes.add(userInputTextbox);

        textBoxContainer.getChildren().add(userInputTextbox);
        wordContainer.getChildren().add(textBoxContainer);
        TheLettersOver.add(ImportantLetter(word));

        GameContainer.getChildren().add(wordContainer);
    }

    private Button BuildValidationButton() {
        var validationButton = new Button("Validate");

        validationButton.setOnMouseClicked(event -> {

            for (int i = 0; i < WordTextboxes.size(); i++) {
                TextField textbox = WordTextboxes.get(i);
                var wordValue = textbox.getText();
                int theLetter = TheLettersOver.get(i);
                ArrayList<Character> chars = new ArrayList<Character>(wordValue.length());
                for (char letter : wordValue.toCharArray()) {
                    chars.add(letter);
                }
                char letter = chars.get(theLetter);
                UserMadeFinal = UserMadeFinal + letter;
            }
            RenderFinalWordTextBox(UserMadeFinal);
            UserMadeFinal = "";
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
        lettersOver = 0;
        return lettersOver;
    }

    private void RenderFinalWordTextBox(String word) {
        final var description = Model.FinalWord.Description;
        String finalRightWord = Model.FinalWord.Word;
        String scrambled;
        String scramble = word.toLowerCase();

        scrambled = Scrambler(scramble, finalRightWord);

        // Build container that holds the entire instance of a word input.
        if (!DisplayLetters) {
            var wordContainer = new VBox();
            wordContainer.setAlignment(Pos.TOP_CENTER);
            var wordDescriptionLabel = new Label(description);

            wordLetters = new Label(scrambled);
            DisplayLetters = true;

            // This prevents the UI from cutting off the text of the description.
            wordDescriptionLabel.setWrapText(true);

            // Add the description label above where the textbox will be.
            wordContainer.getChildren().add(wordDescriptionLabel);
            wordContainer.getChildren().add(wordLetters);

            // Make a new container where the textbox and validation button will be.
            var textBoxContainer = new HBox();
            textBoxContainer.setAlignment(Pos.TOP_CENTER);

            var userInputTextbox = new TextField();

            textBoxContainer.getChildren().add(userInputTextbox);
            wordContainer.getChildren().add(textBoxContainer);
            var validationButton = BuildFinalValidationButton();

            // Add validation button to the container with the textbox.
            textBoxContainer.getChildren().add(validationButton);

            GameContainer.getChildren().add(wordContainer);
        }
        else {
            wordLetters.setText(scrambled);
        }
    }

    private Button BuildFinalValidationButton() {
        var validationButton = new Button("Validate");

        validationButton.setOnMouseClicked(event -> {
            HBox container = (HBox)validationButton.getParent();
            TextField textbox = (TextField)container.getChildren().getFirst();

            var wordValue = textbox.getText();
            var isValid = ValidateWordEntry(wordValue);

            if (isValid) {
                var stage = (Stage)validationButton.getScene().getWindow();
                ViewHelpers.Navigate(stage, "WinScreen.fxml", null);
                return;
            }
            if (!DisplayError) {
                var error = new VBox();
                error.setAlignment(Pos.TOP_CENTER);
                var messege = new Label("It seems like you messed up somewhere.");
                error.getChildren().add(messege);
                GameContainer.getChildren().add(error);
                DisplayError = true;
            }

        });
        return validationButton;
    }

    private boolean ValidateWordEntry(String word) {
        String rightWord = Model.FinalWord.Word.toLowerCase();
        word = word.toLowerCase();

        if (word.equals(rightWord)) {
            return true;
        }
        return false;
    }
    private String Scrambler(String word, String rightWord) {
        do {
            ArrayList<Character> chars = new ArrayList<Character>(word.length());
            for (char letter : word.toCharArray()) {
                chars.add(letter);
            }
            Collections.shuffle(chars);
            char[] shuffled = new char[chars.size()];
            for (int i = 0; i < shuffled.length; i++) {
                shuffled[i] = chars.get(i);
            }
            word = new String(shuffled);
        } while (word.equals(rightWord));
        return word;
    }

}