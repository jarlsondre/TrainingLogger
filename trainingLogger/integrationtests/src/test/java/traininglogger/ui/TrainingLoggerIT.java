package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

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

  // TODO: Gjenta UI-testene, eventuelt lag en "Scenarie"-test.

  /**
   * Tester om det kommer flere bokser å skrive inn info på dersom man trykker på
   * 'legg til', i tillegg til om koden kræsjer
   */
  @Test
  public void addSetTest() {
    clickOn("#newSessionButton");
    clickOn("#newExerciseButton");
    // Legger inn info og trykker på knappen
    TextField repsTextField = lookup("#repsTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("10");
    clickOn(addSetButton);

    // Sjekker at de gamle boksene er tomme
    if (!repsTextField.getText().equals("")) {
      fail("Reps-boksen ble ikke tømt etter at man trykte på legg til sett");
    }
    if (!weightTextField.getText().equals("")) {
      fail("vekt-boksen ble ikke tømt etter at man trykte på legg til sett");
    }

    // Sjekker at det har kommet nye bokser som inneholder informasjonen vi la inn
    repsTextField = lookup("#repsTextField").query();
    weightTextField = lookup("#weightTextField").query();

    if (!repsTextField.getText().equals("10")) {
      System.out.println(repsTextField.getText());
      fail("Reps-boksen inneholdt ikke riktig informasjon etter at settet ble lagt til");
    }
    if (!weightTextField.getText().equals("100")) {
      fail("vekt-boksen inneholdt ikke riktig informasjon etter at settet ble lagt til");
    }
  }

  // TODO: Denne metoden er bare nødvendig dersom UI-testene vi kjører ender med at data lagres til fil:

  @AfterAll
  public static void deleteFiles() {
    Path pathToGeneratedFile = Paths.get(System.getProperty("user.home"), testPath);
    File generatedFile = new File(pathToGeneratedFile.toString());
    generatedFile.delete();
  }

}