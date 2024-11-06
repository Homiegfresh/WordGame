package com.wordgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file, which likely contains a layout better suited for responsive behavior.
        var fxmlLoader = new FXMLLoader(MainApplication.class.getResource("HomeScreen.fxml"));
        Pane root = fxmlLoader.load();

        // Get the screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Set the width and height of the stage based on percentage of screen size
        double width = screenBounds.getWidth() * 0.75;  // 75% of screen width
        double height = screenBounds.getHeight() * 0.75; // 75% of screen height

        // Create a scene with the loaded root
        Scene scene = new Scene(root, width, height);

        // Set the stage to be resizable
        stage.setResizable(true);

        // Set the scene and show the stage
        stage.setTitle("Word Ladder Game");
        stage.setScene(scene);
        stage.show();

        // Ensure initialization is called if necessary
        HomeScreenController controller = fxmlLoader.getController();
        controller.initialization(stage);
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("HomeScreen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//
//        HomeScreenController controller = fxmlLoader.getController();
//        controller.initialization();
//
//        stage.setTitle("Word Ladder Game");
//        stage.setScene(scene);
//        //stage.setFullScreen(true);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}