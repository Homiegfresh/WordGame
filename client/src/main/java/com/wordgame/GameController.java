package com.wordgame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GameController {
    @FXML
    private HBox WordContainer;

    public void initializeWordBox(int lengthOfWords) {
        WordContainer.getChildren().clear();

        // Initialize the number of textboxes that will be for each character.
        for (int i = 0; i < lengthOfWords; i++) {
            TextField tf = new TextField();
            WordContainer.getChildren().add(tf);
        }
    }
}
