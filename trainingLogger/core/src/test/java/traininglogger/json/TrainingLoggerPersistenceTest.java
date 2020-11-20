package traininglogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import traininglogger.core.ComparisonHelper;
import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.core.Set;

public class TrainingLoggerPersistenceTest {

  private TrainingLoggerPersistence trainingLoggerPersistence = new TrainingLoggerPersistence();

  @Test
  public void testSerializersAndDeserializers() {
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
    SessionLogger originalSessionLogger = new SessionLogger();
    originalSessionLogger.addSession(session);
    try {
      StringWriter writer = new StringWriter();
      trainingLoggerPersistence.writeSessionLogger(originalSessionLogger, writer);
      String sessionLoggerAsString = writer.toString();
      SessionLogger recreatedSessionLogger = trainingLoggerPersistence.readSessionLogger(new StringReader(sessionLoggerAsString));
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
    } catch (IOException e) {
      fail();
    }
  }
}