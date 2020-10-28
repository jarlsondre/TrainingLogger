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

  public static void main(String[] args) {
    Set set1 = new Set(5, 100);
    Set set2 = new Set(2, 200);
    Exercise ex1 = new Exercise("Benk");
    ex1.addSets(set1, set2);
    Set set3 = new Set(3, 30);
    Set set4 = new Set(4, 40);
    Exercise ex2 = new Exercise("Albuepress");
    ex2.addSets(set3, set4);
    Session session1 = new Session();
    session1.addExercises(ex1, ex2);

    Set set5 = new Set(10, 20);
    Set set6 = new Set(8, 20);
    Exercise ex3 = new Exercise("Bicepscurls");
    ex3.addSets(set5, set6);
    Set set7 = new Set(3, 150);
    Set set8 = new Set(4, 140);
    Exercise ex4 = new Exercise("Markløft");
    ex4.addSets(set7, set8);
    Session session2 = new Session();
    session2.addExercises(ex3, ex4);

    SessionLogger sessionLogger = new SessionLogger();
    sessionLogger.addSession(session1);
    sessionLogger.addSession(session2);

    System.out.println(sessionLogger);
    

  }

}