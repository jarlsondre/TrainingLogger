package traininglogger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import traininglogger.core.Exercise;
import traininglogger.core.Session;

public class SessionSerializer extends JsonSerializer<Session> {

  /*
   * Denne klassen inneholdet metoden serialize for å konvertere et objekt til
   * json format. format: { "stringDescription": " ... " "date":
   * "dd/MM/yyyy HH:mm" "exercises": " ... "}
   */

  @Override
  public void serialize(Session session, JsonGenerator gen, SerializerProvider serializerProvider)
      throws IOException {
    gen.writeStartObject();
    gen.writeStringField("stringDescription", session.getDescription());
    gen.writeStringField("date", session.getDateAsString());
    gen.writeArrayFieldStart("exercises");
    for (Exercise e : session) {
      gen.writeObject(e);
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }

}