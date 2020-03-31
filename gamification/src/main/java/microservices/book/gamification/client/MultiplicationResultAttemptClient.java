package microservices.book.gamification.client;

import microservices.book.gamification.client.dto.MultiplicationResultAttempt;

/**
 * This interface allows us to connect to the Multiplication microservice.<br>
 *
 * @author frfontoura
 * @version 1.0 31/03/2020
 */
public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationId);

}
