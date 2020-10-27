package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

import static traininglogger.ui.UpdateOverview.sessionToVboxConverter;

public class SessionScreenController {

    @FXML
    VBox sessionOverviewVbox;

    private TrainingLoggerController mainController;
    private SessionLogger sessionLogger;

    public void setSessionLogger(SessionLogger sessionLogger){
        this.sessionLogger = sessionLogger;
        sessionOverviewUpdate();
    }

    public void setMainController(TrainingLoggerController main){
        this.mainController = main;
    }

    @FXML
    public void sessionOverviewUpdate() {
        sessionOverviewVbox.getChildren().clear();
        for (Session session : sessionLogger) {
            VBox box = sessionToVboxConverter(session);
            TitledPane titledPane = new TitledPane(session.getDateAsString(), box);
            titledPane.setAlignment(Pos.CENTER_LEFT);
            titledPane.setExpanded(false);
            sessionOverviewVbox.getChildren().add(0, titledPane);

        }
    }

    @FXML
    private void deleteButtonHandler(){
        sessionLogger.deleteAll();
        sessionOverviewUpdate();
        mainController.saveSessionLogger();
    }


    @FXML
    private void switchToStartScreen() {
        try {
            mainController.changeToStartScreen();
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra Session Screen til Start Screen");
        }
    }



}
