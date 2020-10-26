package traininglogger.ui;

import javafx.fxml.FXML;
import traininglogger.core.Session;

import java.io.IOException;

public class StartScreenController {

    private TrainingLoggerController mainController;

    public void setMainController(TrainingLoggerController main){
        this.mainController = main;
    }

    @FXML
    private void switchToSessionScreen() throws IOException {
        try {
            mainController.changeToSessionScreen();
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra Start Screen til Session Screen");
        }
    }

    @FXML
    private void switchToNewSessionScreen() throws IOException {
        Session session = new Session();

        try {
            mainController.changeToNewSessionScreen();
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra Start Screen til New Session Screen");
        }

    }
}
