package trainingLogger.core;

import trainingLogger.json.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionLogger implements Iterable<Session> {

    private List<Session> sessions = new ArrayList<>();

    @Override
    public Iterator<Session> iterator() {
        return sessions.iterator();
    }

    public void addSession(Session session){
        sessions.add(session);
    }

    public List<Session> getSessions() {return this.sessions;}

    public boolean load(){
        // Kaller statisk metode i FileHandler
        // Resultatet er at sessions fylles opp med Session-objekter
        try {
            InputStream inputstream = new FileInputStream("session_data.txt");
            this.sessions = FileHandler.readFromFile(inputstream);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean save(){
        // Kaller statisk metode i FileHandler
        // Resultatet er at Session-objektene i sessions lagres på disk i JSON-format
        try {
            File file = new File("session_data.txt");
            OutputStream fop = new FileOutputStream(file);
            FileHandler.writeToFile(fop, this.sessions);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void delete(){
        this.sessions = new ArrayList<>();
    }
    
}