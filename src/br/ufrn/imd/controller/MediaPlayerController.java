package br.ufrn.imd.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import br.ufrn.imd.model.UserModel;
import br.ufrn.imd.model.UserVipModel;

public class MediaPlayerController {
	private static UserModel loggedUser = null;
	private static QueueController queueController = new QueueController();
	private static TrackController trackController = null;
	private static UserController userController = null;
	private static PlaylistController playlistController = null;
	private static PlayerController playerController = new PlayerController(queueController);

	public static UserModel getLoggedUser() {
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
	
	public static List<TrackModel> getTracksInQueue() {
		return queueController.getQueue().getTracks();
	}
	
	//User
	
	public static QueueController getQueueController() {
		return queueController;
	}

	public static TrackController getTrackController() {
		return trackController;
	}

	public static UserController getUserController() {
		return userController;
	}

	public static PlaylistController getPlaylistController() {
		return playlistController;
	}

	public static PlayerController getPlayerController() {
		return playerController;
	}

	public static int register(String fullName, String username, String password, boolean vipUser) {
		if (userController.findUserByUsername(username) != null) {
			//Username already registered
			return 1;
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
		return 0;
		
	}

	public static int login(String username, String password) {
		if (loggedUser != null) {
			//You are already logged in. Logoff to change user
			return 1;
		}
		if (userController.findUserByUsername(username) == null) {
			//No user registered with this username
			return 2;
		}
		UserModel user = userController.findUserByUsername(username);
		if (!user.getPassword().equals(password)) {
			//Wrong password for this username
			return 3;
		}

		loggedUser = user;
		return 0;
	}
	
	public static boolean isUserVip() {
		return userController.isUserVip(getLoggedUser().getUsername());
	}
	
	//Track
	
	public static List<TrackModel> searchTrackByName(String searched) {
		List<TrackModel> foundTracks = new ArrayList<TrackModel>();
		if(searched.isBlank()) {
			return foundTracks;
		}
		
		foundTracks = trackController.getTracksByNameSubstring(searched);
		return foundTracks;
	}
	

	//Queue
	
	public static void addTrackToQueue(TrackModel track) {
		queueController.addTrack(track);
	}
	
	public static void removeTrackFromQueue(TrackModel track) {
		queueController.removeTrack(track);
	}
	
	/*
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
	*/
	public static void play() {
		playerController.playQueue();
	}
	
	public static void pause() {
		playerController.pauseTrack();
	}
	
	public static void skip() {
		playerController.skipTrack();
	}
	
	
	//Playlist
	
	public static void createPlaylist(String title) {
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
}
