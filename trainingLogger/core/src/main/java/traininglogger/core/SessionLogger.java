package traininglogger.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** 
 * SessionLogger objektet inneholder en liste med sessions.
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
    this.sessions.clear();
  }

  public String toString(){
    return this.sessions.toString();
  }
}