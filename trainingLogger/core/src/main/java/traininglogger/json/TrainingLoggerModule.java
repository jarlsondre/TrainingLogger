package traininglogger.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  public static void main(String[] args) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());

    // Session1:
    Set set0 = new Set(5, 100);
    Set set1 = new Set(5, 97.5);
    Set set2 = new Set(5, 87.5);

    Set[] sets1 = new Set[3];
    sets1[0] = set0;
    sets1[1] = set1;
    sets1[2] = set2;

    Exercise exercise1 = new Exercise("Serverbasert Benkpress", sets1);

    Session session1 = new Session();
    session1.addExercises(exercise1);

    Set set3 = new Set(8, 130);
    Set set4 = new Set(8, 127.5);
    Set set5 = new Set(7, 125);

    Set[] sets2 = new Set[3];
    sets2[0] = set3;
    sets2[1] = set4;
    sets2[2] = set5;

    Exercise exercise2 = new Exercise("Serverbasert Knebøy", sets2);
    session1.addExercises(exercise2);

    // Session2:

    Set set6 = new Set(8, 120);
    Set set7 = new Set(10, 97.5);
    Set set8 = new Set(15, 87.5);

    Set[] sets3 = new Set[3];
    sets3[0] = set6;
    sets3[1] = set7;
    sets3[2] = set8;

    Exercise exercise3 = new Exercise("Serverbasert Markløft", sets3);

    Session session2 = new Session();
    session2.addExercises(exercise3);

    Set set9 = new Set(4, 150);
    Set set10 = new Set(10, 127.5);
    Set set11 = new Set(12, 125);

    Set[] sets4 = new Set[3];
    sets4[0] = set9;
    sets4[1] = set10;
    sets4[2] = set11;

    Exercise exercise4 = new Exercise("Serverbasert Bulgarsk utfall", sets4);
    session2.addExercises(exercise4);

    // SessionLog:

    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session1);
    sessionLogger.addSession(session2);

    try {
      String sessionLoggerAsJsonString = mapper.writeValueAsString(sessionLogger); // fra objekt til json-streng
      System.out.println(sessionLoggerAsJsonString);
    } catch (JsonProcessingException e) {
      System.err.println("Funka ikke.");
    } 
  }

}