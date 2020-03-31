package microservices.book.gamification.client;

import microservices.book.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This implementation of MultiplicationResultAttemptClient interface connects to the Multiplication microservice via REST.
 *
 * @author frfontoura
 * @version 1.0 31/03/2020
 */
@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient {

    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    public MultiplicationResultAttemptClientImpl(final RestTemplate restTemplate, @Value("${multiplicationHost}") final String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationResultAttemptId) {
        return restTemplate.getForObject(multiplicationHost + "/results/" + multiplicationResultAttemptId, MultiplicationResultAttempt.class);
    }
}
