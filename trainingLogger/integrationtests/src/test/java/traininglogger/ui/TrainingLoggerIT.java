package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


public class TrainingLoggerIT extends ApplicationTest {

  private TrainingLoggerController controller;
  private static String testPath = "user-data.json";

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingLogger_it.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupItems() throws URISyntaxException {
    String port = System.getProperty("traininglogger.port");
    assertNotNull(port, "No traininglogger.port system property set");
    URI baseUri = new URI("http://localhost:" + port + "/traininglogger/");
    System.out.println("Base RemoteTrainingLoggerAcces URI: " + baseUri);
    this.controller.setTrainingLoggerAccess(new RemoteTrainingLoggerAccess(baseUri));
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
  }


  /**
   * Tester et scenario hvor brukeren legger inn et sett og trykker på "legg til øvelse" og deretter "legg til økten". 
   * Testen sjekker om det brukeren skrev inn stemmer med det som kommer opp på "tidliger økter"-skjermen. 
   */
  @Test
  public void ScenarioTest() {
    clickOn("#newSessionButton");
    clickOn("#newExerciseButton");

    // Legger til en session
    TextField nameTextField = lookup("#titleTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    clickOn(nameTextField).write("Knebøy");
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("5");
    Button addSetButton = lookup("#addSetHbox > .button").query();
    clickOn(addSetButton);
    Button addExerciseButton = lookup("Legg til øvelse").query();
    clickOn(addExerciseButton);
    clickOn("#descriptionArea").write("Knebøy var lett");
    Button addSessionButton = lookup("Legg til økten").query();
    clickOn(addSessionButton);

    // Henter ut dataene som har blitt lagret inne i "tidligere økter"
    clickOn("#earlierSessionsButton");
    VBox vBox = lookup("#sessionOverviewVbox").query();
    TitledPane lastSessionTitledPane = (TitledPane) vBox.getChildren().get(0);
    clickOn(lastSessionTitledPane);
    VBox lastSessionVbox = ((VBox) lastSessionTitledPane.getContent());
    Label lastSessionLabel = (Label) lastSessionVbox.getChildren().get(0);
    String lastSessionString = lastSessionLabel.getText();

    // Sjekker om dataene er like de vi skrev inn
    assertEquals("Knebøy:" + "\n" + "100.0kg x 5" + "\n" + "\n" + "Beskrivelse: \n"
        + "Knebøy var lett", lastSessionString);
  }

  @AfterAll
  public static void deleteFiles() {
    Path pathToGeneratedFile = Paths.get(System.getProperty("user.home"), testPath);
    File generatedFile = new File(pathToGeneratedFile.toString());
    generatedFile.delete();
  }

}