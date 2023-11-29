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
	List<PlaylistModel> playlists = new ArrayList<>();

	public PlaylistController() {
	}

	public PlaylistController(String path) {
		super();
		this.path = path;
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

	public void updatePlaylistsList(UserController userController, TrackController trackController) {
		File folder = new File(path);
		File[] playlistsFiles = folder.listFiles();
		if (playlistsFiles != null) {
			for (File file : playlistsFiles) {
				if (file.isFile() && file.getName().endsWith(".txt")) {
					PlaylistModel playlistTemp = new PlaylistModel(file.getAbsolutePath());
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
						String nameUser = reader.readLine();
						String usernameUser = reader.readLine();
						String line = reader.readLine();
						while (line != null) {
							String trackName = line;
							for (TrackModel track : trackController.getTracks()) {
								if (track.getName() == trackName) {
									playlistTemp.getTracks().add(new TrackModel(trackName, track.getDirectory()));
								}
							}
							line = reader.readLine();
						}
						for (UserVipModel u : userController.getUsersVip()) {
							if (u.getFullName() == nameUser && u.getUsername() == usernameUser) {
								u.getPlaylists().add(playlistTemp);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					playlists.add(playlistTemp);
				}
			}
		}
	}

	public void updatePlaylistsFolder(UserController users) {
		File folder = new File(path);
		for (UserVipModel u : users.getUsersVip()) {
			for (PlaylistModel p : u.getPlaylists()) {
				File file = new File(
						folder.getAbsolutePath() + File.separator + p.getTitle().replace(" ", "") + ".txt");
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

	public void renamePlaylist(String newTitle, PlaylistModel playlist) {
		playlist.setTitle(newTitle);
	}

	public void addTrack(TrackModel track, PlaylistModel playlist) {
		playlist.getTracks().add(track);
	}

	public void removeTrack(TrackModel track, PlaylistModel playlist) {
		playlist.getTracks().remove(track);
	}

	public PlaylistModel findByTitle(String title) {
		for (PlaylistModel playlist : getPlaylists()) {
			if (playlist.getTitle().equals(title)) {
				return playlist;
			}
		}
		return null;
	}
}
