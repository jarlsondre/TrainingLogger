package traininglogger.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class NewSessionScreenController {

    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }
}
