package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import traininglogger.core.Set;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingLoggerTest extends ApplicationTest {

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

    /**
     * Før hver test så bytter vi skjerm til NewExerciseScreen
     */
    @BeforeEach
    public void setUp() {
        clickOn("#newSessionButton");
        clickOn("#newExerciseButton");
    }


    @Test
    public void testController_initial() {
        assertNotNull(this.appController);
    }


    /**
     * Tester om det kommer flere bokser å skrive inn info på dersom man trykker på 'legg til',
     * i tillegg til om koden kræsjer
     */
    @Test
    public void addSetTest(){
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

        // Sjekker om øvelsen som har blitt lagt til kommer frem og stemmer med det som ble skrevet inn
        VBox exerciseOverview = lookup("#exerciseOverviewVbox").query();
        TitledPane lastExerciseTitledPane = (TitledPane) exerciseOverview.getChildren().get(0);
        clickOn(lastExerciseTitledPane);
        VBox lastExerciseVbox  = ((VBox) lastExerciseTitledPane.getContent());
        Label lastExerciseLabel = (Label) lastExerciseVbox.getChildren().get(0);
        String lastExerciseString = lastExerciseLabel.getText();
        assertEquals("Benkpress:" + "\n" + "122.5 kg x 10" + "\n"+ "122.5 kg x 10" + "\n"+ "122.5 kg x 10" + "\n" + "\n", lastExerciseString);
    }


    /**
     * Tester om koden kræsjer dersom man forsøker å legge til en økt og om informasjonen som
     * blir lagret er riktig
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
        Button addSessionButton = lookup("Legg til økten").query();
        clickOn(addSessionButton);

        // Henter ut dataene som har blitt lagret inne i "tidligere økter"
        clickOn("Tidligere økter");
        VBox vBox = lookup("#sessionOverviewVbox").query();
        TitledPane lastSessionTitledPane = (TitledPane) vBox.getChildren().get(0);
        clickOn(lastSessionTitledPane);
        VBox lastSessionVbox  = ((VBox) lastSessionTitledPane.getContent());
        Label lastSessionLabel = (Label) lastSessionVbox.getChildren().get(0);
        String lastSessionString = lastSessionLabel.getText();

        // Sjekker om dataene er like de vi skrev inn
        assertEquals("Knebøy:" + "\n" + "122.5kg x 10" + "\n" + "140.0kg x 10" + "\n" + "140.0kg x 10" + "\n" + "\n" +
                "Markløft:" + "\n" + "160.0kg x 10" + "\n" + "170.0kg x 10\n" + "\n", lastSessionString);
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
        }
        catch(Exception e) {
            fail("Koden kræsjet da testen prøvde å skrive inn feil input. Dette skal i stedet håndteres ved en feilmelding på UI-et");
        }
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
        Path path = Paths.get(System.getProperty("user.home"), "sessionLoggerTest.json");
        File f= new File(path.toString());
        f.delete();
}


}
