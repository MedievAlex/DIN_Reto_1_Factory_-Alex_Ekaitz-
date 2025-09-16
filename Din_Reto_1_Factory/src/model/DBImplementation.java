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
	final String SQLUSER = "SELECT * FROM user WHERE codUser = ?";
	final String SQLUSERPSW = "SELECT * FROM user WHERE codUser = ? AND psw = ?";
	final String SQLTYPE = "SELECT type_u FROM user WHERE codUser = ?";
	final String SQLINSERTUSER = "INSERT INTO user VALUES (?,?,?,'Client')";

	/**[DATABASE]**/

	// Declare implementation constructor
	public DBImplementation() {
	}

	// Method to open a new connection
	private void openConnection() {
	}

	/**[USERS]**/

	// Verify that the user exists
	@Override
	public boolean verifyUser(User user) {
		// Open connection and declare a boolean to check if the user exists
		boolean exists = false;
		this.openConnection();

		try {
			// Prepares the SQL query
			stmt = con.prepareStatement(SQLUSER);
			stmt.setString(1, user.getCodU());
			// Executes the SQL query
			ResultSet rs = stmt.executeQuery();
			// If there is any result, the user exists
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

	// Verify that the user and the password matches
	@Override
	public boolean verifyUserPassword(User user) {
		// Open connection and declare a boolean to check if the password exists and matches
		boolean exists = false;
		this.openConnection();

		try {
			// Prepares the SQL query
			stmt = con.prepareStatement(SQLUSERPSW);
			stmt.setString(1, user.getCodU());
			stmt.setString(2, user.getPassword());
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
			stmt.setString(1, user.getCodU());
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

	// Gets users's information
	public User getUser(User user) {
		ResultSet rs = null;

		// Opens the connection
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLUSER);
			stmt.setString(1, user.getCodU());
			rs = stmt.executeQuery();
			while (rs.next()) {
				user.setCodU(rs.getString("codUser"));
				user.setPassword(rs.getString("psw"));
				user.setUsername(rs.getString("username"));

				if (verifyUserType(user)) {
					user.setTypeU(TypeU.ADMIN);
				} else {
					user.setTypeU(TypeU.CLIENT);
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

	// Registers a new user
	@Override
	public boolean registerUser(User user) {
		boolean register = false;

		if (!verifyUser(user)) {
			this.openConnection();
			try {
				stmt = con.prepareStatement(SQLINSERTUSER);
				stmt.setString(1, user.getCodU());
				stmt.setString(2, user.getUsername());
				stmt.setString(3, user.getPassword());
				if (stmt.executeUpdate()>0) {
					register = true;
				}
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("An error has occurred when attempting to register the user.");
				e.printStackTrace();
			}
		}
		return register;
	}
}
