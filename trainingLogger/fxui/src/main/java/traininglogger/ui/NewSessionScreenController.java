package traininglogger.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.Set;

public class NewSessionScreenController {

  @FXML
  VBox exerciseOverviewVbox;

  @FXML
  Button addSessionButton;

  @FXML
  TextArea descriptionArea;

  private TrainingLoggerController mainController;
  private Session session = new Session();

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  public void updateExerciseOverview() {
    this.exerciseOverviewVbox.getChildren().clear();
    for (Exercise exercise : this.session) {
      VBox box = putExerciseInABox(exercise);
      TitledPane titledPane = new TitledPane(exercise.getName(), box);
      titledPane.setAlignment(Pos.CENTER_LEFT);
      titledPane.setExpanded(false);
      this.exerciseOverviewVbox.getChildren().add(titledPane);
    }
  }

  private VBox putExerciseInABox(Exercise exercise) {
    VBox exerciseBox = new VBox();
    StringBuffer exerciseAsString = new StringBuffer(exercise.getName() + ":\n");
    for (Set set : exercise) {
      exerciseAsString.append(set.getWeight() + " kg x " + set.getRepetitions() + "\n");
    }
    exerciseAsString.append("\n");
    String finalString = exerciseAsString.toString();
    Label exerciseInALabel = new Label(finalString);
    exerciseBox.getChildren().add(exerciseInALabel);
    return exerciseBox;
  }

  public void addExerciseToSession(Exercise exercise) {
    this.session.addExercises(exercise);
    updateExerciseOverview();
    if (this.addSessionButton.isDisabled()) {
      this.addSessionButton.setDisable(false);
    }
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
    this.session.setDescription(descriptionArea.getText());
    descriptionArea.clear();
    mainController.addSessionToSessionLogger(this.session);
    this.session = new Session();
    updateExerciseOverview();
    this.addSessionButton.setDisable(true);
    mainController.changeToStartScreen();
  }
}
