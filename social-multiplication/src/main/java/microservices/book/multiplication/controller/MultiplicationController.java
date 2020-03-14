package microservices.book.multiplication.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements a REST API for our Multiplication application
 *
 * @author frfontoura
 * @version 1.0 14/03/2020
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/multiplications")
public final class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }
}

