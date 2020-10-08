package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.FileHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class NewSessionScreenController {

    @FXML
    VBox exerciseOverviewVbox;

    @FXML
    public void initialize() {
        // Her henter vi et session-objekt fra fil og deretter fyller vi på med den siste exercisen. Etter dette vises session-objektet
        Session session = FileHandler.readSessionFromFile("src/main/resources/session_controller_data.json");
        Exercise exercise = FileHandler.readExerciseFromFile("src/main/resources/exercise_controller_data.json");
        File exerciseFile = new File("src/main/resources/exercise_controller_data.json");
        if (exerciseFile.delete() == false) {
            System.out.println("Could not delete file");
        }
        else {
            System.out.println("Successfully deleted file");
        }
        if (!(exercise == null)) {
            session.addExercises(exercise);
        }
        FileHandler.writeSessionToFile("src/main/resources/session_controller_data.json", session);

        // Vise nåværende session
        for (Exercise e : session.getListOfExercises()) {
            Label label = new Label(e.getName() + ": ");
            String temp = "";
            for (Integer[] set : e.getSets()) {
                temp += Integer.toString((Integer) Array.get(set, 0)) +"x"+ Integer.toString((Integer) Array.get(set, 1)) + " ";
                label.setText(label.getText() + temp);
                temp = "";
            }
            exerciseOverviewVbox.getChildren().add(exerciseOverviewVbox.getChildren().size()-1, label);
        }

    }

    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        App.setRoot("NewExerciseScreen");
    }

    /**
     * Funksjon som legger til nåværende økt i 'tidligere økter'
     */
    @FXML
    private void addSessionButtonHandler() throws IOException{
        // Henter først et session-objekt fra filen det er lagret på:
        Session session = FileHandler.readSessionFromFile("src/main/resources/session_controller_data.json");

        // Deretter lages et sessionLogger-objekt som henter sin info fra fil
        SessionLogger logger = new SessionLogger();
        logger.load();
        logger.addSession(session);
        logger.save();

        // Nå vil vi bytte til startskjermen
        switchToStartScreen();
    }
}
