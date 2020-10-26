package traininglogger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class SessionLoggerSerializer extends JsonSerializer<SessionLogger> {

  @Override
  public void serialize(SessionLogger sessionLogger, JsonGenerator gen, SerializerProvider serializerProvider)
      throws IOException {
    gen.writeStartObject();
    gen.writeArrayFieldStart("sessions");
    for (Session session : sessionLogger) {
      gen.writeObject(session);
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }
}