package com.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {
    @FXML
    Button jumblesButton;
    @FXML
    Button wordLadderButton;
    @FXML
    Button startButton;
    @FXML
    ImageView closedBook;
    @FXML
    ImageView openBook;
    @FXML
    Label wordLadderLabel;
    @FXML
    Label jumblesLabel;

    /// This is called upon loading of the home page.
    public void initialization() {
        openBook.setVisible(false);

        wordLadderLabel.setVisible(false);
        jumblesLabel.setVisible(false);
        jumblesButton.setVisible(false);
        wordLadderButton.setVisible(false);
    }

    /// This is he click event for the start button on the home screen.
    @FXML
    public void displayOpenBook(ActionEvent event) {
        closedBook.setVisible(false);
        openBook.setVisible(true);
        startButton.setVisible(false);
        wordLadderButton.setVisible(true);
        jumblesButton.setVisible(true);
        wordLadderLabel.setVisible(true);
        jumblesLabel.setVisible(true);
    }

    public void WordLadderPlay(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameIntake.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

//        GameIntakeController controller = fxmlLoader.getController();
//        controller.initialization();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Ladder Game");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public void JumblesPlay() {

    }
}