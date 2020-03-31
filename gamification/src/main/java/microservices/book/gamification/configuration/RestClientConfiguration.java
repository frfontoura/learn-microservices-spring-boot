package microservices.book.gamification.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configures the REST client in our application.
 *
 * @author frfontoura
 * @version 1.0 31/03/2020
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
