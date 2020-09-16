package trainingLogger.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import trainingLogger.core.Session;

public class FileHandler {

    private static ObjectMapper makeMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new TrainingLoggerModule());
        return mapper;
    }
    
    public static void writeToFile(Writer writer, Collection<Session> col) throws IOException {
        ObjectMapper mapper = FileHandler.makeMapper();
        writer.write(mapper.writeValueAsString(col));
    }


    public static void readFromFile(InputStream in){
        ObjectMapper mapper = FileHandler.makeMapper(); 
        //Collection<Session> col = mapper.readValue(in, ArrayList<>);

    }



}