package trainingLogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import trainingLogger.core.Session;

public class SessionSerializer extends JsonSerializer<Session>{

    /*
    format: 
    {
        "stringDescription": " ... "
        "date": "dd/MM/yyyy HH:mm"
    }
    */

    @Override
    public void serialize(Session session, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("stringDescription", session.getDescription());
        jGen.writeStringField("date", session.getDateString());
        jGen.writeEndObject();
    }



}