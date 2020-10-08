package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

import java.io.IOException;

public class SessionScreenController {

    @FXML VBox sessionOverviewVbox;

    @FXML
    public void initialize() {
        // we want to fill the VBox with the sesssions we have. First we need to load our sessionLogger-object
        SessionLogger logger = new SessionLogger();
        logger.load();
    }


    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }



}
