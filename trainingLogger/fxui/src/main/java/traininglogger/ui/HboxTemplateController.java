package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class HboxTemplateController {

    @FXML
    TextField weightTextField, repsTextField;

    public void setTextField(String weight, String reps){
        weightTextField.setText(weight);
        repsTextField.setText(reps);
    }
}
