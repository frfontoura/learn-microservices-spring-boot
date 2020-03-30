package microservices.book.multiplication.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.MultiplicationSolvedEvent;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author frfontoura
 * @version 1.0 13/03/2020
 */
@Service
@AllArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final EventDispatcher eventDispatcher;

    @Override
    public Multiplication createRandomMultiplication() {
        final int factorA = randomGeneratorService.generateRandomFactor();
        final int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!");

        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        final boolean correct = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();

        final MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                correct
        );

        attemptRepository.save(checkedAttempt);

        eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(),
                checkedAttempt.getUser().getId(),
                checkedAttempt.isCorrect()));

        return correct;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
}
