package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.core.Set;

public class SessionScreenController {

  @FXML
  private VBox sessionOverviewVbox;

  private TrainingLoggerController mainController;

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  @FXML
  private void deleteButtonHandler() {
    this.mainController.deleteLog();
  }

  @FXML
  private void switchToStartScreen() {
    try {
      mainController.changeToStartScreen();
    } catch (Exception e) {
      System.out.println("Kunne ikke bytte fra Session Screen til Start Screen");
    }
  }

  /**
   * Oppdaterer sessionOverviewVbox til å innholde
   * de nåværende Sessionobjektene i sessionLogger-objektet.
   */
  public void updateSessionOverview(SessionLogger sessionLogger) {
    this.sessionOverviewVbox.getChildren().clear();
    for (Session session : sessionLogger) {
      VBox box = putSessionInBox(session);
      TitledPane titledPane = new TitledPane(session.getDateAsString(), box);
      titledPane.setAlignment(Pos.CENTER_LEFT);
      titledPane.setExpanded(false);
      this.sessionOverviewVbox.getChildren().add(0, titledPane);

    }
  }

  private VBox putSessionInBox(Session session) {
    VBox sessionBox = new VBox();
    String sessionAsString = "";
    for (Exercise exercise : session) {
      sessionAsString += exercise.getName() + ":\n";
      for (Set set : exercise) {
        sessionAsString += set.getWeight() + "kg x " + set.getRepetitions() + "\n";
      }
      sessionAsString += "\n";
    }
    System.out.println(session);
    System.out.println(session.getDescription());
    if (!session.getDescription().equals("")){
      sessionAsString += "Beskrivelse: \n";
      sessionAsString += session.getDescription();
    }
    Label sessionInALabel = new Label(sessionAsString);
    sessionBox.getChildren().add(sessionInALabel);
    return sessionBox;
  }
}
