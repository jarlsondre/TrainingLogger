package traininglogger.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.Set;

public class UpdateOverview {

  public static VBox sessionToVboxConverter(Session session) {
    VBox box = new VBox();
    for (Exercise exercise : session) {
      Label label = new Label(exercise.getName() + ": ");
      String temp = "";
      for (Set set : exercise) {
        temp += Double.toString(set.getWeight()) + "KG x " + Integer.toString(set.getRepetitions()) + ", ";
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
