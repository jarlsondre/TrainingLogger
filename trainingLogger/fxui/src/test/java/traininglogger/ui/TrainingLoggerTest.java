package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import traininglogger.core.SessionLogger;
import traininglogger.core.Set;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingLoggerTest extends ApplicationTest {

  private TrainingLoggerController controller;
  private static String testPath = "fxui-test-sessionlogger.json";

  @Override
  public void start(Stage stage) throws Exception {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingLoggerTest.fxml"));
    Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupItems() {
    DirectTrainingLoggerAccess access = new DirectTrainingLoggerAccess(new SessionLogger());
    access.setUserSessionLoggerPath(testPath);
    this.controller.setTrainingLoggerAccess(access);
    clickOn("#newSessionButton");
    try {
      Thread.sleep(2000);
      WaitForAsyncUtils.waitForFxEvents();
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    clickOn("#newExerciseButton");
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
  }

  /**
   * Tester om det kommer flere bokser å skrive inn info på dersom man trykker på
   * 'legg til', i tillegg til om koden kræsjer
   */
  @Test
  public void addSetTest() {
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

  /**
   * Tester om koden krsæjer dersom man forsøker å legge til en øvelse
   */
  @Test
  public void addExerciseTest() {
    // Legger til 3 sett
    TextField nameTextField = lookup("#titleTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    clickOn(nameTextField).write("Benkpress");
    addSet(10, 122.5, repsTextField, weightTextField);
    addSet(10, 122.5, repsTextField, weightTextField);
    addSet(10, 122.5, repsTextField, weightTextField);
    Button addExerciseButton = lookup("Legg til øvelse").query();
    clickOn(addExerciseButton);

    // Sjekker om øvelsen som har blitt lagt til kommer frem og stemmer med det som
    // ble skrevet inn
    VBox exerciseOverview = lookup("#exerciseOverviewVbox").query();
    TitledPane lastExerciseTitledPane = (TitledPane) exerciseOverview.getChildren().get(0);
    clickOn(lastExerciseTitledPane);
    VBox lastExerciseVbox = ((VBox) lastExerciseTitledPane.getContent());
    Label lastExerciseLabel = (Label) lastExerciseVbox.getChildren().get(0);
    String lastExerciseString = lastExerciseLabel.getText();
    assertEquals("Benkpress:" + "\n" + "122.5 kg x 10" + "\n" + "122.5 kg x 10" + "\n" + "122.5 kg x 10" + "\n" + "\n",
        lastExerciseString);
  }

  /**
   * Tester om koden kræsjer dersom man forsøker å legge til en økt og om
   * informasjonen som blir lagret er riktig
   */
  @Test
  public void addSessionTest() {
    // Legger til en session
    TextField nameTextField = lookup("#titleTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    clickOn(nameTextField).write("Knebøy");
    addSet(10, 122.5, repsTextField, weightTextField);
    addSet(10, 140, repsTextField, weightTextField);
    addSet(10, 140, repsTextField, weightTextField);
    Button addExerciseButton = lookup("Legg til øvelse").query();
    clickOn(addExerciseButton);
    clickOn("Legg til øvelse");
    clickOn(nameTextField).write("Markløft");
    addSet(10, 160, repsTextField, weightTextField);
    addSet(10, 170, repsTextField, weightTextField);
    clickOn(addExerciseButton);
    clickOn("#descriptionArea").write("Markløft var tungt, knebøy var lett");
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
    assertEquals("Knebøy:" + "\n" + "122.5kg x 10" + "\n" + "140.0kg x 10" + "\n" + "140.0kg x 10" + "\n" + "\n"
        + "Markløft:" + "\n" + "160.0kg x 10" + "\n" + "170.0kg x 10\n" + "\n" + "Beskrivelse: \n"
        + "Markløft var tungt, knebøy var lett", lastSessionString);
  }

  /**
   * Denne metoden tester om applikasjonen håndterer feil input
   */
  @Test
  public void wrongInputTest() {
    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    clickOn(weightTextField).write("80.0");
    clickOn(repsTextField).write("bokstaver");
    try {
      clickOn(addSetButton);
    } catch (Exception e) {
      fail(
          "Koden kræsjet da testen prøvde å skrive inn feil input. Dette skal i stedet håndteres ved en feilmelding på UI-et");
    }
  }

  @Test
  public void recordTest(){
    String name = "Knebøy";
    String record  = "150.0";
    TextField nameTextField = lookup("#titleTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    clickOn(nameTextField).write(name);
    addSet(10, 122.5, repsTextField, weightTextField);
    addSet(10, 140, repsTextField, weightTextField);
    addSet(10, Double.parseDouble(record), repsTextField, weightTextField);
    Button addExerciseButton = lookup("Legg til øvelse").query();
    clickOn(addExerciseButton);
    clickOn("#addSessionButton");


    clickOn("#recordButton");
    VBox vbox = lookup("#recordOverviewVbox").query();
    HBox hbox = (HBox) vbox.getChildren().get(0);
    String string = ((Label)hbox.getChildren().get(0)).getText();
    assertEquals(string, name + ": " + record);
    Button startScreen = lookup("Startskjerm").queryButton();
    clickOn(startScreen);

    clickOn("#newSessionButton");
    clickOn("#newExerciseButton");

    String newRecord  = "200.0";
    clickOn(nameTextField).write(name);
    addSet(10, 122.5, repsTextField, weightTextField);
    addSet(10, 140, repsTextField, weightTextField);
    addSet(10, Double.parseDouble(newRecord), repsTextField, weightTextField);
    clickOn(addExerciseButton);
    clickOn("#addSessionButton");

    clickOn("#recordButton");
    vbox = lookup("#recordOverviewVbox").query();
    hbox = (HBox) vbox.getChildren().get(0);
    string = ((Label)hbox.getChildren().get(0)).getText();
    assertEquals(string, name + ": " + newRecord);
  }

  private void addSet(int repetitions, double weight, TextField repsTextField, TextField weightTextField) {
    Button addSetButton = lookup("#addSetHbox > .button").query();
    Set set = new Set(repetitions, weight);
    clickOn(weightTextField).write(Double.toString(set.getWeight()));
    clickOn(repsTextField).write(Integer.toString(set.getRepetitions()));
    clickOn(addSetButton);
  }

  @AfterAll
  public static void deleteFiles() {
    Path pathToGeneratedFile = Paths.get(System.getProperty("user.home"), testPath);
    File generatedFile = new File(pathToGeneratedFile.toString());
    generatedFile.delete();
  }
}
