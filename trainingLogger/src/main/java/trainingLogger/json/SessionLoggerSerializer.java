package trainingLogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import trainingLogger.core.Session;
import trainingLogger.core.SessionLogger;

public class SessionLoggerSerializer extends JsonSerializer<SessionLogger> {

    /*
     * format: {"sessions": [ ... ]}
     */

    @Override
    public void serialize(SessionLogger logger, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("sessions");
        for (Session s : logger) {
            gen.writeObject(s);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}