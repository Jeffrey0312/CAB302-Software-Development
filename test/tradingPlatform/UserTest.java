package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFirstname() {
        assertNotNull(user.getFirstname());
    }

    @Test
    void setFirstname() {

    }

    @Test
    void getLastname() {
        assertNotNull(user.getLastname());
    }

    @Test
    void setLastname() {
    }

    @Test
    void getUsername() {
        assertNotNull(user.getUsername());
    }

    @Test
    void setUsername() {
    }

    @Test
    void getPassword() {
        assertNotNull(user.getPassword());
    }

    @Test
    void setPassword() {
    }

    @Test
    void compareTo() {
    }
}