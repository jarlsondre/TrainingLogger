package traininglogger.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerPersistence;

public class DirectTrainingLoggerAccess implements TrainingLoggerAccess {

  private SessionLogger sessionLogger;
  private String userSessionLoggerPath;

  public DirectTrainingLoggerAccess(SessionLogger sessionLogger) {
    this.sessionLogger = sessionLogger;
  }

  public void setUserSessionLoggerPath(String userSessionLoggerPath) {
    this.userSessionLoggerPath = userSessionLoggerPath;
  }

  @Override
  public void addSession(Session session) {
    this.sessionLogger.addSession(session);
    saveSessionLogger();
  }

  @Override
  public void deleteAll() {
    this.sessionLogger.deleteAll();
  }

  @Override
  public SessionLogger getSessionLogger() {
    return this.sessionLogger;
  }

  private TrainingLoggerPersistence trainingLoggerPersistence = null;

  private void saveSessionLogger() {
    if (userSessionLoggerPath != null) {
      if (trainingLoggerPersistence == null) {
        trainingLoggerPersistence = new TrainingLoggerPersistence();
      }
      Path path = Paths.get(System.getProperty("user.home"), userSessionLoggerPath);
      try (Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)) {
        trainingLoggerPersistence.writeSessionLogger(this.sessionLogger, writer);
      } catch (IOException e) {
        System.err.println("Fikk ikke skrevet til " + userSessionLoggerPath + " på hjemmeområdet");
      }
    }
  }
}