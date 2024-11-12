package com.wordgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final String homeScreenViewName = "HomeScreen.fxml";
        var viewLoader = new FXMLLoader(getClass().getResource(homeScreenViewName));

        ViewHelpers.RenderView(viewLoader, stage);
        HomeScreenController homeController = viewLoader.getController();
        homeController.Initialize(stage);

//        // Load the FXML file, which likely contains a layout better suited for responsive behavior.
//        var fxmlLoader = new FXMLLoader(MainApplication.class.getResource("HomeScreen.fxml"));
//        Pane root = fxmlLoader.load();
//
//        // Get the screen bounds
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
//        // Set the width and height of the stage based on percentage of screen size
//        double width = screenBounds.getWidth() * 0.75;  // 75% of screen width
//        double height = screenBounds.getHeight() * 0.75; // 75% of screen height
//
//        Scene scene = new Scene(root, width, height);
//
//        stage.setResizable(true);
//
//        stage.setTitle("Word Ladder Game");
//        stage.setScene(scene);
//        stage.show();
//
//        HomeScreenController controller = fxmlLoader.getController();
//        controller.initialization(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}