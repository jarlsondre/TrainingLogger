package traininglogger.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglogger.core.ComparisonHelper;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.core.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ModuleTest {

  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
  }

  @Test
  public void testSetSerializer() {
    Set set = new Set(5, 100);
    try {
      String howStringShouldLookLike = "{\"repetitions\":5,\"weight\":100.0}";
      String stringProducedBySerializer = mapper.writeValueAsString(set);
      assertEquals(howStringShouldLookLike, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testSetDeserializer() {
    String setAsJsonString = "{\"repetitions\":5,\"weight\":100.0}";
    try {
      Set setFromJsonString = mapper.readValue(setAsJsonString, Set.class);
      Set set = new Set(5, 100);
      assertTrue(ComparisonHelper.equalSet(set, setFromJsonString));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testExerciseSerializer() {
    Exercise exercise = new Exercise("Benk");
    Set set1 = new Set(5, 100);
    Set set2 = new Set(10, 200);
    exercise.addSets(set1, set2);
    try {
      String howStringShouldLookLike = "{\"name\":\"Benk\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},{\"repetitions\":10,\"weight\":200.0}]}";
      String stringProducedBySerializer = mapper.writeValueAsString(exercise);
      assertEquals(howStringShouldLookLike, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testExerciseDeserializer() {
    String exerciseAsJsonString = "{\"name\":\"Benk\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},{\"repetitions\":10,\"weight\":200.0}]}";
    try {
      Exercise exerciseFromJsonString = mapper.readValue(exerciseAsJsonString, Exercise.class);
      Exercise exercise = new Exercise("Benk");
      Set set1 = new Set(5, 100);
      Set set2 = new Set(10, 200);
      exercise.addSets(set1, set2);
      assertTrue(ComparisonHelper.equalExercise(exercise, exerciseFromJsonString));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  private final static String sessionLoggerWithOneSession = "{\"sessions\":[{\"stringDescription\":\"Uten trening gir livet ingen mening!\",\"date\":\"12/12/1989 15:15\",\"exercises\":[{\"name\":\"Testpress\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},{\"repetitions\":5,\"weight\":97.5},{\"repetitions\":5,\"weight\":87.5}]},{\"name\":\"Testbøy\",\"sets\":[{\"repetitions\":8,\"weight\":130.0},{\"repetitions\":8,\"weight\":127.5},{\"repetitions\":7,\"weight\":125.0}]}]}],\"records\":{\"Testpress\":100.0,\"Testbøy\":130.0}}";

  @Test
  public void testSerializers(){
    Session session = new Session();

    Set set0 = new Set(5, 100);
    Set set1 = new Set(5, 97.5);
    Set set2 = new Set(5, 87.5);
    Set[] sets1 = new Set[3];
    sets1[0] = set0;
    sets1[1] = set1;
    sets1[2] = set2;
    Exercise exercise1 = new Exercise("Testpress", sets1);
    session.addExercises(exercise1);

    Set set3 = new Set(8, 130);
    Set set4 = new Set(8, 127.5);
    Set set5 = new Set(7, 125);
    Set[] sets2 = new Set[3];
    sets2[0] = set3;
    sets2[1] = set4;
    sets2[2] = set5;
    Exercise exercise2 = new Exercise("Testbøy", sets2);
    session.addExercises(exercise2);

    session.setDate("12/12/1989 15:15");
    session.setDescription("Uten trening gir livet ingen mening!");

    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session);
    try {
      String stringProducedBySerializer = mapper.writeValueAsString(sessionLogger);
      assertEquals(sessionLoggerWithOneSession, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }



  }

}