package traininglogger.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewSessionScreenControllerTest extends ApplicationTest {

    NewSessionScreenController newSessionScreenController;

    @Override
    public void start(Stage stage) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewSessionScreen_test.fxml"));
        Parent root = loader.load();
        this.newSessionScreenController = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
      }
      catch(Exception e) {
        System.out.println("Feilet under start");
        e.printStackTrace(); 
        System.out.println(e);
      }
    }

    //@Test
    // public void testAddSession() {

        // Vi lager dataen som skal testes
        // Exercise e1 = new Exercise("Knebøy", 5,5,5,5,4,4);
        // Exercise e2 = new Exercise("Benkpress", 5,5,5,5,4,3);
        // Exercise e3 = new Exercise("Markløft", 5,5,5,5,4,2);
        //Session session = new Session("Bra!", e1,e2,e3);

        // Vi skriver dataen til fil
        // FileHandler.writeSessionToFile("src/main/resources/session_controller_data.json", session);
        // Button addSessionButton = lookup("#addSessionButton").query();
        // clickOn(addSessionButton);

        // Vi sjekker at dataen vi laget ble skrevet til fil
        // SessionLogger logger = new SessionLogger();
        // logger.load();
        // session.setDate(logger.getLastSession().getDate());
        //assertEquals(logger.getLastSession(), session);
    // }

    
}
