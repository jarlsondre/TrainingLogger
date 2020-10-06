package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class NewSessionScreenController {

    @FXML
    Button newExerciseButton;

    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        App.setRoot("NewExerciseScreen");
    }
}
