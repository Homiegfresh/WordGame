package com.wordgame;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WinScreenController {
    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ViewHelpers.RedirectHome(stage);
    }

    @FXML
    public void NewGame(MouseEvent event) {

    }
}
