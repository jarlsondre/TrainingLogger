package trainingLogger.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import trainingLogger.core.Session;
import trainingLogger.core.SessionLogger;

public class AppController {
  @FXML
  Button addDescriptionButton;

  @FXML
  Button loadButton, saveButton, deleteButton;

  @FXML
  TextArea descriptionTextArea;

  @FXML
  VBox sessionOverviewVBox;

  SessionLogger sessionLogger = new SessionLogger();

  @FXML
  public void handleAddDescriptionButton() {
    String description = descriptionTextArea.getText(); // Tar inn teksten fra TextArea
    descriptionTextArea.setText(""); // Fjerne teksten i TextArea
    if (!description.isEmpty()) { // Sjekker om strengen er tom, slik at vi ikke må behnadle en tom streng
      // Her kan du gjøre ting med strengen
      // addSessionToSessionOverview(new Session(description)); //Bare for å ha en
      // funksjon her midlertidig.
      sessionLogger.addSession(new Session(description));
    }
    updateSessionOverview();
  }

  public void updateSessionOverview() {
    sessionOverviewVBox.getChildren().clear();
    for (Session session : sessionLogger) {
      addSessionToSessionOverview(session);
    }
  }

  // Legger til en titlePane med en vBox som kan fylles med innhold. Her blir
  // descrption lagt til som en label i vboxen
  public void addStringWithTitleToSessionOverview(String title, String description) {
    VBox vBox = new VBox();
    vBox.getChildren().add(new Label(description));
    TitledPane titledPane = new TitledPane(title, vBox);
    titledPane.setAlignment(Pos.CENTER_LEFT);
    titledPane.setExpanded(false);
    sessionOverviewVBox.getChildren().add(0, titledPane);
  }

  public void addSessionToSessionOverview(Session session) {
    addStringWithTitleToSessionOverview(session.getDateString(), session.getDescription());
  }

  @FXML
  public void saveButtonHandler() {
    System.out.println("Save");
    sessionLogger.save();
  }

  @FXML
  public void loadButtonHandler() {
    System.out.println("Load");
    sessionLogger.load();
    updateSessionOverview();
  }

  @FXML
  public void deleteButtonHandler() {
    System.out.println("Delete");
    sessionLogger.delete();
    updateSessionOverview();

  }

}
