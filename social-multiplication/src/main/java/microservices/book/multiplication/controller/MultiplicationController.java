package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements a REST API for our Multiplication application
 *
 * @author frfontoura
 * @version 1.0 14/03/2020
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/multiplications")
public final class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        log.info("Generating a random  multiplication from server @{}", serverPort);
        return multiplicationService.createRandomMultiplication();
    }

}

