package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author frfontoura
 * @version 1.0 14/03/2020
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @PostMapping
    public ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody final MultiplicationResultAttempt attempt) {
        final boolean correct = multiplicationService.checkAttempt(attempt);
        final MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                attempt.getUser(),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                correct
        );
        return ResponseEntity.ok(checkedAttempt);
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") final String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

}
