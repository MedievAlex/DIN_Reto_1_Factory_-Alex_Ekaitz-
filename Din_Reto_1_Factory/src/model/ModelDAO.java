package model;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public interface ModelDAO {

    /**
     * Verifies if the user exists and if it does it copies all the attributes
     * to the object to return it.
     *
     * @param user
     */
    public User verifyUser(User user); // 

    /**
     * Verifies that the password matches returning a boolean.
     *
     * @param user
     */
    public boolean verifyUserPassword(User user); // Verifies that the password matches

    /**
     * Verifies the user's type to see if its an Admin.
     *
     * @param user
     */
    public boolean verifyUserType(User user); // Verify the user type

}
