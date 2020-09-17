package trainingLogger.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import trainingLogger.core.Session;

public class FileHandler {

    /*
    Denne klassen skal håndtere lagring av objekter ved filskriving og fillesning.
    Metodene er statiske.
    */

    //Statisk metode som lager en ObjectMapper med riktig modul registrert.
    private static ObjectMapper makeMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TrainingLoggerModule());
        return mapper;
    }
    
    //Statisk metode som skriver en liste med sessions til fil med bruk av json.
    public static void writeToFile(OutputStream out, List<Session> sessions) throws IOException {
        Writer writer = new OutputStreamWriter(out);
        ObjectMapper mapper = FileHandler.makeMapper();
        mapper.writeValue(writer, sessions);

    }

    //Statisk metode som leser en json fil, og returnerer en liste med sessions.
    public static List<Session> readFromFile(InputStream in) throws IOException{
        ObjectMapper mapper = FileHandler.makeMapper();  
        List<Session> sessions = mapper.readValue(in, new TypeReference<List<Session>>() {});
        return sessions;
    }



}