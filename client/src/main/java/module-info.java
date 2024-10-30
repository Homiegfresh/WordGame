module com.wordgame.wordgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires com.wordgame.models;

    opens com.wordgame to javafx.fxml;
    exports com.wordgame;
}