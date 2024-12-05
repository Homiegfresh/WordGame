package com.wordgame;

import com.wordgame.models.enums.GameType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WinScreenController {
    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ViewHelpers.RedirectHome(stage);
    }

    @FXML
    public void NewGame(MouseEvent event) throws IOException {
        GameType gametype = GameType.WordLadder;
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ViewHelpers.<GameIntakeController>Navigate(stage,"Gameintake.fxml",controller ->
                controller.initialize(gametype));
    }
}
