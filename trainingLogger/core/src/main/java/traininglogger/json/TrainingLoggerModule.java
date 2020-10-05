package traininglogger.json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import traininglogger.core.Exercise;
import traininglogger.core.Session;

public class TrainingLoggerModule extends SimpleModule {

  private static final String NAME = "trainingLoggerModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {
  };

  public TrainingLoggerModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(Session.class, new SessionSerializer());
    addDeserializer(Session.class, new SessionDeserializer());
    addSerializer(Exercise.class, new ExerciseSerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());
  }

}