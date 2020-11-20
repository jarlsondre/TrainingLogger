package traininglogger.ui;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public interface TrainingLoggerAccess {

  void addSession(Session session);

  void deleteAll();

  SessionLogger getSessionLogger();

}