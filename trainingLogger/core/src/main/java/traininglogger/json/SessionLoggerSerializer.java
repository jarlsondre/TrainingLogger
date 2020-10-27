package traininglogger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class SessionLoggerSerializer extends JsonSerializer<SessionLogger> {

  @Override
  public void serialize(SessionLogger logger, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeArrayFieldStart("sessions");
    for (Session session : logger) {
      gen.writeObject(session);
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }
}