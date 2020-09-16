package trainingLogger.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import trainingLogger.core.Session;

public class SessionListSerializer extends JsonSerializer<ArrayList<Session>> {


    /*
   * format: 
   * {"sessions": [ ... ]}
   */

    @Override
    public void serialize(ArrayList<Session> ar, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("sessions");
        for(Session s : ar){
            gen.writeObject(s);
        }
        gen.writeEndArray();
        gen.writeEndObject();

    }

}