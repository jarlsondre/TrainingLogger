module gr2001.fxui {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires gr2001.core;

    opens traininglogger.ui to javafx.fxml;
    exports traininglogger.ui;
}