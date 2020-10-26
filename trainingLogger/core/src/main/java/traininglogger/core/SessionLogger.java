package traininglogger.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** 
 * SessionLogger objektet inneholder en liste med sessions. objektet er ment å
 * kunne lagre, manipulere og endre på lista med sessions.
 */
public class SessionLogger implements Iterable<Session> {

  private List<Session> sessions = new ArrayList<>();

  @Override
  public Iterator<Session> iterator() {
    return sessions.iterator();
  }

  public void addSession(Session session) {
    sessions.add(session);
  }

  public void deleteAll() {
    this.sessions = new ArrayList<>();
  }

}