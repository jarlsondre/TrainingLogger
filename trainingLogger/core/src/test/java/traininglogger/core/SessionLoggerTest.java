package traininglogger.core;

import java.util.Iterator;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionLoggerTest {

  @Test
  public void addDelateSessionTest() {
    Session session = new Session();
    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session);
    assertTrue(sessionLogger.iterator().hasNext());
    sessionLogger.deleteAll();
    assertFalse(sessionLogger.iterator().hasNext());
  }

  @Test
  public void sessionIteratorTest() {
    Session session1 = new Session();
    Session session2 = new Session();
    Session session3 = new Session();
    Session session4 = new Session();
    Session[] sessions = {session1, session2, session3, session4};
    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session1);
    sessionLogger.addSession(session2);
    sessionLogger.addSession(session3);
    sessionLogger.addSession(session4);
    Iterator<Session> iterator = sessionLogger.iterator();
    for (int i = 0; i < 4; i++) {
      assertTrue(iterator.hasNext());
      assertTrue(ComparisonHelper.equalSession(sessions[i], iterator.next()));
    }
    assertFalse(iterator.hasNext());
  }

  @Test
  public void recordTest(){
    String ex1 = "Benkpress";
    String ex2 = "Knebøy";
    SessionLogger sessionLogger = new SessionLogger();
    Exercise exercise1 = new Exercise(ex1, new Set(2, 2), new Set(4, 10));
    Exercise exercise2 = new Exercise(ex2, new Set(2, 2), new Set(4, 20));
    Session session1 = new Session();
    session1.addExercises(exercise1, exercise2);
    sessionLogger.addSession(session1);
    Map<String, Double> records = sessionLogger.getRecords();
    assertEquals(records.get(ex1), 10);
    assertEquals(records.get(ex2), 20);

    Exercise exercise3 = new Exercise(ex1, new Set(2, 2), new Set(4, 10.1));
    Exercise exercise4 = new Exercise(ex2, new Set(2, 2), new Set(4, 20.1));
    Session session2 = new Session();
    session2.addExercises(exercise3, exercise4);
    sessionLogger.addSession(session2);
    records = sessionLogger.getRecords();
    assertEquals(records.get(ex1), 10.1);
    assertEquals(records.get(ex2), 20.1);

    Exercise exercise5 = new Exercise(ex1, new Set(10, 10), new Set(4, 7.1));
    Exercise exercise6 = new Exercise(ex2, new Set(10, 10), new Set(4, 19.1));
    Session session3 = new Session();
    session1.addExercises(exercise5, exercise6);
    sessionLogger.addSession(session3);
    records = sessionLogger.getRecords();
    assertEquals(records.get(ex1), 10.1);
    assertEquals(records.get(ex2), 20.1);




  }

}