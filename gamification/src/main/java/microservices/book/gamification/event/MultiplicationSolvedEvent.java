package microservices.book.gamification.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Event that models the fact that a Multiplication
 * has been solved in the system. Provides some context information about the multiplication.
 *
 * @author frfontoura
 * @version 1.0 29/03/2020
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {

    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;

    private MultiplicationSolvedEvent() {
        this(null, null, false);
    }

}
