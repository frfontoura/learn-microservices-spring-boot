package microservices.book.testutils.beans;

/**
 * @author frfontoura
 * @version 1.0 04/04/2020
 */
public class AttemptResponse {

    private boolean correct;
    private long id;
    private User user;

    public AttemptResponse() {
    }

    public boolean isCorrect() {
        return correct;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
