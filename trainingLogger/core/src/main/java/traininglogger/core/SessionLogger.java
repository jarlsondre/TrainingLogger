package traininglogger.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * SessionLogger objektet inneholder en liste med sessions og et Map med rekorder.
 */
public class SessionLogger implements Iterable<Session> {

  private final List<Session> sessions;
  private final Map<String, Double> records;

  public SessionLogger(List<Session> sessions, Map<String, Double> records) {
    this.sessions = sessions;
    this.records = records;
  }

  public SessionLogger() {
    sessions = new ArrayList<>();
    records = new HashMap<>();
  }

  public void updateRecordsWithSession(Session session) {
    for (Exercise exercise : session) {
      String name = exercise.getName();
      double maxWeight = 0;
      for (Set set : exercise) {
        maxWeight = Math.max(maxWeight, set.getWeight());
      }
      if (records.containsKey(name) && (maxWeight > records.get(name))) {
        records.replace(name, maxWeight);
      } else {
        records.put(name, maxWeight);
      }
    }
  }

  public void deleteRecords() {
    records.clear();
  }

  public Map<String, Double> getRecords() {
    return new HashMap<>(this.records);
  }

  @Override
  public Iterator<Session> iterator() {
    return sessions.iterator();
  }

  public void addSession(Session session) {
    updateRecordsWithSession(session);
    sessions.add(session);
  }

  public void deleteAll() {
    this.sessions.clear();
    this.deleteRecords();
  }

  public String toString() {
    return this.sessions.toString();
  }
}