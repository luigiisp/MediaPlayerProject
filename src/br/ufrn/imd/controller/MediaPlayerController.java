package br.ufrn.imd.controller;

import br.ufrn.imd.model.UserModel;

public class MediaPlayerController {
	UserModel loggedUser = null;
	TrackController trackController = new TrackController();
	QueueController queueController = new QueueController();
	PlaylistController playlistController = new PlaylistController();
	UserController userController = new UserController();

	public void register(String fullName, String username, String password) {
		if (userController.findUserByUsername(username) != null) {
			System.out.println("Username already registered");
			return;
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
