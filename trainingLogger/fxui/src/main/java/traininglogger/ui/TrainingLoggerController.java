package traininglogger.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;

public class TrainingLoggerController {

  private TrainingLoggerAccess trainingLoggerAccess;

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

  private SessionScreenController sessionScreenController;
  private NewSessionScreenController newSessionScreenController;

  @FXML
  public void initialize() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    try {
      sessionScreen = loader.load(getClass().getResource("SessionScreen.fxml").openStream());
      sessionScreenController = loader.getController();
      sessionScreenController.setMainController(this);
    } catch (Exception e) {
      e.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      startScreen = loader.load(getClass().getResource("StartScreen.fxml").openStream());
      StartScreenController controller = loader.getController();
      controller.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      newSessionScreen = loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
      newSessionScreenController = loader.getController();
      newSessionScreenController.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      newExerciseScreen = loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
      NewExerciseScreenController newExerciseScreenController = loader.getController();
      newExerciseScreenController.setMainController(this);
      newExerciseScreenController.setNewSessionScreenController(newSessionScreenController);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    changeToStartScreen();
  }

  public void changeToNewSessionScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(newSessionScreen);
  }

  public void changeToSessionScreen() {
    this.sessionScreenController.setSessionLogger(this.trainingLoggerAccess.getSessionLogger());
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(sessionScreen);
  }

  public void changeToStartScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(startScreen);
  }

  public void changeToNewExerciseScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(newExerciseScreen);
  }

  public void addSessionToSessionLogger(Session session) {
    this.trainingLoggerAccess.addSession(session);
    sessionScreenController.sessionOverviewUpdate();
  }

public void setTrainingLoggerAccess(TrainingLoggerAccess trainingLoggerAccess) {
  this.trainingLoggerAccess = trainingLoggerAccess;
  // TODO: Se tilsvarende metode hos Hallvard (TodoModellController.setTodoModelAccess())
  // Trenger vi siste linja?
}

public void deleteLog(){
  this.trainingLoggerAccess.deleteAll();
}
}
