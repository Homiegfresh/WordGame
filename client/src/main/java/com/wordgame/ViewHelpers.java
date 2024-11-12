package com.wordgame;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

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
}
