package model;

import java.sql.*;
import java.util.*;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class DBImplementation implements ModelDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
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

    /**
     * Verifica si el usuario dado es correcto.
     * 
     * @param username
     * @return exists
     */
    @Override
    public boolean verifyUserExists(String username) {
        boolean exists = false;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSER);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
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

    /**
     * Verifica si la contraseña es correcta dado un usuario.
     * 
     * @param username
     * @param password
     * @return valid
     */
    @Override
    public boolean verifyUserPassword(String username, String password) {
        boolean valid = false;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSERPSW);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
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
    
    /**
     * Obtiene el usuario con todos sus datos.
     * 
     * @param username
     * @return user
     */
    @Override
    public User getUser(String username) {
        User user = null;
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSER);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
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
    
    /**
     * Obtiene todos los usuarios de la base de datos.
     * 
     * @return users
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            openConnection();
            stmt = con.prepareStatement(SQLUSERS);
            rs = stmt.executeQuery();
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