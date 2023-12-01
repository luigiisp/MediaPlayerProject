package br.ufrn.imd.controller;

import java.util.List;

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

	public void searchTrackByName(String searched) {
		List<TrackModel> tracksFound = trackController.getTracksByNameSubstring(searched);
		System.out.println("Tracks found:");
		for (TrackModel track : tracksFound) {
			System.out.println(track.getName());
		}
	}

	public void addTrackToQueue(String trackName) {
		TrackModel track = trackController.getTrackByName(trackName);
		if (track == null) {
			System.out.println("Track not found");
		}
		queueController.addTrack(track);
	}

	public void play() {
		playerController.playQueue();
	}

	public void skip() {
		playerController.skipTrack();
	}

	public void createPlaylist(String title) {
		if (!(loggedUser instanceof UserVipModel)) {
			System.out.println("Only vip users can create playlists");
			return;
		}

		if (((UserVipModel) loggedUser).getPlaylists().contains(playlistController.findByTitle(title))) {
			
		}
		playlistController.addPlaylist(title);
		playlistController.addPlaylistToUser(title, (UserVipModel) loggedUser);

	}
}
