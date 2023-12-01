package br.ufrn.imd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import br.ufrn.imd.model.UserVipModel;

public class PlaylistController {
	String path;
	UserController userController = null;
	TrackController trackController = null;
	List<PlaylistModel> playlists = new ArrayList<>();

	public PlaylistController() {
	}

	public PlaylistController(String path, UserController userController, TrackController trackController) {
		super();
		this.path = path;
		this.userController = userController;
		this.trackController = trackController;
	}

	public List<PlaylistModel> getPlaylists() {
		return playlists;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// consider removing fullname from playlisttxt
	public void updatePlaylistsList() {
		File folder = new File(path);
		File[] playlistsFiles = folder.listFiles();
		if (playlistsFiles != null) {
			for (File file : playlistsFiles) {
				if (file.isFile() && file.getName().endsWith(".txt")) {
					String[] playlistName = file.getName().split(".");
					PlaylistModel playlistTemp = new PlaylistModel(playlistName[0], file.getAbsolutePath());
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
						String playlistOwnerFullname = reader.readLine();
						String playlistOwnerUsername = reader.readLine();
						String line = reader.readLine();
						while (line != null) {
							String trackName = line;
							if (trackController.getTrackByName(trackName) != null) {
								addTrackToPlaylist(trackName, playlistTemp.getTitle());
							}
							line = reader.readLine();
						}
						UserVipModel playlistOwner = userController.findUserVipByUsername(playlistOwnerUsername);
						if (playlistOwner != null) {
							playlistOwner.getPlaylists().add(playlistTemp);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					addPlaylist(playlistTemp);
				}
			}
		}
	}

	public void updatePlaylistsFolder() {
		for (UserVipModel u : userController.getUsersVip()) {
			for (PlaylistModel p : u.getPlaylists()) {
				File file = new File(path + File.separator + p.getTitle().replace(" ", "") + ".txt");
				System.out.println(file.getAbsolutePath());
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
					writer.write(u.getFullName());
					writer.newLine();
					writer.write(u.getUsername());
					writer.newLine();
					for (TrackModel t : p.getTracks()) {
						writer.write(t.getName());
						writer.newLine();
						writer.write(t.getDirectory());
						writer.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void renamePlaylist(String newTitle, String oldTitle) {
		if (findByTitle(oldTitle) != null) {
			findByTitle(oldTitle).setTitle(newTitle);
		}
	}

	public void addTrackToPlaylist(String trackName, String playlistName) {
		if (findByTitle(playlistName) != null && trackController.getTrackByName(trackName) != null) {
			findByTitle(playlistName).getTracks().add(trackController.getTrackByName(trackName));
			updatePlaylistsFolder();
		}
	}

	public void removeTrackToPlaylist(String trackName, String playlistName) {
		if (findByTitle(playlistName) != null && trackController.getTrackByName(trackName) != null) {
			findByTitle(playlistName).getTracks().remove(trackController.getTrackByName(trackName));
			updatePlaylistsFolder();
		}
	}

	public void addPlaylist(PlaylistModel playlist) {
		playlists.add(playlist);
		updatePlaylistsFolder();
	}

	public void removePlaylist(PlaylistModel playlist) {
		playlists.remove(playlist);
		updatePlaylistsFolder();
	}

	public PlaylistModel findByTitle(String title) {
		for (PlaylistModel playlist : getPlaylists()) {
			if (playlist.getTitle().equals(title)) {
				return playlist;
			}
		}
		return null;
	}

	public void addPlaylistToUser(String title, UserVipModel userVip) {
		if (findByTitle(title) != null) {
			userController.findUserVipByUsername(userVip.getUsername()).getPlaylists().add(findByTitle(title));
			updatePlaylistsFolder();
		}
	}
}
