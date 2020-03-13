package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;

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
}
