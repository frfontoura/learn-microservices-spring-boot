package microservices.book.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import microservices.book.gamification.client.MultiplicationResultAttemptDeserializer;

/**
 * Identifies the attempt from a user to solve a multiplication.
 *
 * @author frfontoura
 * @version 1.0 31/03/2020
 */
@Builder
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public class MultiplicationResultAttempt {

    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;
    private final boolean correct;

    private MultiplicationResultAttempt() {
        this(null, -1, -1, -1, false);
    }
}
