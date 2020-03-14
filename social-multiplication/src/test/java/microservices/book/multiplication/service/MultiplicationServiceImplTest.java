package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MultiplicationServiceImplTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

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
        Multiplication multiplication = new Multiplication(50,60);
        User user = new User("tony_stark");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkWrongAttempt() {
        Multiplication multiplication = new Multiplication(50,60);
        User user = new User("tony_stark");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        assertThat(attemptResult).isFalse();
    }
}
