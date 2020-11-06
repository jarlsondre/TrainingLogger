package traininglogger.core;

import java.util.*;


/** 
 * SessionLogger objektet inneholder en liste med sessions og et Map med rekorder.
 */
public class SessionLogger implements Iterable<Session> {

  private List<Session> sessions = new ArrayList<>();
  private Map<String, Double> records = new HashMap<>();

  public void updateRecordsWithSession(Session session){
    for (Exercise exercise : session){
      String name  = exercise.getName();
      double maxWeight = 0;
      for (Set set : exercise){
        maxWeight = Math.max(maxWeight, set.getWeight());
      }
      if (records.containsKey(name) && (maxWeight > records.get(name))){
        records.replace(name, maxWeight);
      }
      else{
        records.put(name, maxWeight);
      }
    }
  }

  public void initializeRecords(){
    records.clear();
    for(Session session : this){
      updateRecordsWithSession(session);
    }
  }

  public void deleteRecords(){
    records.clear();
  }

  @Override
  public Iterator<Session> iterator() {
    return sessions.iterator();
  }

  public void addSession(Session session) {
    updateRecordsWithSession(session);
    sessions.add(session);
    System.out.println(records);
  }

  public void deleteAll() {
    this.sessions.clear();
    this.deleteRecords();
  }

  public String toString() {
    return this.sessions.toString();
  }
}