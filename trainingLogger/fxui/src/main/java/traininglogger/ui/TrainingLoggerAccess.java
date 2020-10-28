package traininglogger.ui;

import traininglogger.core.Session;

public interface TrainingLoggerAccess {

  public void addSession(Session session);

  public void deleteAll();

}