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
    loadSubControllersAndScreenNodes();
    changeToStartScreen();
  }

  private void loadSubControllersAndScreenNodes() {
    // Load StartScreen Node
    FXMLLoader loader = new FXMLLoader();
    try {
      this.startScreen = loader.load(getClass().getResource("StartScreen.fxml").openStream());
      StartScreenController controller = loader.getController();
      controller.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    // Load Log Screen Node and Controller. Register this as main Controller.
    loader = new FXMLLoader();
    try {
      this.sessionScreen = loader.load(getClass().getResource("SessionScreen.fxml").openStream());
      this.sessionScreenController = loader.getController();
      this.sessionScreenController.setMainController(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Load New Session Screen Node and Controller. Register this as main
    // Controller.
    loader = new FXMLLoader();
    try {
      this.newSessionScreen = loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
      this.newSessionScreenController = loader.getController();
      this.newSessionScreenController.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    // Load New Exercise Screen Node and Controller. Register controllers.
    loader = new FXMLLoader();
    try {
      this.newExerciseScreen = loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
      NewExerciseScreenController newExerciseScreenController = loader.getController();
      newExerciseScreenController.setMainController(this);
      newExerciseScreenController.setNewSessionScreenController(this.newSessionScreenController);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void changeToStartScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(this.startScreen);
  }

  public void changeToNewSessionScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(newSessionScreen);
  }

  public void changeToSessionScreen() {
    this.sessionScreenController.setSessionLogger(this.trainingLoggerAccess.getSessionLogger()); //TODO: 
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(sessionScreen);
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
    // TODO: Se tilsvarende metode hos Hallvard
    // (TodoModellController.setTodoModelAccess())
    // Trenger vi siste linja?
  }

  public void deleteLog() {
    this.trainingLoggerAccess.deleteAll();
  }
}
