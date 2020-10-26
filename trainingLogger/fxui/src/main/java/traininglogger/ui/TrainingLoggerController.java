package traininglogger.ui;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerModule;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrainingLoggerController {
  @FXML
  VBox mainVbox;

  @FXML
  Node sessionScreen;

  @FXML
  Node startScreen;

  @FXML
  Node newSessionScreen;

  @FXML
  Node newExerciseScreen;

  private SessionScreenController sessionScreenController;
  private NewSessionScreenController newSessionScreenController;
  private SessionLogger sessionLogger;

  @FXML
  public void initialize() throws IOException {
    loadSessionLogger();

    FXMLLoader loader = new FXMLLoader();
    try {
      sessionScreen = loader.load(getClass().getResource("SessionScreen.fxml").openStream());
      sessionScreenController = loader.getController();
      sessionScreenController.setMainController(this);
      sessionScreenController.setSessionLogger(this.sessionLogger);
    } catch (Exception e) {
      e.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      startScreen = loader.load(getClass().getResource("StartScreen.fxml").openStream());
      StartScreenController controller = loader.getController();
      controller.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      newSessionScreen = loader.load(getClass().getResource("NewSessionScreen.fxml").openStream());
      newSessionScreenController = loader.getController();
      newSessionScreenController.setMainController(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    loader = new FXMLLoader();
    try {
      newExerciseScreen = loader.load(getClass().getResource("NewExerciseScreen.fxml").openStream());
      NewExerciseScreenController newExerciseScreenController = loader.getController();
      newExerciseScreenController.setMainController(this);
      newExerciseScreenController.setNewSessionScreenController(newSessionScreenController);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    changeToStartScreen();
  }

  public void changeToNewSessionScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(newSessionScreen);
  }

  public void changeToSessionScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(sessionScreen);
  }

  public void changeToStartScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(startScreen);
  }

  public void changeToNewExerciseScreen() {
    mainVbox.getChildren().clear();
    mainVbox.getChildren().add(newExerciseScreen);
  }

  public void addSessionToSessionLogger(Session session) {
    sessionLogger.addSession(session);
    sessionScreenController.sessionOverviewUpdate();
    saveSessionLogger();
  }

  private final ObjectMapper mapper = new ObjectMapper();
  private final static String userSessionLoggerPath = "sessionlogger.json";

  @SuppressFBWarnings //SpotBug sier at reaader ikke lukkes, men den lukkes. Kjent feil.
  public void loadSessionLogger() throws IOException {
    this.mapper.registerModule(new TrainingLoggerModule());
    Reader reader = null;
    // Prøv å lese lagret fil fra brukerens hjemmeområde:
    try {
      reader = new FileReader(Paths.get(System.getProperty("user.home"), userSessionLoggerPath).toFile(),
          StandardCharsets.UTF_8);
    } catch (IOException ioEx) {
      System.err.println("Fant ingen " + userSessionLoggerPath + " på hjemmeområdet.");
    }
    if (reader == null) {
      this.sessionLogger = new SessionLogger();
    }
    else {
      this.sessionLogger = mapper.readValue(reader, SessionLogger.class);
    }
  }

  @SuppressFBWarnings //SpotBug sier at writer ikke lukkes, men den lukkes. Kjent feil.
  public void saveSessionLogger() {
      Path path = Paths.get(System.getProperty("user.home"), userSessionLoggerPath);
      try {
          Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
          this.mapper.writerWithDefaultPrettyPrinter().writeValue(writer, this.sessionLogger);
          writer.close();
        } catch (IOException e) {
          System.err.println("Fikk ikke skrevet til sessionlog.json på hjemmeområdet.");
      }
  }
}
