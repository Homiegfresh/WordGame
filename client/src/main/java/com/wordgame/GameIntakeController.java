package com.wordgame;

import com.wordgame.models.enums.GameDifficulty;
import com.wordgame.models.enums.GameType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
* This class is used to determine what game configuration should be used for the instance of the game.
* The game type is passed in and the user selects a difficulty.
* Once the difficulty is chosen the system can create a new game.
*/
public class GameIntakeController {
    /// Stores the game type for navigation.
    private GameType gameType;

    @FXML
    public Button easyButton;
    @FXML
    public Button mediumButton;
    @FXML
    public Button hardButton;

    /**
     * Initializes the controller with the specified game type.
     * <p>
     * Sets up the buttons with appropriate game difficulty values for user selection.
     *
     * @param type the game type that is being set up.
     */
    public void initialize(GameType type) {
        gameType = type;

        // Associates a game difficulty with the button.
        easyButton.setUserData("EASY");
        mediumButton.setUserData("MEDIUM");
        hardButton.setUserData("HARD");

        easyButton.setOnAction(this::difficultySelected);
        mediumButton.setOnAction(this::difficultySelected);
        hardButton.setOnAction(this::difficultySelected);
    }

    /**
     * Handles the difficulty selection event triggered by the user clicking a difficulty button.
     * <p>
     * Depending on the selected game type, this method will load the appropriate game configuration.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    public void difficultySelected(ActionEvent event) {
        var difficultyString = (String)((Button) event.getSource()).getUserData();
        GameDifficulty difficultyValue = GameDifficulty.valueOf(difficultyString);

        try {
            if (gameType == GameType.Jumbles) {
                final String viewName = "Jumbles.fxml";

                var viewLoader = new FXMLLoader(getClass().getResource(viewName));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ViewHelpers.RenderView(viewLoader, stage);

                JumblesController controller = viewLoader.getController();
                controller.initialize(difficultyValue);
            }
            else if (gameType == GameType.WordLadder) {
                final String viewName = "WordLadder.fxml";

                var viewLoader = new FXMLLoader(getClass().getResource(viewName));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ViewHelpers.RenderView(viewLoader, stage);

                WordLadderController controller = viewLoader.getController();
                controller.initialize(difficultyValue);
            }
        }
        catch (Exception e) {
            // TODO: Error handling.
        }
    }
}
