package model;

import java.sql.*;
import java.util.*;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class DBImplementation implements ModelDAO {

    /**
     * Prepare statement variables.
     */
    private Connection con;
    private PreparedStatement stmt;

    /**
     * Prepare SQL Connection's attributes.
     */
    private ResourceBundle configFile;
    @SuppressWarnings("unused")
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    /**
     * Prepare SQL queries.
     */
    final String SQLUSER = "SELECT * FROM User WHERE U_USERNAME = ?";
    final String SQLUSERPSW = "SELECT * FROM User WHERE U_USERNAME = ? AND U_PASSWORD = ?";
    final String SQLTYPE = "SELECT type_u FROM User WHERE U_USERNAME = ?";

    /**
     * DBIplementation's constructor declaration.
     */
    public DBImplementation() {
        this.configFile = ResourceBundle.getBundle("model.classConfig");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /**
     * Opens connection with the database.
     */
    private void openConnection() {
        try {
            // Try opening the connection
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            System.out.println("Error when attempting to open the DB.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the user exists and if it does it copies all the attributes
     * to the object to return it.
     *
     * @param user
     * @return user
     */
    @Override
    public User verifyUser(User user) {
        // Open connection and declare a boolean to check if the user exists
        boolean exists = false;
        this.openConnection();

        try {
            // Prepares the SQL query
            stmt = con.prepareStatement(SQLUSER);
            stmt.setString(1, user.getU_username());
            // Executes the SQL query
            ResultSet rs = stmt.executeQuery();
            // If there is any result, the user exists
            if (rs.next()) {
                user.setU_username(rs.getString("codUser"));
                user.setU_password(rs.getString("psw"));
                user.setU_name(rs.getString("username"));

                if (verifyUserType(user)) {
                    user.setU_type(UserType.ADMIN);
                } else {
                    user.setU_type(UserType.CLIENT);
                }
            }
            // Closes the connection
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("The user couldn't be verified properly.");
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Verifies that the password matches returning a boolean. TRUE if they
     * match, FALSE if not.
     *
     * @param user
     * @return exists
     */
    @Override
    public boolean verifyUserPassword(User user) {
        // Open connection and declare a boolean to check if the password exists and matches
        boolean exists = false;
        this.openConnection();

        try {
            // Prepares the SQL query
            stmt = con.prepareStatement(SQLUSERPSW);
            stmt.setString(1, user.getU_username());
            stmt.setString(2, user.getU_password());
            // Executes the SQL query
            ResultSet rs = stmt.executeQuery();
            // If there is any result, the password exists and matches properly
            if (rs.next()) {
                exists = true;
            }
            // Closes the connection
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("The user couldn't be verified properly.");
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * Verifies the user's type to see if its an Admin. TRUE if its an admin,
     * FALSE if not.
     *
     * @param user
     * @return admin
     */
    @Override
    public boolean verifyUserType(User user) {
        // Open connection and declare a boolean to check if the user is an admin
        boolean admin = false;
        this.openConnection();

        try {
            // Prepares the SQL query
            stmt = con.prepareStatement(SQLTYPE);
            stmt.setString(1, user.getU_username());
            // Executes the SQL query
            ResultSet rs = stmt.executeQuery();
            // If there is any result, the user exists, and they are an admin
            if (rs.next() && rs.getString(1).equals("Admin")) {
                admin = true;
            }
            // Closes the connection
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("The user couldn't be verified properly.");
            e.printStackTrace();
        }
        return admin;
    }
}
