package model;

import java.sql.*;
import java.util.*;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class DBImplementation implements ModelDAO {

    private Connection con;
    private PreparedStatement stmt;

    private ResourceBundle configFile;
    @SuppressWarnings("unused")
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    final String SQLUSER = "SELECT * FROM User WHERE U_USERNAME = ?";
    final String SQLUSERPSW = "SELECT * FROM User WHERE U_USERNAME = ? AND U_PASSWORD = ?";
    final String SQLUSERS = "SELECT * FROM User";

    public DBImplementation() {
        this.configFile = ResourceBundle.getBundle("model.classConfig");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    private void openConnection() throws SQLException {
        con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
    }

    @Override
    public boolean verifyUserExists(String username) {
        boolean exists = false;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSER);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            exists = rs.next();
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error verifying user existence.");
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean verifyUserPassword(String username, String password) {
        boolean valid = false;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSERPSW);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            valid = rs.next();
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error verifying user password.");
            e.printStackTrace();
        }
        return valid;
    }

    @Override
    public User getUser(String username) {
        User user = null;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSER);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("U_USERNAME"),
                    rs.getString("U_PASSWORD"),
                    rs.getString("U_NAME"),
                    rs.getString("U_LASTNAME"),
                    UserType.valueOf(rs.getString("U_TYPE"))
                );
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving full user data.");
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSERS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getString("U_USERNAME"),
                    rs.getString("U_PASSWORD"),
                    rs.getString("U_NAME"),
                    rs.getString("U_LASTNAME"),
                    UserType.valueOf(rs.getString("U_TYPE"))
                );
                users.add(user);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving users.");
            e.printStackTrace();
        }
        return users;
    }
}
