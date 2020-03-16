package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface allows us to save and retrieve Multiplications
 * @author frfontoura
 * @version 1.0 16/03/2020
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
