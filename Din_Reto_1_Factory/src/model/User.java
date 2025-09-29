package model;

import java.io.Serializable;

/**
 * @author Alex Irazola, Ekaitz Campo
 */
public class User implements Serializable {
    // Declare variables

    private String u_username;
    private String u_password;
    private String u_name;
    private String u_lastname;
    private UserType u_type;

    /**
     * Empty constructor.
     */
    public User() {
        this.u_username = "";
        this.u_password = "";
        this.u_name = "";
        this.u_lastname = "";
        this.u_type = UserType.CLIENT;
    }

    /**
     * Parametized constructor to create the user with the necessary variables
     * to verify if it exists.
     *
     * @param u_username
     * @param u_password
     */
    public User(String u_username, String u_password) {
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_name = "";
        this.u_lastname = "";
        this.u_type = UserType.CLIENT;
    }

    /**
     * Complete parametized constructor to create the user with the full
     * information.
     *
     * @param u_username
     * @param u_password
     * @param u_name
     * @param u_lastname
     * @param u_type
     */
    public User(String u_username, String u_password, String u_name, String u_lastname, UserType u_type) {
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_name = u_name;
        this.u_lastname = u_lastname;
        this.u_type = u_type;
    }

    /**
     * Username's getter.
     * 
     * @return u_username 
     */
    public String getU_username() {
        return u_username;
    }

    /**
     * Username's setter.
     * 
     * @param u_username 
     */
    public void setU_username(String u_username) {
        this.u_username = u_username;
    }
    
    /**
     * Password's getter.
     * 
     * @return u_password
     */
    public String getU_password() {
        return u_password;
    }

    /**
     * Password's setter.
     * 
     * @param u_password 
     */
    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    /**
     * Name's getter.
     * 
     * @return u_name
     */
    public String getU_name() {
        return u_name;
    }

    /**
     * Name's setter.
     * 
     * @param u_name 
     */
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    
    /**
     * Last name's getter.
     * 
     * @return u_lastname
     */

    public String getU_lastname() {
        return u_lastname;
    }
    
    /**
     * Last name's setter.
     * 
     * @param u_lastname 
     */

    public void setU_lastname(String u_lastname) {
        this.u_lastname = u_lastname;
    }

    /**
     * User type's getter.
     * 
     * @return u_type
     */
    public UserType getU_type() {
        return u_type;
    }

    /**
     * User type's setter.
     * 
     * @param u_type 
     */
    public void setU_type(UserType u_type) {
        this.u_type = u_type;
    }

    /**
     * Method for the use of showing the variables of the object.
     * 
     * @return string
     */
    @Override
    public String toString() {
        return "User [Username: " + u_username + ", Password: " + u_password  + ", Name: " + u_name + ", Lastname" + u_lastname + ", Type: " + u_type + "]";
    }
}
