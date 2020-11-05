package traininglogger.ui;

import javafx.fxml.FXML;

public class StartScreenController {

  private TrainingLoggerController mainController;

  public void setMainController(TrainingLoggerController main) {
    this.mainController = main;
  }

  @FXML
  private void switchToSessionScreen() {
    mainController.changeToSessionScreen();
  }

  @FXML
  private void switchToNewSessionScreen() {
    mainController.changeToNewSessionScreen();
  }
}
