package microservices.book.gamification.service;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.repository.ScoreCardRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author frfontoura
 * @version 1.0 04/04/2020
 */
@RequiredArgsConstructor
@Profile("test")
@Service
public class AdminServiceImpl implements AdminService {

    private final BadgeCardRepository badgeCardRepository;
    private final  ScoreCardRepository scoreCardRepository;

    @Override
    public void deleteDatabaseContents() {
        scoreCardRepository.deleteAll();
        badgeCardRepository.deleteAll();
    }
}
