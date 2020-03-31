package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents a line in our LeaderBoard: it links a user to a total score.
 *
 * @author frfontoura
 * @version 1.0 30/03/2020
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    private LeaderBoardRow() {
        this(null, null);
    }
}
