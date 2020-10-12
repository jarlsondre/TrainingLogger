package traininglogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import traininglogger.core.Exercise;

public class ExerciseDeserializer extends JsonDeserializer<Exercise> {

  /*
   * Denne klassen inneholder metoden deserializer, som konverterer et json
   * formatert exercise objekt tilbake til objektet. Objektet må ha formatet: {
   * "name": " ... " "sets": " ... " }, hvor sets er en liste av tupler, henholdsvis reps og vekt.
   * Eksempel: {\"name\":\"Knebøy\",\"sets\":[5,5,6,6]}
   */

  @Override
  public Exercise deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treenode = parser.getCodec().readTree(parser); 
    return deserialize((JsonNode) treenode);
  }

  public Exercise deserialize(JsonNode jnode) {
    if (jnode instanceof ObjectNode) {
      ObjectNode node = (ObjectNode) jnode;
      Exercise exercise = null;
      JsonNode textNode1 = node.get("name");
      if (textNode1 instanceof TextNode) {
        exercise = new Exercise(((TextNode) textNode1).asText());
      }
      JsonNode setsNode = node.get("sets");
      if (setsNode instanceof ArrayNode) {
        ArrayNode setsNodeArray = (ArrayNode) setsNode;
        Integer[] integers = new Integer[setsNodeArray.size()];
        int counter = 0;
        for (JsonNode element : setsNodeArray) {
          Integer integer = element.asInt();
          integers[counter] = integer;
          counter++;
        }
        exercise.addSets(integers);
      }
      return exercise;
    }
    return null;
  }

}