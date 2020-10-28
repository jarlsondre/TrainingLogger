package traininglogger.ui;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class DirectTrainingLoggerAccess implements TrainingLoggerAccess {

  private SessionLogger sessionLogger;

  public DirectTrainingLoggerAccess(SessionLogger sessionLogger) {
    this.sessionLogger = sessionLogger;
  }

  @Override
  public void addSession(Session session) {
    this.sessionLogger.addSession(session);
  }

  @Override
  public void deleteAll() {
    this.sessionLogger.deleteAll();
  }
}