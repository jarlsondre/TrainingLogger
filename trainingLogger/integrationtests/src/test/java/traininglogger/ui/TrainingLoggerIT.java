package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class TrainingLoggerIT extends ApplicationTest {

  private TrainingLoggerController controller;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingLogger_it.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupItems() throws URISyntaxException {
      String port = System.getProperty("traininglogger.port");
      assertNotNull(port, "No traininglogger.port system property set");
      URI baseUri = new URI("http://localhost:" + port + "/traininglogger/");
      System.out.println("Base RemoteTrainingLoggerAcces URI: " + baseUri);
      this.controller.setTrainingLoggerAccess(new RemoteTrainingLoggerAccess(baseUri));
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
  }
}