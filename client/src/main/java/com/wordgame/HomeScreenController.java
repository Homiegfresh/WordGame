package com.wordgame;

import com.wordgame.models.enums.GameDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeScreenController {
    @FXML
    protected void onStartClick(ActionEvent event) throws IOException {

            var model = ServiceHelpers.getWordLadderGameModel(GameDifficulty.HARD);

            // Load the new FXML file
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameIntake.fxml"));
//            Parent newRoot = fxmlLoader.load();
//
//            // Get the current stage
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            // Set the new scene
//            Scene newScene = new Scene(newRoot);
//            stage.setScene(newScene);
//            stage.setTitle("Word Ladder");
    }
}