package traininglogger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import traininglogger.core.Exercise;
import traininglogger.core.Set;

/**
 * Denne klassen inneholder metoden deserializer, som konverterer et json
 * formatert exercise objekt tilbake til objektet. Objektet må ha formatet: {
 * "name": " ... " "sets": " ... " }, hvor sets er en liste av tupler,
 * henholdsvis reps og vekt. Eksempel: {\"name\":\"Knebøy\",\"sets\":[5,5,6,6]}
 */
public class ExerciseDeserializer extends JsonDeserializer<Exercise> {

  private SetDeserializer setDeserializer = new SetDeserializer();

  @Override
  public Exercise deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treenode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treenode);
  }

  /**
   * Konstruerer et Exercise-objekt fra en parset JsonNode.
   *
   * @param jsonNode templatet for nytt Exercise-objekt
   * @return det rekonstruerte Exercise-objektet hvis deserialiseringen lykkes, null ellers.
   */
  public Exercise deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JsonNode nameNode = objectNode.get("name");
      JsonNode setsNode = objectNode.get("sets");
      if (nameNode instanceof TextNode && setsNode instanceof ArrayNode) {
        String name = ((TextNode) nameNode).asText();
        ArrayNode sets = (ArrayNode) setsNode;
        Exercise exercise = new Exercise(name);
        for (JsonNode element : sets) {
          Set set = this.setDeserializer.deserialize(element);
          if (set != null) {
            exercise.addSets(set);
          }
        }
        return exercise;
      }
    }
    return null;
  }
}