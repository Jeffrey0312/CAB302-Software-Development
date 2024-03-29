package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    @BeforeEach
    void setUp() {
        user = new User("AmyL","Amy","Lin","123");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFirstname() {
        assertEquals("Amy",user.getFirstname());
    }


    @Test
    void getLastname() {
        assertEquals("Lin",user.getLastname());
    }


    @Test
    void getUsername() {
        assertEquals("AmyL",user.getUsername());
    }

    @Test
    void setUsername() {assertEquals("AmyL",user.getUsername());

    }

    @Test
    void getPassword() {
        assertEquals("123",user.getPassword());
    }

    @Test
    void setPassword() {
    }


}