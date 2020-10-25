package traininglogger.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrainingLoggerController {
    @FXML
    VBox mainVbox;

    @FXML
    public void initialize(){FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("StartScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            StartScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            System.out.println("HEIIIIIIIII");
        }
    }

    public void changeToNewSessionScreen(){
        mainVbox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            NewSessionScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToSessionScreen(){
        mainVbox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("SessionScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            SessionScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToStartScreen(){
        mainVbox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("StartScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            StartScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToNewExerciseScreen(){
        mainVbox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            Node node  =  loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
            mainVbox.getChildren().add(node);
            NewExerciseScreenController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
