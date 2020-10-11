package traininglogger.ui;

import javafx.fxml.FXML;
import traininglogger.core.Session;
import traininglogger.json.FileHandler;

import java.io.File;
import java.io.IOException;

public class StartScreenController {

    @FXML
    private void switchToSessionScreen() throws IOException {
        try {
            App.setRoot("SessionScreen");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra Start Screen til Session Screen");
        }
    }

    @FXML
    private void switchToNewSessionScreen() throws IOException {
        Session session = new Session();

        // deleting the file and recreating it
        File sessionFile = new File("src/main/resources/session_controller_data.json");
        if (sessionFile.delete() == false) {
            System.out.println("Could not session delete file");
        }
        else {
            System.out.println("Successfully deleted session file");
        }
        FileHandler.writeSessionToFile("src/main/resources/session_controller_data.json", session);

        try {
            App.setRoot("NewSessionScreen");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra Start Screen til New Session Screen");
        }

    }
}
