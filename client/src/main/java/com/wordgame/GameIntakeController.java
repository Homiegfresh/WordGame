package com.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameIntakeController {
//    @FXML
//    private TextField lengthOfWords;
//    @FXML
//    private Label errorMessage; // Declared with @FXML to connect with FXML file

    @FXML
    public void initialization() {
        // Hide error message initially
        //errorMessage.setVisible(false);
    }

//    private boolean validate() {
//        String lengthOfWordString = lengthOfWords.getText();
//        if (lengthOfWordString.isEmpty()) {
//            return false;
//        }
//
//        try {
//            Integer.parseInt(lengthOfWordString);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//
//        return true;
//    }

//    @FXML
//    protected void onStartClick(ActionEvent event) throws IOException {
//        // Check if input is valid
//        if (!validate()) {
//            errorMessage.setText("Please enter a valid number.");
//            errorMessage.setVisible(true); // Show error message if invalid input
//            return;
//        }
//
//        // Input is valid, proceed with starting the game
//        int lengthOfWord = Integer.parseInt(lengthOfWords.getText());
//        try {
//            // Load the new FXML file
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
//            Parent gamePage = fxmlLoader.load();
//
//            // Pass length of words to GameController
//            GameController controller = fxmlLoader.getController();
//            controller.initializeWordBox(lengthOfWord);
//
//            // Get the current stage and set the new scene
//            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            Scene newScene = new Scene(gamePage);
//            stage.setScene(newScene);
//            stage.setFullScreen(true);
//            stage.setTitle("Word Ladder");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
