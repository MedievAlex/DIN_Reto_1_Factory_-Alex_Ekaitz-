package model;

import java.sql.*;
import java.util.*;

public class DBImplementation implements ModelDAO {
	// Prepare statement variables
	private Connection con;
	private PreparedStatement stmt;

	// Prepare SQL Connection variables (with a supressed warning, as it's kind of annoying to see)
	private ResourceBundle configFile;
	@SuppressWarnings("unused")
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	/**[SQL QUERIES]**/

	// USERS
	final String SQLUSER = "SELECT * FROM User WHERE U_USERNAME = ?";
	final String SQLUSERPSW = "SELECT * FROM User WHERE U_USERNAME = ? AND U_PASSWORD = ?";
	final String SQLTYPE = "SELECT type_u FROM User WHERE U_USERNAME = ?";
	final String SQLINSERTUSER = "INSERT INTO User VALUES (?,?,?,'Client')";

	/**[DATABASE]**/

	// Declare implementation constructor
	public DBImplementation() {
	}

	// Method to open a new connection
	private void openConnection() {
	}

	/**[USERS]**/

	 // Verifies that the user exists and copies the information
	@Override
	public boolean verifyUser(User user) {
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
		return exists;
	}

	 // Verifies that the password matches
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

	// Verify the user type (only used once the user is verified)
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

        /*
	// Gets users's information
	public User getUser(User user) {
		ResultSet rs = null;

		// Opens the connection
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLUSER);
			stmt.setString(1, user.getU_username());
			rs = stmt.executeQuery();
			while (rs.next()) {
				user.setU_username(rs.getString("codUser"));
				user.setU_password(rs.getString("psw"));
				user.setU_name(rs.getString("username"));

				if (verifyUserType(user)) {
					user.setU_type(UserType.ADMIN);
				} else {
					user.setU_type(UserType.CLIENT);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("The user couldn't be retrieved.");
			e.printStackTrace();
		}
		return user;
	}
        */
}
