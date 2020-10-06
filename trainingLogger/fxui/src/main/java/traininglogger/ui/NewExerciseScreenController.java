package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewExerciseScreenController {
    @FXML
    VBox addSetVbox;

    @FXML
    HBox addSetHbox;

    @FXML
    TextField weightTextField, repsTextField;

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        App.setRoot("NewSessionScreen");
    }

    @FXML
    private void addExerciseButtonHandler() throws IOException {
        switchToNewExerciseScreen();
    }

    @FXML
    private void addSetButtonHandler(){
        // Legg til ting her
    }
}
