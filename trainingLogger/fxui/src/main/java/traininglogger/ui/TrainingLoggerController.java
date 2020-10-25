package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

import java.io.IOException;

public class TrainingLoggerController {
    @FXML
    VBox mainVbox;

    @FXML
    Node sessionScreen;

    @FXML
    Node startScreen;

    @FXML
    Node newSessionScreen;

    @FXML
    Node newExerciseScreen;



    SessionScreenController sessionScreenController;
    NewExerciseScreenController newExerciseScreenController;
    NewSessionScreenController newSessionScreenController;

    private SessionLogger sessionLogger;

    @FXML
    public void initialize(){FXMLLoader loader = new FXMLLoader();
        sessionLogger = new SessionLogger();
        sessionLogger.load();

        try {
            sessionScreen = loader.load(getClass().getResource("SessionScreen.fxml").openStream());
            sessionScreenController = loader.getController();
            sessionScreenController.setMainController(this);
            sessionScreenController.setSessionLogger(this.sessionLogger);
        } catch (Exception e){
            e.printStackTrace();
        }

        loader = new FXMLLoader();
        try {
            startScreen  =  loader.load(getClass().getResource("StartScreen.fxml").openStream());
            StartScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        loader = new FXMLLoader();
        try {
            newSessionScreen  =  loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
            newSessionScreenController = loader.getController();
            newSessionScreenController.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        loader = new FXMLLoader();
        try {
            newExerciseScreen = loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
            newExerciseScreenController = loader.getController();
            newExerciseScreenController.setMainController(this);
            newExerciseScreenController.setNewSessionScreenController(newSessionScreenController);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        changeToStartScreen();
    }

    public void changeToNewSessionScreen(){
        mainVbox.getChildren().clear();
        mainVbox.getChildren().add(newSessionScreen);
    }

    public void changeToSessionScreen(){
        mainVbox.getChildren().clear();
        mainVbox.getChildren().add(sessionScreen);
    }

    public void changeToStartScreen(){
        mainVbox.getChildren().clear();
        mainVbox.getChildren().add(startScreen);
    }

    public void changeToNewExerciseScreen(){
        mainVbox.getChildren().clear();
        mainVbox.getChildren().add(newExerciseScreen);
    }

    public void addSessionToSessionLogger(Session session){
        sessionLogger.addSession(session);
        sessionScreenController.sessionOverviewUpdate();
        sessionLogger.save();
    }



}
