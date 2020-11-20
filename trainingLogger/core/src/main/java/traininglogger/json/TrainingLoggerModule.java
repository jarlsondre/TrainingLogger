package traininglogger.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.core.Set;

@SuppressWarnings("serial")
public class TrainingLoggerModule extends SimpleModule {

  private static final String NAME = "trainingLoggerModule";

  /**
   * Konstruerer en TrainingLoggerModule som kobler Set, Exercise, Session og
   * SessionLogger til sin respektive (de)serialiserere.
   */
  public TrainingLoggerModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(Session.class, new SessionSerializer());
    addDeserializer(Session.class, new SessionDeserializer());
    addSerializer(Exercise.class, new ExerciseSerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());
    addSerializer(Set.class, new SetSerializer());
    addDeserializer(Set.class, new SetDeserializer());
    addSerializer(SessionLogger.class, new SessionLoggerSerializer());
    addDeserializer(SessionLogger.class, new SessionLoggerDeserializer());
  }
}