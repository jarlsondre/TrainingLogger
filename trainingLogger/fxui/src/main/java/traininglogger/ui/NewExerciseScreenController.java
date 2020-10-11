package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.json.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewExerciseScreenController {

    private List<Integer> sets = new ArrayList<>();

    @FXML
    VBox addSetVbox;

    @FXML
    HBox addSetHbox;

    @FXML
    TextField weightTextField, repsTextField, titleTextField;

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        App.setRoot("NewSessionScreen");
    }

    @FXML
    private void addExerciseButtonHandler() throws IOException {
        Exercise exercise = new Exercise(titleTextField.getText());
        for(int i = 0; i < sets.size(); i = i + 2){
            exercise.addSets(sets.get(i), sets.get(i+1));
        }
        System.out.println(exercise);
        //HER MÅ DET GJØRES NOE MED exercise

        // skriver til fil
        FileHandler.writeExerciseToFile("src/main/resources/exercise_controller_data.json", exercise);
        switchToNewExerciseScreen();
    }

    @FXML
    private void addSetButtonHandler(){
        int weight = Integer.parseInt(weightTextField.getText());
        int reps = Integer.parseInt(repsTextField.getText());
        sets.add(weight);
        sets.add(reps);
        addHboxToVbox();
        weightTextField.setText("");
        repsTextField.setText("");
    }



    // Legger til en ny hbox i addSetVbox med verdiene fra den nederste Hboxen.
    private void addHboxToVbox(){
        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("HboxTemplate.fxml").openStream());
            addSetVbox.getChildren().add(addSetVbox.getChildren().size() - 1,node);
            HboxTemplateController controller = (HboxTemplateController)loader.getController();
            controller.setTextField(weightTextField.getText(), repsTextField.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Lager metoden for å kunne teste. Burde egentlig ikke finnes spør du meg, blir bare rotete.
    public int getSetElementByIndex(int i) {
      return sets.get(i);
    }
}
