package br.ufrn.imd.controller;

import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import br.ufrn.imd.model.UserModel;
import br.ufrn.imd.model.UserVipModel;

public class MediaPlayerController {
	private UserModel loggedUser = null;
	private QueueController queueController = new QueueController();
	private TrackController trackController = null;
	private UserController userController = null;
	private PlaylistController playlistController = null;
	private PlayerController playerController = new PlayerController(queueController);

	public MediaPlayerController(TrackController trackController, PlaylistController playlistController,
			UserController userController) {
		super();
		this.trackController = trackController;
		this.playlistController = playlistController;
		this.userController = userController;
	}
	
	public UserModel getLoggedUser() {
		return loggedUser;
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
	
	public void searchTrackByName(String searched) {
		System.out.println("Tracks found: ");
		for(TrackModel track : trackController.getTracks()) {
			if(track.getName().contains(searched)) {
				System.out.println("- " + track.getName());
			}
		}
	}
	
	public void addTrackToQueue(String trackName) {
		TrackModel track = trackController.getTrackByName(trackName);
		if(track == null) {
			System.out.println("Track not found");
		}
		queueController.addTrack(track);
	}
	
	public void play() {
		playerController.playQueue();
	}
	
	public void createPlaylist(String title) {
		if(loggedUser instanceof UserVipModel) {
			playlistController.addPlaylist(new PlaylistModel(playlistController.getPath()));
		}
	}
}
