package traininglogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import traininglogger.core.Exercise;
import traininglogger.core.Set;

  /**
   * Denne klassen inneholdet metoden serialize for å konvertere et objekt til
   * json format. format: 
   * { "name": " ... " 
   * "sets": " ... "}
   * Eksempel: {\"name\":\"Knebøy\",\"sets\":[5,5,6,6]}
   */
public class ExerciseSerializer extends JsonSerializer<Exercise> {

  @Override
  public void serialize(Exercise exercise, JsonGenerator gen, SerializerProvider arg2) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("name", exercise.getName());
    gen.writeArrayFieldStart("sets");
    for (Set set : exercise) {
      gen.writeObject(set);
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }

}