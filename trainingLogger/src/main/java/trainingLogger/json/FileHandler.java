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
import trainingLogger.core.SessionLogger;

public class FileHandler {

    private static ObjectMapper makeMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TrainingLoggerModule());
        return mapper;
    }
    
    public static void writeToFile(OutputStream out, List<Session> sessions) throws IOException {
        Writer writer = new OutputStreamWriter(out);
        ObjectMapper mapper = FileHandler.makeMapper();
        writer.write(mapper.writeValueAsString(sessions));
    }

    public static List<Session> readFromFile(InputStream in) throws IOException{
        ObjectMapper mapper = FileHandler.makeMapper();  
        List<Session> sessions = mapper.readValue(in, new TypeReference<List<Session>>() {});
        return sessions;
    }



}