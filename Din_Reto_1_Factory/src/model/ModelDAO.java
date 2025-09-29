package model;

/**
 * @author Alex Irazola, Ekaitz Campo
 */
public interface ModelDAO {

    /**
     * Verifies if the user exists
     *
     * @param username
     * @return boolean
     */
    public boolean verifyUserExists(String username); // Verifies that the user exists

    /**
     * Verifies that the password matches returning a boolean.
     *
     * @param username
     * @param password
     * @return boolean
     */
    public boolean verifyUserPassword(String username, String password); // Verifies that the password matches

    /**
     * Verifies the user's type to see if its an Admin.
     *
     * @param username
     * @return User
     */
    public User getUser(String username); // Gets the user

}
