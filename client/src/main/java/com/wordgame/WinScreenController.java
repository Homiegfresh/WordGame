package com.wordgame;

import com.wordgame.models.enums.GameType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/// This controller handles the activities within the win screen after a game.
public class WinScreenController {
    /// On click event for the return home button.
    @FXML
    public void RedirectHome(MouseEvent event) {
        var stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ViewHelpers.RedirectHome(stage);
    }

    /// On click event for the new game button.
    @FXML
    public void NewGame(MouseEvent event) {
        GameType gametype = GameType.WordLadder;
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ViewHelpers.<GameIntakeController>Navigate(stage,"Gameintake.fxml",controller ->
                controller.initialize(gametype));
    }
}
