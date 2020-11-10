package traininglogger.ui;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Set;


public class NewExerciseScreenController {

  @FXML
  VBox addSetVbox;
  @FXML
  HBox addSetHbox;
  @FXML
  TextField weightTextField;
  @FXML
  TextField repsTextField;
  @FXML
  TextField titleTextField;
  @FXML
  Button addExerciseButton;
  private Exercise exercise = new Exercise();
  private TrainingLoggerController mainController;
  private NewSessionScreenController newSessionScreenController;
  private boolean theTextFieldIsBlank = true;

  @FXML
  void initialize() {
    this.titleTextField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String oldValue,
                          String newValue) {
        boolean setHasBeenAdded = addSetVbox.getChildren().size() > 1;
        theTextFieldIsBlank = newValue.isBlank();
        if (theTextFieldIsBlank) {
          addExerciseButton.setDisable(true);
        } else {
          if (setHasBeenAdded) {
            addExerciseButton.setDisable(false);
          }
        }
      }
    });
  }

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  public void setNewSessionScreenController(NewSessionScreenController newSessionScreenController) {
    this.newSessionScreenController = newSessionScreenController;
  }

  @FXML
  private void switchToNewSessionScreen() throws IOException {
    try {
      mainController.changeToNewSessionScreen();
      resetInputFields();
    } catch (Exception e) {
      System.out.println("Kunne ikke bytte fra New Exercise Screen til New Session Screen");
    }

  }

  @FXML
  private void addExerciseButtonHandler() throws IOException {
    this.exercise.setName(this.titleTextField.getText());
    this.newSessionScreenController.addExerciseToSession(this.exercise);
    this.exercise = new Exercise();
    resetInputFields();
    this.addExerciseButton.setDisable(true);
    this.mainController.changeToNewSessionScreen();
  }

  @FXML
  private void addSetButtonHandler() {
    try {
      double weight = Double.parseDouble(weightTextField.getText());
      int reps = Integer.parseInt(repsTextField.getText());
      if (addSetVbox.getChildren().get(0) instanceof Label) {
        addSetVbox.getChildren().remove(0);
      }
      exercise.addSets(new Set(reps, weight));
      addHboxToVbox();
      weightTextField.setText("");
      repsTextField.setText("");
      if (this.addExerciseButton.isDisabled() && (!theTextFieldIsBlank)) {
        this.addExerciseButton.setDisable(false);
      }
    } catch (Exception exception) {
        if (addSetVbox.getChildren().get(0) instanceof Label) {
          addSetVbox.getChildren().remove(0);
        }
        String errorLabelString = "";
        if (exception instanceof NumberFormatException) {
          if (exception.getMessage().equals("empty String")) {
            errorLabelString = "Begge feltene må være utfylte";
          } else {
            errorLabelString = "Vekt må være et desimaltall og reps et heltall";
          }
        } else if (exception instanceof IllegalArgumentException) {
          errorLabelString = "Input kan ikke være større enn 1000";
        }
        Label errorLabel = new Label(errorLabelString);
        addSetVbox.getChildren().add(0, errorLabel);
    }
  }

  private void resetInputFields() {
    Node temp = addSetVbox.getChildren().get(addSetVbox.getChildren().size() - 1);
    addSetVbox.getChildren().clear();
    addSetVbox.getChildren().add(temp);
    titleTextField.setText("");
  }

  // Legger til en ny hbox i addSetVbox med verdiene fra den nederste Hboxen.
  private void addHboxToVbox() {
    FXMLLoader loader = new FXMLLoader();
    try {
      Node node = loader.load(getClass().getResource("HboxTemplate.fxml").openStream());
      addSetVbox.getChildren().add(addSetVbox.getChildren().size() - 1, node);
      HboxTemplateController controller = loader.getController();
      controller.setTextField(weightTextField.getText(), repsTextField.getText());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Metode for å hente ut hvilken exercise som er lagret i controlleren. Denne
   * kan blant annet brukes for å teste om exercise-objektet blir instansiert
   * riktig
   *
   * @return Exercise-objektet til controlleren
   */

  public Exercise getExercise() {
    return this.exercise;
  } // TODO: Denne metoden brukes bare i test. Beholde?

}
