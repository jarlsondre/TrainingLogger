module gr2001 {
    requires javafx.controls;
    requires javafx.fxml;

    opens trainingLogger.ui to javafx.fxml;
    exports trainingLogger.ui;
}