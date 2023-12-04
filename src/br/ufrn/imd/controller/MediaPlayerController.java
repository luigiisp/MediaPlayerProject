package br.ufrn.imd.controller;

import br.ufrn.imd.model.UserModel;

public class MediaPlayerController {
	private static UserModel loggedUser = null;
	private static QueueController queueController = new QueueController();
	private static TrackController trackController = null;
	private static UserController userController = null;
	private static PlaylistController playlistController = null;
	private static PlayerController playerController = new PlayerController(queueController);

	public UserModel getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(UserModel user) {
		loggedUser = user;
	}
	
	public static void setTrackController(TrackController trackC) {
		trackController = trackC;
	}


	public static void setUserController(UserController userC) {
		userController = userC;
	}

	public static void setPlaylistController(PlaylistController playlistC) {
		playlistController = playlistC;
	}
	
	/*
	//User
	
	public static void register(String fullName, String username, String password, boolean vipUser) {
		if (userController.findUserByUsername(username) != null) {
			System.out.println("Username already registered");
			return;
		}

		if (vipUser) {
			UserVipModel newUser = new UserVipModel(fullName, password, username);
			userController.addUserVip(newUser);

			loggedUser = newUser;
		} else {
			UserModel newUser = new UserModel(fullName, password, username);
			userController.addUserCommon(newUser);
			loggedUser = newUser;
		}
	}


	public void login(String username, String password) {
		if (loggedUser != null) {
			System.out.println("You are already logged in. Logoff to change user");
			return;
		}
		if (userController.findUserByUsername(username) == null) {
			System.out.println("No user registered with this username");
			return;
		}
		UserModel user = userController.findUserByUsername(username);
		if (!user.getPassword().equals(password)) {
			System.out.println("Wrong password for this username");
			return;
		}

		loggedUser = user;
	}

	//Track
	
	public void searchTrackByName(String searched) {
		List<TrackModel> tracksFound = trackController.getTracksByNameSubstring(searched);
		System.out.println("Tracks found:");
		for (TrackModel track : tracksFound) {
			System.out.println(track.getName());
		}
	}

	//Queue
	
	public void addTrackToQueue(String trackName) {
		TrackModel track = trackController.getTrackByName(trackName);
		if (track == null) {
			System.out.println("Track not found");
		}
		queueController.addTrack(track);
	}

	public void addPlaylistToQueue(String title) {
		PlaylistModel playlist = playlistController.findByTitle(loggedUser.getUsername(), title);
		if (playlist == null) {
			System.out.println("Playlist not found");
		}
		queueController.addPlaylist(playlist);
	}

	public void clearQueue() {
		playerController.getPlayer().getQueueController().clearQueue();
	}
	
	public void play() {
		playerController.playQueue();
	}

	public void skip() {
		playerController.skipTrack();
	}
	
	//Playlist
	
	public void createPlaylist(String title) {
		PlaylistModel playlist = new PlaylistModel(title, playlistController.getPath());
		if (!(loggedUser instanceof UserVipModel)) {
			System.out.println("Only vip users can create playlists");
			return;
		}

		if ((playlistController.findByTitle(loggedUser.getUsername(), playlist.getTitle())) != null) {
			System.out.println("User already has a playlist with this title");
			return;
		}
		playlistController.addPlaylist(loggedUser.getUsername(), playlist);
	}
	
	public void addTrackToPlaylist(TrackModel track, PlaylistModel playlist) {
		if(playlist.getTracks().contains(track)) {
			System.out.println("This playlist already contains this track");
			return;
		}
		playlist.getTracks().add(track);
		playlistController.updatePlaylistsFolder();
	}
	*/
}
