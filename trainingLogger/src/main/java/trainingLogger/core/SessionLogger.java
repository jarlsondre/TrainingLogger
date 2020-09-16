package trainingLogger.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionLogger implements Iterable<Session> {

    private List<Session> sessions = new ArrayList<Session>();

    @Override
    public Iterator iterator() {
        return sessions.iterator();
    }

    public void addSession(Session session){
        sessions.add(session);
    }

    public void load(){
        // Kaller statisk metode i FileHandler
        // Resultatet er at sessions fylles opp med Session-objekter
    }

    public void save(){
        // Kaller statisk metode i FileHandler
        // Resultatet er at Session-objektene i sessions lagres på disk i JSON-format
    }
    
}