package microservices.book.multiplication.service;

/**
 * @author frfontoura
 * @version 1.0 13/03/2020
 */
public interface RandomGeneratorService {

    /**
     * @return a randomly-generated factor. It's always a number between 11 and 99
     */
    int generateRandomFactor();
}
