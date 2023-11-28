package br.ufrn.imd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.UserModel;
import br.ufrn.imd.model.UserVipModel;

public class UserController {
	String path;
	List<UserModel> users = new ArrayList<>();

	public UserController() {
	}

	public UserController(String path) {
		super();
		this.path = path + ".txt";
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// Updates tracks list
	public void updateUsersList() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(getPath()));
			String line = br.readLine();

			while (line != null) {
				String name = line;
				line = br.readLine();
				String username = line;
				line = br.readLine();
				String password = line;
				line = br.readLine();
				String userType = line;
				line = br.readLine();
				if (userType.toUpperCase() == "COMMON") {
					users.add(new UserModel(name, password, username));
				} else if (userType.toUpperCase() == "VIP") {
					users.add(new UserVipModel(name, password, username));
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Updates tracks file content
	public void updateUsersFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(getPath()));
			for (UserModel u : users) {
				bw.write(u.getFullName());
				bw.newLine();
				bw.write(u.getUsername());
				bw.newLine();
				bw.write(u.getPassword());
				bw.newLine();
				if (u instanceof UserVipModel) {
					bw.write("VIP");
				} else {
					bw.write("COMMON");
				}
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUser(UserModel user) {
		getUsers().add(user);
		updateUsersFile();
	}

	public void removeUser(UserModel user) {
		getUsers().remove(user);
		updateUsersFile();
	}
}
