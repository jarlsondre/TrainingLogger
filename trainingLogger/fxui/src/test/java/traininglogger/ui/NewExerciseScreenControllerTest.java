package traininglogger.ui;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import traininglogger.core.Exercise;
import traininglogger.json.FileHandler;

import static org.junit.jupiter.api.Assertions.*;

public class NewExerciseScreenControllerTest extends ApplicationTest {

  private NewExerciseScreenController newExerciseScreenController;


  @Override
  public void start(Stage stage) {
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewExerciseScreen_test.fxml"));
    Parent root = loader.load();
    this.newExerciseScreenController = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
    }
    catch(Exception e) {
      System.out.println("Feilet under start"); 
    }
  }



  @Test
  public void testController_initial() {
    assertNotNull(this.newExerciseScreenController);
  }


  /**
   * Denne metoden tester om et sett legges til riktig. Den sammenligner inneholdene til settet, men ikke
   * navnet på øvelsen.
   */
  @Test
  public void testAddSet(){
    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("5");
    clickOn(addSetButton);

    Exercise testExercise = new Exercise();
    testExercise.addSets(100, 5);
    assertTrue(Exercise.isEqual(testExercise.getSets(), newExerciseScreenController.getExercise().getSets()));
  }




  @Test
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

    // Nå vil vi sjekke at riktig info er skrevet til mellomlagrings-filen
    Exercise exercise = FileHandler.readExerciseFromFile("src/main/resources/exercise_controller_data.json");
    FileDeleter.deleteFile("src/main/resources/exercise_controller_data.json");
    Exercise testExercise = new Exercise("Benkpress", 100, 5, 100, 5);
    assertEquals(exercise, testExercise);
  }

  /**
   * Denne metoden tester om applikasjonen håndterer feil input
   */
  @Test
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

    // Nå vi må sjekke at det vi skrev inn ikke har blitt lagret
    Exercise exercise = newExerciseScreenController.getExercise();

    // Dersom exercise ikke har noen sett så har ikke settet blitt lagret
    if (!(exercise.getSets().size() == 0)) {
      Integer[] set = exercise.getSet(exercise.getSets().size()-1);
      String[] testSet = {"82.5", "askf"};
      assertFalse(testSet[0].equals(set[0].toString()) && testSet[1].equals(set[1].toString()));
    }



  }

}