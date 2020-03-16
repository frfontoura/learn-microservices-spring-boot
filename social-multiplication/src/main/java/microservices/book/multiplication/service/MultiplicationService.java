package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

/**
 * @author frfontoura
 * @version 1.0 13/03/2020
 */
public interface MultiplicationService {

    /**
     * Creates a Multiplication object with two randomly-generated factors between 11 and 99.
     *
     * @return Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the attempt matches the result of the multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    /**
     * @return the latest 5 attempts for a given user, identified by their alias
     */
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
