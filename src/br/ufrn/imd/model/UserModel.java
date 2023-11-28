package br.ufrn.imd.model;

public class UserModel {

	private String fullName;
	private String password;
	private String username; // unique identifier

	public UserModel() {
	}

	public UserModel(String fullName, String password, String username) {
		super();
		this.fullName = fullName;
		this.password = password;
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
