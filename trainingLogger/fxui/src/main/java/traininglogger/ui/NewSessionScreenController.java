package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.FileHandler;
import java.io.IOException;

import static traininglogger.ui.UpdateOverview.sessionToVboxConverter;

public class NewSessionScreenController {
    @FXML
    TrainingLoggerController mainController;

    @FXML
    VBox exerciseOverviewVbox;

    public void setMainController(TrainingLoggerController main){
        this.mainController = main;
    }

    @FXML
    public void initialize() {
        // Her henter vi et session-objekt fra fil og deretter fyller vi på med den siste exercisen. Etter dette vises session-objektet
        Session session = FileHandler.readSessionFromFile("src/main/resources/session_controller_data.json");
        Exercise exercise = FileHandler.readExerciseFromFile("src/main/resources/exercise_controller_data.json");
        if (!(exercise == null)) {
            session.addExercises(exercise);
        }
        FileHandler.writeSessionToFile("src/main/resources/session_controller_data.json", session);

        // Vise nåværende session
        exerciseOverviewVbox.getChildren().add(0, sessionToVboxConverter(session));

    }

    @FXML
    private void switchToStartScreen() throws IOException {
        try {
            mainController.changeToStartScreen();
            FileDeleter.deleteFile("src/main/resources/exercise_controller_data.json");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra New Session Screen til Start Screen");
        }
    }

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        try {
            mainController.changeToNewExerciseScreen();
            FileDeleter.deleteFile("src/main/resources/exercise_controller_data.json");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra New Session Screen til New Exercise Screen");
        }
    }

    /**
     * Funksjon som legger til nåværende økt i 'tidligere økter'
     */
    @FXML
    private void addSessionButtonHandler() throws IOException{
        // Henter først et session-objekt fra filen det er lagret på:
        Session session = FileHandler.readSessionFromFile("src/main/resources/session_controller_data.json");

        // Deretter lages et sessionLogger-objekt som henter sin info fra fil
       mainController.addSessionToSessionLogger(session);

        // Nå vil vi bytte til startskjermen
        mainController.changeToStartScreen();
    }
}
