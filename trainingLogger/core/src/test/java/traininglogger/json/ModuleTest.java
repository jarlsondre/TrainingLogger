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

import java.time.LocalDateTime;
import java.util.Iterator;

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

  // Bruker konkatinering fordi gitpod-IDEen slår seg vrang hvis strengen står på én linje:
  private final static String sessionLoggerWithOneSession = "{\"sessions\":[{\"stringDescription\":\"Uten trening gir livet ingen mening!\",\"date\":\"12/12/1989 15:15\",\"exercises\":[{\"name\":\"Testpress\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},"
      + "{\"repetitions\":5,\"weight\":97.5},{\"repetitions\":5,\"weight\":87.5}]},{\"name\":\"Testbøy\",\"sets\":[{\"repetitions\":8,\"weight\":130.0},{\"repetitions\":8,\"weight\":127.5},{\"repetitions\":7,\"weight\":125.0}]}]}],\"records\":{\"Testpress\":100.0,\"Testbøy\":130.0}}";

  @Test
  public void testSerializers() {
    SessionLogger sessionLogger = createSessionLoggerWithOneSession();
    try {
      String stringProducedBySerializer = mapper.writeValueAsString(sessionLogger);
      assertEquals(sessionLoggerWithOneSession, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testDeserializers() {
     try {
       SessionLogger sessionLogger = mapper.readValue(sessionLoggerWithOneSession, SessionLogger.class);
       assertEquals(sessionLogger.getRecords().get("Testpress"), 100);
       assertEquals(sessionLogger.getRecords().get("Testbøy"), 130);

       assertTrue(sessionLogger.iterator().hasNext());
       Session session = sessionLogger.iterator().next();
       assertEquals(session.getDate(), LocalDateTime.of(1989, 12, 12, 15, 15));
       assertEquals(session.getDescription(), "Uten trening gir livet ingen mening!");

       assertTrue(session.iterator().hasNext());
       Iterator<Exercise> sessionIterator = session.iterator();

       Exercise exercise1 = sessionIterator.next();
       assertEquals(exercise1.getName(), "Testpress");

       assertTrue(exercise1.iterator().hasNext());
       Iterator<Set> exerciseIterator = exercise1.iterator();

       Set set0 = exerciseIterator.next();
       assertEquals(5, set0.getRepetitions());
       assertEquals(100, set0.getWeight());
       assertTrue(exerciseIterator.hasNext());

       Set set1 = exerciseIterator.next();
       assertEquals(5, set1.getRepetitions());
       assertEquals(97.5, set1.getWeight());
       assertTrue(exerciseIterator.hasNext());

       Set set2 = exerciseIterator.next();
       assertEquals(5, set2.getRepetitions());
       assertEquals(87.5, set2.getWeight());
       assertFalse(exerciseIterator.hasNext());

       assertTrue(sessionIterator.hasNext());
       Exercise exercise2 = sessionIterator.next();
       assertEquals("Testbøy", exercise2.getName());
       assertFalse(sessionIterator.hasNext());
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testSerializersAndDeserializers() {
    SessionLogger originalSessionLogger = createSessionLoggerWithOneSession();
    try {
      String sessionLoggerAsString = mapper.writeValueAsString(originalSessionLogger);
      SessionLogger recreatedSessionLogger = mapper.readValue(sessionLoggerAsString, SessionLogger.class);
      assertEquals(originalSessionLogger.getRecords().get("Testpress"), recreatedSessionLogger.getRecords().get("Testpress"));
      assertEquals(originalSessionLogger.getRecords().get("Testbøy"), recreatedSessionLogger.getRecords().get("Testbøy"));
      assertTrue(originalSessionLogger.iterator().hasNext());
      Iterator<Session> originalIterator = originalSessionLogger.iterator();
      Session originalSession = originalIterator.next();
      assertTrue(recreatedSessionLogger.iterator().hasNext());
      Iterator<Session> recreatedIterator = recreatedSessionLogger.iterator();
      Session recreatedSession = recreatedIterator.next();
      assertEquals(originalSession.getDescription(), recreatedSession.getDescription());
      assertEquals(originalSession.getDate(), recreatedSession.getDate());
      assertFalse(originalIterator.hasNext());
      assertFalse(recreatedIterator.hasNext());
      assertTrue(originalSession.iterator().hasNext());
      Iterator<Exercise> originalSessionIterator = originalSession.iterator();
      assertTrue(recreatedSession.iterator().hasNext());
      Iterator<Exercise> recreatedSessionIterator = recreatedSession.iterator();
      assertTrue(ComparisonHelper.equalExercise(originalSessionIterator.next(), recreatedSessionIterator.next()));
      assertTrue(ComparisonHelper.equalExercise(originalSessionIterator.next(), recreatedSessionIterator.next()));
      assertFalse(originalSessionIterator.hasNext());
      assertFalse(recreatedSessionIterator.hasNext());
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  private SessionLogger createSessionLoggerWithOneSession() {
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
    return sessionLogger;
  }
}