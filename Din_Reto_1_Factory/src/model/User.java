package model;

public class User {
	// Declare variables
	private String u_username;
	private String u_name;
	private String u_password;
	private UserType u_type;

	// Declare empty constructor
	public User() {
		this.u_username = "";
		this.u_name = "";
		this.u_password = "";
		this.u_type = UserType.UNSET;
	}

	// Declare parametrized constructors
	public User(String codU, String password) {
		this.u_username = codU;
		this.u_name = "";
		this.u_password = password;
		this.u_type = UserType.CLIENT;
	}

	public User(String codU, String username, String password, UserType typeU) {
		this.u_username = codU;
		this.u_name = username;
		this.u_password = password;
		this.u_type = typeU;
	}

	// Declare getters and setters
	public String getU_username() {
		return u_username;
	}

	public void setU_username(String codU) {
		this.u_username = codU;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String username) {
		this.u_name = username;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String password) {
		this.u_password = password;
	}

	public UserType getU_type() {
		return u_type;
	}

	public void setU_type(UserType typeU) {
		this.u_type = typeU;
	}

	// Declare toString
	@Override
	public String toString() {
		return "User [Username: "+u_username+", Name: "+u_name+", Password: "+u_password+", Type: "+u_type+"]";
	}  
}
