package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Stores information to identify the user
 * @author frfontoura
 * @version 1.0 14/03/2020
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class User {

    private final String alias;

    protected User() {
        alias = null;
    }
}