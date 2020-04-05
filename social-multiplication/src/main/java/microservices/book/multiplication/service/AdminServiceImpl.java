package microservices.book.multiplication.service;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.repository.MultiplicationRepository;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author frfontoura
 * @version 1.0 04/04/2020
 */
@RequiredArgsConstructor
@Profile("test")
@Service
public class AdminServiceImpl implements AdminService{

    private final  MultiplicationRepository multiplicationRepository;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;

    @Override
    public void deleteDatabaseContents() {
        attemptRepository.deleteAll();
        multiplicationRepository.deleteAll();
        userRepository.deleteAll();
    }
}
