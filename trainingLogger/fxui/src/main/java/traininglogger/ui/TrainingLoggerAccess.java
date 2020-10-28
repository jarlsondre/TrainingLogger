package traininglogger.ui;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public interface TrainingLoggerAccess {

  public void addSession(Session session);

  public void deleteAll();

  public SessionLogger getSessionLogger();

}