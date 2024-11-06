package com.wordgame;

import com.wordgame.models.enums.GameType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/// This controller controls the home screen of the game.
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
    @FXML
    VBox mainContainer;

    /// This is called upon loading of the home page.
    public void initialization(Stage stage){
        openBook.setVisible(false);
        wordLadderLabel.setVisible(false);
        jumblesLabel.setVisible(false);
        jumblesButton.setVisible(false);
        wordLadderButton.setVisible(false);

//        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
//           var newHeight = newValue.doubleValue();
//           mainContainer.setSpacing(newHeight * 2);
//        });
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

    /// Used to navigate the user to the game intake screen.
    private void LoadGameIntakeScreen(ActionEvent event, GameType gameType) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("GameIntake.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        GameIntakeController controller = fxmlLoader.getController();
        controller.initialize(gameType);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        if (gameType == GameType.Jumbles) {
            stage.setTitle("Jumbles");
        }
        else if (gameType == GameType.WordLadder) {
            stage.setTitle("Word Ladder");
        }

        stage.setScene(scene);
        stage.show();
    }

    /// Word ladder button click event.
    public void WordLadderPlay(ActionEvent event) throws IOException {
        LoadGameIntakeScreen(event, GameType.WordLadder);
    }

    /// Jumbles button click event.
    public void JumblesPlay(ActionEvent event) throws IOException {
        LoadGameIntakeScreen(event, GameType.Jumbles);
    }
}