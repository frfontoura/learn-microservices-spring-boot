package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author frfontoura
 * @version 1.0 14/03/2020
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @Value("${server.port}")
    private int serverPort;

    @PostMapping
    public ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody final MultiplicationResultAttempt attempt) {
        return ResponseEntity.ok(multiplicationService.checkAttempt(attempt));
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") final String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

    @GetMapping("/{resultId}")
    ResponseEntity<MultiplicationResultAttempt> getResultById(final @PathVariable("resultId") Long resultId) {
        log.info("Retrieving result {} from server @ {}", resultId, serverPort);
        return ResponseEntity.ok(
                multiplicationService.getResultById(resultId)
        );
    }

}
