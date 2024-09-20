package com.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameIntakeController {
    @FXML
    private TextField lengthOfWords;

    private boolean validate() {
        String lengthOfWordString = lengthOfWords.getText();
        if (lengthOfWordString.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(lengthOfWordString);
        }
        catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @FXML
    protected void onStartClick(ActionEvent event) throws IOException {
        if (!validate()) {
            // TODO: Add error message.
        }

        int lengthOfWord = Integer.parseInt(lengthOfWords.getText());
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent gamePage = fxmlLoader.load();

            GameController controller = fxmlLoader.getController();
            controller.initializeWordBox(lengthOfWord);

            // Get the current stage
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene newScene = new Scene(gamePage);
            stage.setScene(newScene);
            stage.setFullScreen(true);
            stage.setTitle("Word Ladder"); // Optional: Set the title for the new scene
        } catch (IOException _) {
        }
    }
}
