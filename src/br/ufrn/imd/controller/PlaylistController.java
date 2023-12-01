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

	// consider removing fullname from playlist.txt
	public void updatePlaylistsList() {
		File folder = new File(path);
		File[] playlistsFiles = folder.listFiles();
		if (playlistsFiles != null) {
			for (File file : playlistsFiles) {
				if (file.isFile() && file.getName().endsWith(".txt")) {
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
						String playlistOwnerFullname = reader.readLine();
						String playlistOwnerUsername = reader.readLine();
						String playlistTitle = reader.readLine();
						String line = reader.readLine();
						PlaylistModel playlistTemp = new PlaylistModel(playlistTitle, file.getPath());
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
						addPlaylist(playlistTemp.getTitle());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void updatePlaylistsFolder() {
		int x = 0;
		for (UserVipModel u : userController.getUsersVip()) {
			for (PlaylistModel p : u.getPlaylists()) {
				File file = new File(getPath() + File.separator + "playlist" + Integer.toString(x++) + ".txt");
				p.setDirectory(file.getPath());
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(
							new FileWriter(file));
					writer.write(u.getFullName());
					writer.newLine();
					writer.write(u.getUsername());
					writer.newLine();
					writer.write(p.getTitle());
					writer.newLine();
					for (TrackModel t : p.getTracks()) {
						writer.write(t.getName());
						writer.newLine();
						writer.write(t.getDirectory());
						writer.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null) {
							writer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void renamePlaylist(String newTitle, String oldTitle) {
		if (findByTitle(oldTitle) != null) {
			findByTitle(oldTitle).setTitle(newTitle);
			updatePlaylistsFolder();
		}
	}

	public void addTrackToPlaylist(String trackName, String playlistName) {
		if (findByTitle(playlistName) != null && trackController.getTrackByName(trackName) != null) {
			findByTitle(playlistName).getTracks().add(trackController.getTrackByName(trackName));
			updatePlaylistsFolder();
		}
	}

	public void removeTrackFromPlaylist(String trackName, String playlistName) {
		if (findByTitle(playlistName) != null && trackController.getTrackByName(trackName) != null) {
			findByTitle(playlistName).getTracks().remove(trackController.getTrackByName(trackName));
			updatePlaylistsFolder();
		}
	}

	public void addPlaylist(String title) {
		if (findByTitle(title) == null) {
			playlists.add(new PlaylistModel(title));
			updatePlaylistsFolder();
		}
	}

	public void removePlaylist(String title) {
		if (findByTitle(title) != null) {
			playlists.remove(findByTitle(title));
			updatePlaylistsFolder();
		}
	}

	public PlaylistModel findByTitle(String title) {
		for (PlaylistModel playlist : getPlaylists()) {
			if (playlist.getTitle().equals(title)) {
				System.out.println("Entrou");
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
