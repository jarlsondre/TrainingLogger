package trainingLogger.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class AppController {
    @FXML
    Button addDescriptionButton;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    VBox sessionOverviewVBox;


    @FXML
    public void handleAddDescriptionButton() {
        String description = descriptionTextArea.getText();  // Tar inn teksten fra TextArea
        descriptionTextArea.setText("");    // Fjerne teksten i TextArea
        if (!description.isEmpty()){        // Sjekker om strengen er tom, slik at vi ikke må behnadle en tom streng
            System.out.println(description);
        }
    }
}
