package microservices.book.gamification.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import microservices.book.gamification.client.dto.MultiplicationResultAttempt;

import java.io.IOException;

/**
 * Deserializes an attempt coming from the Multiplication microservice into the Gamification's representation of an attempt
 *
 * @author frfontoura
 * @version 1.0 31/03/2020
 */
public class MultiplicationResultAttemptDeserializer extends JsonDeserializer<MultiplicationResultAttempt> {

    @Override
    public MultiplicationResultAttempt deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final ObjectCodec oc = jsonParser.getCodec();
        final JsonNode node = oc.readTree(jsonParser);
        return MultiplicationResultAttempt.builder()
                .userAlias(node.get("user").get("alias").asText())
                .multiplicationFactorA(node.get("multiplication").get("factorA").asInt())
                .multiplicationFactorB(node.get("multiplication").get("factorB").asInt())
                .resultAttempt(node.get("resultAttempt").asInt())
                .correct(node.get("correct").asBoolean())
                .build();
    }
}
