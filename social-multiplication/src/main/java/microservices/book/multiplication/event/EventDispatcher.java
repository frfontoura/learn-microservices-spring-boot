package microservices.book.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Handles the communication with the Event Bus
 *
 * @author frfontoura
 * @version 1.0 30/03/2020
 */
//TODO verificar se o @RequiredArgsConstructor funciona aqui
@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;
    private String multiplicationExchange;
    private String multiplicationSolvedRoutingKey;

    @Autowired
    public EventDispatcher(final RabbitTemplate rabbitTemplate,
                           @Value("${multiplication.exchange}") final String multiplicationExchange,
                           @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
                multiplicationExchange,
                multiplicationSolvedRoutingKey,
                multiplicationSolvedEvent);
    }

}
