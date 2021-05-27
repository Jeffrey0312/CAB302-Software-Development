package tradingPlatform;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {

    private static final long serialVersionUID = 332082608397623483L;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    /**
     * No args constructor.
     */
    public User() {
    }

    /**
     * Constructor to set values for the Person's details.
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     */
    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstname() {
        return firstName;
    }

    /**
     * @param firstName the name to set
     */
    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastname() {
        return lastName;
    }

    /**
     * @param lastName the name to set
     */
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the userName
     */
    public String getUsername() {
        return userName;
    }

    /**
     * @param userName the name to set
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the name to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other User object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareTo(User other) {
        return this.userName.compareTo(other.userName);
    }


}
