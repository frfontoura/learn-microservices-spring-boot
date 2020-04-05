package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.MultiplicationSolvedEvent;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MultiplicationServiceImplTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventDispatcher eventDispatcher;

    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    @Test
    public void createRandomMultiplicationTest() {
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        final Multiplication multiplication = multiplicationService.createRandomMultiplication();

        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        final Multiplication multiplication = new Multiplication(50, 60);
        final User user = new User("john_doe");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user, multiplication, 3000, false);
        final MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(
                user, multiplication, 3000, true);
        final MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(attempt.getId(),
                attempt.getUser().getId(), true);
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        // Note: the service will set correct to true
        given(attemptRepository.save(verifiedAttempt)).willReturn(verifiedAttempt);

        // when
        final MultiplicationResultAttempt resultAttempt = multiplicationService.checkAttempt(attempt);

        // then
        assertThat(resultAttempt.isCorrect()).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
        verify(eventDispatcher).send(eq(event));
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        final Multiplication multiplication = new Multiplication(50, 60);
        final User user = new User("john_doe");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user, multiplication, 3010, false);
        final MultiplicationResultAttempt storedAttempt = new MultiplicationResultAttempt(
                user, multiplication, 3010, false);
        final MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(attempt.getId(),
                attempt.getUser().getId(), false);
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        given(attemptRepository.save(attempt)).willReturn(storedAttempt);

        // when
        final MultiplicationResultAttempt resultAttempt = multiplicationService.checkAttempt(attempt);

        // then
        assertThat(resultAttempt.isCorrect()).isFalse();
        verify(attemptRepository).save(attempt);
        verify(eventDispatcher).send(eq(event));
    }

    @Test
    public void retrieveStatsTest() {
        final Multiplication multiplication = new Multiplication(50, 60);
        final User user = new User("tony_stark");
        final MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        final MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
        final List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);
        given(userRepository.findByAlias(user.getAlias())).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc(user.getAlias())).willReturn(latestAttempts);

        final List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationService.getStatsForUser(user.getAlias());

        assertThat(latestAttempts).isEqualTo(latestAttempts);
    }
}
