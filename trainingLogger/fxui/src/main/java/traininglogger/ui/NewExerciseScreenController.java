package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewExerciseScreenController {
    @FXML
    VBox addSetVbox;

    @FXML
    HBox addSetHbox;

    @FXML
    TextField weightTextField, repsTextField;

    @FXML
    private void switchToNewExerciseScreen() throws IOException {
        App.setRoot("NewSessionScreen");
    }

    @FXML
    private void addExerciseButtonHandler() throws IOException {
        switchToNewExerciseScreen();
    }

    @FXML
    private void addSetButtonHandler(){
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
            //get the controller
            HboxTemplateController controller = (HboxTemplateController)loader.getController();
            controller.setTextField(weightTextField.getText(), repsTextField.getText()); //set label
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
