package traininglogger.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import traininglogger.core.Exercise;
import traininglogger.core.Set;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class NewExerciseScreenControllerTest extends ApplicationTest {

  private NewExerciseScreenController newExerciseScreenController;
  private AppController appController;


  @Override
  public void start(Stage stage) {
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AppTest.fxml"));
    Parent root = loader.load();
    this.appController = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
    }
    catch(Exception e) {
        e.printStackTrace();
        System.out.println(e); 
    }
  }

  @BeforeEach
  public void setUp() {
      clickOn("Ny økt");
      clickOn("Legg til øvelse");
  }

 @Test
  public void testController_initial() {
    assertNotNull(this.appController);
  }


  /**
   * Denne metoden tester å legge til to sett og sjekker deretter om settene som har blitt lagret er riktige.
   */
  @Test
  public void testAddSet(){

    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    Set set1 = new Set(5, 100);
    Set set2 = new Set(8, 102.5);
    clickOn(weightTextField).write(Double.toString(set1.getWeight()));
    clickOn(repsTextField).write(Integer.toString(set1.getRepetitions()));
    clickOn(addSetButton);
    clickOn(weightTextField).write(Double.toString(set2.getWeight()));
    clickOn(repsTextField).write(Integer.toString(set2.getRepetitions()));
    clickOn(addSetButton);
    clickOn("Legg til øvelse");
    clickOn("Legg til økten");
    // Her har vi skrevet til filen "jsonSaveTest.json" lokalisert i target-filen. Nå må vi sjekke hva denne inneholder
      //Deretter skal vi bruke equals-metoden for å finne ut om det den inneholder er riktig.
      //

    // I tillegg må vi sørge for at sampleResources ikke blir lagret inn i denne filen.


 }

  public void testAddExercise() {
    TextField titleTextField = lookup("#titleTextField").query();
    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    Button addExerciseButton = lookup("#addExerciseButton").query();
    clickOn(titleTextField).write("Benkpress");
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("5");
    clickOn(addSetButton);
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("5");
    clickOn(addSetButton);

    // Bytte av skjerm kommer til å utløse et unntak som er håndtert av en try/catch i en annen fil :)
    clickOn(addExerciseButton);
 }

  /**
   * Denne metoden tester om applikasjonen håndterer feil input
   */
  public void testWrongInput() {
    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();

    // Det skal ikke være lov å skrive inn noe annet en heltall
    clickOn(weightTextField).write("82.5");
    clickOn(repsTextField).write("askf");
    try {
      clickOn(addSetButton);
    }
    catch(Exception e) {
      fail();
    }
 }
}