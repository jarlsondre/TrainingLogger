package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

public class SessionLoggerTest {


  public void addDelateSessionTest(){
    Session session = new Session(); 
    SessionLogger sessionLogger = new SessionLogger(); 
    sessionLogger.addSession(session); 
    assertTrue(sessionLogger.iterator().hasNext());
    sessionLogger.deleteAll();
    assertFalse(sessionLogger.iterator().hasNext());
  }

  public void sessionIteratorTest(){
    Session session1 = new Session();
    Session session2 = new Session();
    Session session3 = new Session();
    Session session4 = new Session();
    Session[] sessions = {session1, session2, session3,session4};
    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session1);
    sessionLogger.addSession(session2);
    sessionLogger.addSession(session3);
    sessionLogger.addSession(session4);
    Iterator<Session> iterator = sessionLogger.iterator(); 
    for(int i = 0; i < 4; i++){
      assertTrue(iterator.hasNext());
      assertEquals(sessions[i], iterator.next());
    }
    assertFalse(iterator.hasNext());

  }

}