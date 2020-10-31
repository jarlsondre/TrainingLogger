package traininglogger.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;

public class NewSessionScreenController {

  @FXML
  VBox exerciseOverviewVbox;

  private TrainingLoggerController mainController;
  private Session session = new Session();

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  public void updateExerciseOverview() {
    this.exerciseOverviewVbox.getChildren().clear();
    // VBox currentSessionAsBox = putSessionInABox(this.session); TODO: lage updateExerciseOverview tilsvarende
    // updateSessionOverview i SessionScreenController. 
    // this.exerciseOverviewVbox.getChildren().add(currentSessionAsBox);
  }

  public void addExerciseToSession(Exercise exercise) {
    this.session.addExercises(exercise);
    updateExerciseOverview();
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
    mainController.addSessionToSessionLogger(this.session);
    this.session = new Session();
    updateExerciseOverview();
    mainController.changeToStartScreen();
  }
}
