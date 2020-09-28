module gr2001 {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens traininglogger.ui to javafx.fxml;
    exports traininglogger.ui;
    exports traininglogger.core;
}