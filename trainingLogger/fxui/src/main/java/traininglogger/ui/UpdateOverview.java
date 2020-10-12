package traininglogger.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;

import java.lang.reflect.Array;

public class UpdateOverview {

    public static VBox sessionToVboxConverter(Session session) {
        VBox box = new VBox();
        for (Exercise exercise : session.getListOfExercises()) {
            Label label = new Label(exercise.getName() + ": ");
            String temp = "";
            for (Integer[] set : exercise.getSets()) {
                temp += Integer.toString((Integer) Array.get(set, 0)) +"KG x "+ Integer.toString((Integer) Array.get(set, 1)) + ", ";
                label.setText(label.getText() + temp);
                temp = "";
            }
            String finalText = label.getText();
            finalText = finalText.substring(0, finalText.length() - 2);
            label.setText(finalText);
            box.getChildren().add(label);
        }
        return box;
    }
}
