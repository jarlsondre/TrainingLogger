package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    SessionScreenController sessionScreenController;

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

        }
        catch (Exception e){
            e.printStackTrace();
        }

        loader = new FXMLLoader();
        try {
            startScreen  =  loader.load(getClass().getResource("StartScreen.fxml").openStream());
            mainVbox.getChildren().add(startScreen);
            StartScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        changeToStartScreen();
    }

    public void changeToNewSessionScreen(){
        mainVbox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
            NewSessionScreenController controller = loader.getController();
            controller.setMainController(this);
            mainVbox.getChildren().add(node);
        } catch (IOException ex) {
            ex.printStackTrace();
        }



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

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            NewExerciseScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addSessionToSessionLogger(Session session){
        sessionLogger.addSession(session);
        sessionScreenController.sessionOverviewUpdate();

    }


}
