package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

import java.io.IOException;
import java.lang.reflect.Array;

import static traininglogger.ui.UpdateOverview.sessionToVboxConverter;

public class SessionScreenController {

    @FXML VBox sessionOverviewVbox;

    SessionLogger logger;

    @FXML
    public void initialize() {
        // we want to fill the VBox with the sesssions we have. First we need to load our sessionLogger-object
        logger = new SessionLogger();
        logger.load();
        sessionOverviewUpdate();
    }

    @FXML
    private void sessionOverviewUpdate() {
        sessionOverviewVbox.getChildren().clear();
        for (Session session : logger) {
            VBox box = sessionToVboxConverter(session);
            TitledPane titledPane = new TitledPane(session.getDateString(), box);
            titledPane.setAlignment(Pos.CENTER_LEFT);
            titledPane.setExpanded(false);
            sessionOverviewVbox.getChildren().add(0, titledPane);

        }
    }


    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }



}
