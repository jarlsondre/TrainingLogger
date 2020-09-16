module gr2001 {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens trainingLogger.ui to javafx.fxml;
    exports trainingLogger.ui;
    exports trainingLogger.core;
}