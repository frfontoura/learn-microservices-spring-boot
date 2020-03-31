package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This object contains the result of one or many iteractions of the game.<br>
 * It may contain any combination of {@link ScoreCard} objects and {@link BadgeCard} objects.<br>
 * <br><br>
 * It can be used as a delta (as a single game iteration) or represent the total amount of score / badges.
 *
 * @author frfontoura
 * @version 1.0 30/03/2020
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    private GameStats() {
        this.userId = 0l;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    /**
     * Factory method to build an empty instance (zero points and no badges)
     * @param userId the user's id
     * @return {@link GameStats} object with zero score and no badges
     */
    public static GameStats emptyStats(final Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    /**
     * @return an unmodifiable view of the badge cards list
     */
    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }

}
