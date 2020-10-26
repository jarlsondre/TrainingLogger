package traininglogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import traininglogger.core.Set;

/**
   * Denne klassen inneholdet metoden serialize for å konvertere et objekt av Set til
   * json format. format: 
   * { "reps": " ... " 
   * "weight": " ... "}
   * Eksempel: {\"reps\":\"5\",\"weight\"\"55.0\"}
   * 
*/
public class SetSerializer extends JsonSerializer<Set> {

  @Override
  public void serialize(Set set, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("repetitions", Integer.toString(set.getRepetitions()));
    gen.writeStringField("weight", Double.toString(set.getWeight()));
    gen.writeEndObject();
  }








}