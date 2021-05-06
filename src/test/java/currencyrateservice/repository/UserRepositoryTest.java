package currencyrateservice.repository;

import currencyrateservice.domain.User;
import currencyrateservice.domain.security.Role;
import currencyrateservice.domain.security.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("UserRepository")
class UserRepositoryTest {

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String EMAIL = "email";

    private static final String PASSWORD = "password";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        user = createUser();
    }

    @Test
    void shouldReturnExistingUserByEmail() {
        User expected = entityManager.persistAndFlush(user);
        User actual = repository.findUserByEmail(EMAIL).get();
        assertThat(actual).isEqualTo(expected);
    }

    private User createUser() {
        return User.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
