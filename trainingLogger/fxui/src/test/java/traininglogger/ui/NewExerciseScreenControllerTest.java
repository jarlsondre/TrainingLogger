package traininglogger.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewExerciseScreenControllerTest extends ApplicationTest {

  private NewExerciseScreenController controller;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewExerciseScreen_test.fxml"));
    Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
  }

  @Test
  public void testAddSet(){
    TextField titleTextField = lookup("#titleTextField").query();
    clickOn(titleTextField).write("Benkpress");
    TextField weightTextField = lookup("#weightTextField").query();
    TextField repsTextField = lookup("#repsTextField").query();
    Button addSetButton = lookup("#addSetHbox > .button").query();
    clickOn(weightTextField).write("100");
    clickOn(repsTextField).write("5");
    clickOn(addSetButton);
    /* Her mener jeg at controlleren burde inneholdt en Exercise i stedet for
    en liste av Integers (sets). Hadde vært naturlig å sjekke om denne Exercisen hadde blitt oppdatert som forventet.*/
    assertTrue(controller.getSetElementByIndex(0) == 100);
    assertTrue(controller.getSetElementByIndex(1) == 5);
  }
}