package trainingLogger.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import trainingLogger.core.SessionLogger;

public class FileHandler {

    private static ObjectMapper makeMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TrainingLoggerModule());
        return mapper;
    }
    
    public static void writeToFile(OutputStream out, SessionLogger logger) throws IOException {
        Writer writer = new OutputStreamWriter(out);
        ObjectMapper mapper = FileHandler.makeMapper();
        writer.write(mapper.writeValueAsString(logger));
    }

    public static SessionLogger readFromFile(InputStream in) throws IOException{
        ObjectMapper mapper = FileHandler.makeMapper(); 
        SessionLogger logger = mapper.readValue(in, SessionLogger.class);
        return logger;
    }



}