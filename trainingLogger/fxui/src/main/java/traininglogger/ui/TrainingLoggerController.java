package traininglogger.ui;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
  private NewExerciseScreenController newExerciseScreenController;
  private NewSessionScreenController newSessionScreenController;
  private SessionLogger sessionLogger;

  @FXML
  public void initialize() throws JsonParseException, JsonMappingException, IOException {
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
      newExerciseScreenController = loader.getController();
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

  private ObjectMapper mapper = new ObjectMapper();
  private String userSessionLoggerPath = "sessionlogger.json";

  public void loadSessionLogger() throws JsonParseException, JsonMappingException, IOException {
    this.mapper.registerModule(new TrainingLoggerModule());
    Reader reader = null;
    // Prøv å lese lagret fil fra brukerens hjemmeområde:
    try {
      reader = new FileReader(Paths.get(System.getProperty("user.home"), userSessionLoggerPath).toFile(),
          StandardCharsets.UTF_8);
    } catch (IOException ioex) {
      System.err.println("Fant ingen " + userSessionLoggerPath + " på hjemmeområdet.");
    }
    if (reader == null) {
      this.sessionLogger = new SessionLogger();
    } else {
      this.sessionLogger = mapper.readValue(reader, SessionLogger.class);
      reader.close();
    }
  }

  public void saveSessionLogger() {
    if (userSessionLoggerPath != null) {
      Path path = Paths.get(System.getProperty("user.home"), userSessionLoggerPath);
      try {
        Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
        this.mapper.writerWithDefaultPrettyPrinter().writeValue(writer, this.sessionLogger);
      } catch (IOException e) {
        System.err.println("Fikk ikke skrevet til sessionlog.json på hjemmeområdet.");
      }
    }
  }
  
}
