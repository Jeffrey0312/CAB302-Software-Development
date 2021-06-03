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
    void getFirstname() throws Exception {
  assertEquals(null,user.getFirstname());
    }

    @Test
    void setFirstname() {

    }

    @Test
    void getLastname() {
        assertEquals(null,user.getLastname());
    }

    @Test
    void setLastname() {
    }

    @Test
    void getUsername() {
    assertEquals(null,user.getUsername());
    }

    @Test
    void setUsername() {
    }

    @Test
    void getPassword() {
        assertEquals(null,user.getPassword());
    }

    @Test
    void setPassword() {
    }

    /**
     * compare different users
     */
    @Test
    void compareTo() {
        User aUser = new User();
        User bUser = new User();
        assertTrue(aUser.getFirstname() ==bUser.getFirstname());

    }
}