package traininglogger.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class StartScreenController {

    @FXML
    private void switchToSessionScreen() throws IOException {
        App.setRoot("SessionScreen");
    }

    @FXML
    private void switchToNewSessionScreen() throws IOException {
        App.setRoot("NewSessionScreen");
    }
}
