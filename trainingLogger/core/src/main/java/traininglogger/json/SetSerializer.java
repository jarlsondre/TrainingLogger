package traininglogger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import traininglogger.core.Set;

/**
 * Denne klassen inneholdet metoden serialize for å konvertere et objekt av Set
 * til json format. format: { "reps": " ... " "weight": " ... "} Eksempel:
 * {\"reps\":\"5\",\"weight\"\"55.0\"}
 */
public class SetSerializer extends JsonSerializer<Set> {

  @Override
  public void serialize(Set set, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("repetitions", set.getRepetitions());
    gen.writeNumberField("weight", set.getWeight());
    gen.writeEndObject();
  }

}