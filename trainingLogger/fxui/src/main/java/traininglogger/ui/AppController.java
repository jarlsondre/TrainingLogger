package traininglogger.ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.core.Set;
import traininglogger.json.TrainingLoggerPersistence;


public class AppController {

  @FXML
  String userSessionLoggerPath;

  @FXML
  String sampleSessionLoggerResource;

  @FXML
  String endpointUri;

  @FXML
  TrainingLoggerController trainingLoggerController;

  /*
   * Sørger for at dennes indre trainingLoggerController får registrert riktigt
   * aksessor (TrainingLoggerAccess), dvs. avgjør om appen skal kjøres lokalt
   * eller over server.
   */
  @FXML
  void initialize() {
    TrainingLoggerAccess trainingLoggerAccess = null;
    if (this.endpointUri != null) {
      RemoteTrainingLoggerAccess remoteAccess;
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteTrainingLoggerAccess(new URI(endpointUri));
        trainingLoggerAccess = remoteAccess;
      } catch (URISyntaxException e) {
        System.err.println(e);
      }
    }
    if (trainingLoggerAccess == null) {
      DirectTrainingLoggerAccess directAccess =
          new DirectTrainingLoggerAccess(getInitialSessionLogger());
      directAccess.setUserSessionLoggerPath(userSessionLoggerPath);
      trainingLoggerAccess = directAccess;
    }
    trainingLoggerController.setTrainingLoggerAccess(trainingLoggerAccess);
  }

  // Hvis aksessoren skal være direkte (appen skal kjøres lokalt),
  // finn i riktig oppstarts-logg:
  private SessionLogger getInitialSessionLogger() {
    // setter opp data
    Reader reader = null;
    // prøv å lese fil fra hjemmeområdet først:
    if (userSessionLoggerPath != null) {
      try {
        reader = new FileReader(Paths.get(System.getProperty("user.home"),
            userSessionLoggerPath).toFile(),
            StandardCharsets.UTF_8);
      } catch (IOException ioex) {
        System.err.println("Fant ingen " + userSessionLoggerPath + " på hjemmeområdet");
      }
    }

    if (reader == null && sampleSessionLoggerResource != null) {
      // prøv vedlagt eksempelfil i stedet:
      URL url = getClass().getResource(sampleSessionLoggerResource);
      if (url != null) {
        try {
          reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
          System.err.println("Kunne ikke lese innebygget " + sampleSessionLoggerResource);
        }
      } else {
        System.err.println("Fant ikke innebygget " + sampleSessionLoggerResource);
      }
    }

    if (reader == null) {
      // TODO: use embedded String (se Hallvard sin TodoAppController!)
    }

    SessionLogger sessionLogger = null;
    try {
      TrainingLoggerPersistence trainingLoggerPersistence = new TrainingLoggerPersistence();
      sessionLogger = trainingLoggerPersistence.readSessionLogger(reader);
    } catch (IOException e) {
      // ignore
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        // ignore
      }
    }
    if (sessionLogger == null) {
      sessionLogger = new SessionLogger();
      Session session = new Session();
      Set set = new Set(5, 100);
      Exercise exercise = new Exercise("Benk", set);
      session.addExercises(exercise);
      sessionLogger.addSession(session);
    }
    return sessionLogger;
  }

}