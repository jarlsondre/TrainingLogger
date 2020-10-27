package traininglogger.ui;

import static traininglogger.ui.UpdateOverview.sessionToVboxConverter;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;



public class NewSessionScreenController {

  @FXML
  VBox exerciseOverviewVbox;

  private TrainingLoggerController mainController;
  private Session session;

  public NewSessionScreenController() {
    session = new Session();
  }

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  @FXML
  public void initialize() {
    exerciseOverviewVbox.getChildren().add(0, sessionToVboxConverter(session));
  }

  public void addExerciseToSession(Exercise exercise) {
    session.addExercises(exercise);
    exerciseOverviewVbox.getChildren().clear();
    exerciseOverviewVbox.getChildren().add(0, sessionToVboxConverter(session));
  }

  @FXML
  private void switchToStartScreen() throws IOException {
    try {
      mainController.changeToStartScreen();
    } catch (Exception e) {
      System.out.println("Kunne ikke bytte fra New Session Screen til Start Screen");
    }
  }

  @FXML
  private void switchToNewExerciseScreen() throws IOException {
    try {
      mainController.changeToNewExerciseScreen();
    } catch (Exception e) {
      System.out.println("Kunne ikke bytte fra New Session Screen til New Exercise Screen");
    }
  }

  /**
   * Funksjon som legger til nåværende økt i 'tidligere økter'
   */
  @FXML
  private void addSessionButtonHandler() throws IOException {
    mainController.addSessionToSessionLogger(session);
    session = new Session();
    exerciseOverviewVbox.getChildren().clear();
    // Nå vil vi bytte til startskjermen
    mainController.changeToStartScreen();
  }
}
