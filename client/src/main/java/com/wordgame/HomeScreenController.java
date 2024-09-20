package com.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {
    @FXML
    protected void onStartClick(ActionEvent event) throws IOException {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameIntake.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
            stage.setFullScreen(true);
            stage.setTitle("Word Ladder");
    }
}