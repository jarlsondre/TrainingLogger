package trainingLogger.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
            //Her kan du gjøre ting med strengen
            addStringWithTitleToSessionOverview("Økt numemr x", description); //Bare for å ha en funksjon her midlertidig.
        }
    }


    public void addStringToSessionOverview(String string){
         sessionOverviewVBox.getChildren().add(new Text(string));
    }


    //Legger til en titlePane med en vBox som kan fylles med innhold. Her blir descrption lagt til som en label i vboxen
    public void addStringWithTitleToSessionOverview(String title, String description){
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label(description));
        TitledPane titledPane = new TitledPane("Title" ,vBox);
        titledPane.setAlignment(Pos.CENTER_LEFT);
        titledPane.setExpanded(false);

        sessionOverviewVBox.getChildren().add(titledPane);


    }
}
