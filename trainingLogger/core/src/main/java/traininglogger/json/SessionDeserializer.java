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
import traininglogger.core.Session;

/**
 * Denne klassen inneholder metoden deserializer, som konverterer et json
 * formatert session objekt tilbake til objektet. Objektet må ha formatet: {
 * "stringDescription": " ... " "date": "dd/MM/yyyy HH:mm" "exercises": " ... "
 * }
 */
public class SessionDeserializer extends JsonDeserializer<Session> {

  private ExerciseDeserializer exerciseDeserializer = new ExerciseDeserializer();

  @Override
  public Session deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treenode = parser.getCodec().readTree(parser);
    return this.deserialize((JsonNode) treenode);
  }

  /**
   * Konstruerer et Session-objekt fra en parset JsonNode.
   *
   * @param jsonNode templatet for nytt Session-objekt
   * @return det rekonstruerte Session-objektet hvis deserialiseringen lykkes, null ellers.
   */
  public Session deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode node = (ObjectNode) jsonNode;
      Session session = new Session();
      JsonNode textNode1 = node.get("stringDescription");
      if (textNode1 instanceof TextNode) {
        session.setDescription(((TextNode) textNode1).asText());
      }
      JsonNode textNode2 = node.get("date");
      if (textNode2 instanceof TextNode) {
        session.setDate(((TextNode) textNode2).asText());
      }
      JsonNode setsNode = node.get("exercises");
      if (setsNode instanceof ArrayNode) {
        ArrayNode setsNodeArray = (ArrayNode) setsNode;
        Exercise[] exercises = new Exercise[setsNodeArray.size()];
        int counter = 0;
        for (JsonNode element : ((ArrayNode) setsNode)) {
          Exercise exercise = this.exerciseDeserializer.deserialize(element);
          if (exercise != null) {
            exercises[counter] = exercise;
          }
          counter++;
        }
        session.addExercises(exercises);
      }
      return session;
    }
    return null;
  }

}