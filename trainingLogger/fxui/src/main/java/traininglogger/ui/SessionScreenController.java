package traininglogger.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class SessionScreenController {

    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }
}
