package com.wordgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WinScreenController {
    @FXML
    public void RedirectHome(MouseEvent event) {
        try {
            Parent homePage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreen.fxml")));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void NewGame(MouseEvent event) {

    }
}
