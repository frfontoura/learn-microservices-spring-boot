package microservices.book.testutils.beans;

/**
 * @author frfontoura
 * @version 1.0 04/04/2020
 */
public class ScoreResponse {
    private long userId;
    private int score;

    public ScoreResponse() {
    }

    public ScoreResponse(final int score) {
        this.score = score;
    }

    public long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }
}
