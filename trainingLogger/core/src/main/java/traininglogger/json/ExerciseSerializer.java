package traininglogger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import traininglogger.core.Exercise;
import traininglogger.core.Set;

public class ExerciseSerializer extends JsonSerializer<Exercise> {

  /* format:
     * {
     * "name" : "...",
     * "sets" :[...]
     * }
     */

  @Override
  public void serialize(Exercise exercise, JsonGenerator gen, SerializerProvider arg2) 
      throws IOException {
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