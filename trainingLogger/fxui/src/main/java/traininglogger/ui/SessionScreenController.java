package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SessionScreenController {

    @FXML VBox sessionOverviewVbox;


    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }



}
