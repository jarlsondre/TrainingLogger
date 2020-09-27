package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class AppController {
  @FXML
  Button addDescriptionButton;

  @FXML
  Button loadButton;
  
  @FXML 
  Button saveButton;

  @FXML
  Button deleteButton;

  @FXML
  TextArea descriptionTextArea;

  @FXML
  VBox sessionOverviewBox;

  SessionLogger sessionLogger = new SessionLogger();

  @FXML
  public void handleAddDescriptionButton() {
    String description = descriptionTextArea.getText(); // Tar inn teksten fra TextArea
    descriptionTextArea.setText(""); // Fjerne teksten i TextArea
    if (!description.isEmpty()) { // Sjekker om strengen er tom, 
      // slik at vi ikke må behnadle en tom streng.
      // Her kan du gjøre ting med strengen
      // addSessionToSessionOverview(new Session(description)); //Bare for å ha en
      // funksjon her midlertidig.
      sessionLogger.addSession(new Session(description));
    }
    updateSessionOverview();
  }

  public void updateSessionOverview() {
    sessionOverviewBox.getChildren().clear();
    for (Session session : sessionLogger) {
      addSessionToSessionOverview(session);
    }
  }

  // Legger til en titlePane med en VBox som kan fylles med innhold. Her blir
  // descrption lagt til som en label i vboxen
  public void addStringWithTitleToSessionOverview(String title, String description) {
    VBox box = new VBox();
    box.getChildren().add(new Label(description));
    TitledPane titledPane = new TitledPane(title, box);
    titledPane.setAlignment(Pos.CENTER_LEFT);
    titledPane.setExpanded(false);
    sessionOverviewBox.getChildren().add(0, titledPane);
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
