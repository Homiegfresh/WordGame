package com.wordgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class ViewHelpers {
    private ViewHelpers() {
        throw new UnsupportedOperationException("Cannot instantiate service helper class.");
    }

    public static void RenderView(FXMLLoader viewLoader, Stage currentWindow) throws IOException {
        // Load the FXML into memory.
        Pane root = viewLoader.load();

        // Create a new scene to render into the window.
        Scene newScene = new Scene(root);

        // Set up the stage with the new scene.
        currentWindow.setResizable(true);
        currentWindow.setTitle("Diction-ary");
        currentWindow.setScene(newScene);

        currentWindow.show();
    }

    /**
     * Navigate to a specified FXML screen and optionally initialize its controller.
     *
     * @param stage        The current stage to set the new scene on.
     * @param fxmlPath     The path to the FXML file.
     * @param initializer  A Consumer to initialize the controller (can be null if no initialization is needed).
     */
    public static <T> void Navigate(Stage stage, String fxmlPath, Consumer<T> initializer) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(ViewHelpers.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Get the controller
            Object controller = loader.getController();

            // Run the initializer if provided and the controller is of the expected type
            if (initializer != null && controller != null) {
                try {
                    @SuppressWarnings("unchecked")
                    T typedController = (T) controller;
                    initializer.accept(typedController);
                } catch (ClassCastException e) {
                    System.err.println("Controller type mismatch: " + e.getMessage());
                }
            }

            // Set the scene on the stage
            stage.setScene(new Scene(root));

            // Set up the stage with the new scene.
            stage.setResizable(true);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    public static void RedirectHome(Stage stage) {
        ViewHelpers.<HomeScreenController>Navigate(stage, "HomeScreen.fxml", controller ->
            controller.Initialize(stage)
        );
    }
}
