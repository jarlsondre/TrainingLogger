package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglogger.core.SessionLogger;

public class RecordScreenController {
    @FXML
    VBox recordOverviewVbox;

    private TrainingLoggerController mainController;

    public void setMainController(TrainingLoggerController main) {
        this.mainController = main;
    }

    @FXML
    private void switchToStartScreen() {
        try {
            mainController.changeToStartScreen();
        } catch (Exception e) {
            System.out.println("Kunne ikke bytte fra Session Screen til Start Screen");
        }
    }

    public void updateRecordOverview(SessionLogger sessionLogger){
        recordOverviewVbox.getChildren().clear();
        for(String name : sessionLogger.getRecords().keySet()){
            HBox record = new HBox();
            record.getChildren().add(new Label(name +": " + sessionLogger.getRecords().get(name)));
            recordOverviewVbox.getChildren().add(record);
        }
    }

}
