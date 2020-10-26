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

  /**
   *Kaller statisk metode i FileHandler
   *Resultatet er at sessions fylles opp med Session-objekter 
  public boolean load() {
    try {
      InputStream inputstream = new FileInputStream(fileLocationString);
      this.sessions = FileHandler.readFromFile(inputstream);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  Kaller statisk metode i FileHandler
  Resultatet er at Session-objektene i sessions lagres på disk i JSON-format

  public boolean save() {
    try {
      File file = new File(fileLocationString);
      OutputStream fop = new FileOutputStream(file);
      FileHandler.writeToFile(fop, this.sessions);
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }*/

  public void deleteAll() {
    this.sessions = new ArrayList<>();
  }

  public void deleteLast() {
    this.sessions.remove(this.sessions.size()-1);
  }

  public Session getLastSession() {
    return this.sessions.get(this.sessions.size()-1);
  }

}