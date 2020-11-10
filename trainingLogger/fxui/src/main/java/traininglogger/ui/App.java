package traininglogger.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage stage) throws IOException {
    Scene scene = new Scene(new FXMLLoader(App.class.getResource("App.fxml")).load());
    stage.setScene(scene);
    stage.show();
  }

}
