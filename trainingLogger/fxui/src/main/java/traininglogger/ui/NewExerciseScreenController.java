package traininglogger.ui;

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
import traininglogger.json.FileHandler;

import java.io.IOException;

public class NewExerciseScreenController {

    private Exercise exercise;

    @FXML
    VBox addSetVbox;

    @FXML
    HBox addSetHbox;

    @FXML
    TextField weightTextField, repsTextField, titleTextField;

    @FXML
    Button addExerciseButton;

    TrainingLoggerController mainController;

    NewSessionScreenController newSessionScreenController;


    public void setMainController(TrainingLoggerController main){
        this.mainController = main;
    }

    public void setNewSessionScreenController(NewSessionScreenController newSessionScreenController){
        this.newSessionScreenController = newSessionScreenController;
    }

    @FXML
    public void initialize(){
        exercise = new Exercise();
    }

    @FXML
    private void switchToNewSessionScreen() throws IOException {
        try {
            mainController.changeToNewSessionScreen();
            resetInputFields();
        }
        catch(Exception e) {
            System.out.println("Kunne ikke bytte fra New Exercise Screen til New Session Screen");
        }

    }

    @FXML
    private void addExerciseButtonHandler() throws IOException {
        exercise.setName(titleTextField.getText());
        newSessionScreenController.addExerciseToSession(exercise);
        exercise = new Exercise();
        mainController.changeToNewSessionScreen();
        resetInputFields();


    }

    @FXML
    private void addSetButtonHandler(){
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
        }
        catch (Exception e) {
            Label errorLabel = new Label("Input må være et heltall");
            addSetVbox.getChildren().add(0, errorLabel);
            System.out.println("Input må være heltall");
        }

    }

    private void resetInputFields(){
        Node temp = addSetVbox.getChildren().get(addSetVbox.getChildren().size()-1);
        addSetVbox.getChildren().clear();
        addSetVbox.getChildren().add(temp);
        titleTextField.setText("");
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

    /**
     * Metode for å hente ut hvilken exercise som er lagret i controlleren. Denne kan blant annet brukes for å
     * teste om exercise-objektet blir instansiert riktig
     *
     * @return Exercise-objektet til controlleren
     */

    public Exercise getExercise() {
        return this.exercise;
    }

}
