package tradingPlatform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    @BeforeEach
    public void newUser() throws Exception {
        user=new User();
    }
    @Test
    @DisplayName("test")
    void getFirstname(User user) {
  assertEquals("test",user.getFirstname());
    }

    @Test
    @DisplayName("test")
    void setFirstname(User userName) {
    }

    @Test
    @DisplayName("test")
    void getLastname(User user) {
        assertEquals("test",user.getLastname());
    }

    @Test
    void setLastname() {
    }

    @Test
    @DisplayName("test")
    void getUsername() {
    assertEquals("test",user.getUsername());
    }

    @Test
    void setUsername() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }

    /**
     * compare different users
     */
    @Test
    void compareTo() {
        user.compareTo(user);
        User aUser = new User();
        User bUser = new User();
        assertTrue(aUser.getFirstname()!=bUser.getFirstname());

    }
}