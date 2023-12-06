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
	List<UserModel> usersCommon = new ArrayList<>();
	List<UserVipModel> usersVip = new ArrayList<>();

	public UserController() {
	}

	public UserController(String path) {
		super();
		this.path = path;
	}

	public List<UserModel> getUsersCommon() {
		return usersCommon;
	}

	public List<UserVipModel> getUsersVip() {
		return usersVip;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

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
				if (userType.toUpperCase().equals("COMMON")) {
					usersCommon.add(new UserModel(name, password, username));
				} else if (userType.toUpperCase().equals("VIP")) {
					usersVip.add(new UserVipModel(name, password, username));
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUsersFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(getPath()));
			for (UserModel u : getUsersCommon()) {
				bw.write(u.getFullName());
				bw.newLine();
				bw.write(u.getUsername());
				bw.newLine();
				bw.write(u.getPassword());
				bw.newLine();
				bw.write("COMMON");
				bw.newLine();
			}
			for (UserModel u : getUsersVip()) {
				bw.write(u.getFullName());
				bw.newLine();
				bw.write(u.getUsername());
				bw.newLine();
				bw.write(u.getPassword());
				bw.newLine();
				bw.write("VIP");
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUserCommon(UserModel user) {
		getUsersCommon().add(user);
		updateUsersFile();
	}

	public void removeUserCommon(UserModel user) {
		getUsersCommon().remove(user);
		updateUsersFile();
	}

	public void addUserVip(UserVipModel user) {
		getUsersVip().add(user);
		updateUsersFile();
	}

	public void removeUserVip(UserVipModel user) {
		getUsersVip().remove(user);
		updateUsersFile();
	}

	public void renameUserFullName(UserModel user, String newName) {
		if (findUserByUsername(user.getUsername()).getFullName().equals(newName)) {
			findUserByUsername(user.getUsername()).setFullName(newName);
			updateUsersFile();
		}
	}

	public void renameUsername(UserModel user, String newUsername) {
		if (findUserByUsername(user.getUsername()).getUsername().equals(newUsername)) {
			findUserByUsername(user.getUsername()).setUsername(newUsername);
			updateUsersFile();
		}
	}

	public void changePassword(UserModel user, String newPassword) {
		if (findUserByUsername(user.getUsername()).getPassword().equals(newPassword)) {
			findUserByUsername(user.getUsername()).setPassword(newPassword);
			updateUsersFile();
		}
	}

	public UserModel findUserCommonByUsername(String username) {
		for (UserModel user : getUsersCommon()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public UserVipModel findUserVipByUsername(String username) {
		for (UserVipModel user : getUsersVip()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public UserModel findUserByUsername(String username) {
		return (findUserCommonByUsername(username) != null ? findUserCommonByUsername(username)
				: findUserVipByUsername(username));
	}

	public boolean isUserVip(String username) {
		if (findUserCommonByUsername(username) != null) {
			return false;
		}
		return true;
	}
}
