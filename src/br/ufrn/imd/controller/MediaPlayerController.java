package br.ufrn.imd.controller;

import br.ufrn.imd.model.UserModel;
import br.ufrn.imd.model.UserVipModel;

public class MediaPlayerController {
	UserModel loggedUser = null;
	TrackController trackController = new TrackController();
	QueueController queueController = new QueueController();
	PlaylistController playlistController = new PlaylistController();
	UserController userController = new UserController();

	public MediaPlayerController() {
		super();
	}

	public void register(String fullName, String username, String password, boolean vipUser) {
		if (userController.findUserByUsername(username) != null) {
			System.out.println("Username already registered");
			return;
		}
		
		if(vipUser) {
			UserVipModel newUser = new UserVipModel(fullName, username, password);
			userController.addUserVip(newUser);
			
			loggedUser = newUser;
		} else {
			UserModel newUser = new UserModel(fullName, username, password); 
			userController.addUserCommon(newUser);
			loggedUser = newUser;
		}		
	}

	public void login(String username, String password) {
		if (userController.findUserByUsername(username) == null) {
			System.out.println("No user registered with this username");
			return;
		}
		UserModel user = userController.findUserByUsername(username);
		if (user.getPassword() != password) {
			System.out.println("Wrong password for this username");
			return;
		}

		loggedUser = user;
	}
}
