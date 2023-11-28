package br.ufrn.imd.controller;

import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;

public class PlaylistController {
	PlaylistModel playlist;

	public PlaylistController(PlaylistModel playlist) {
		super();
		this.playlist = playlist;
	}

	public PlaylistModel getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistModel playlist) {
		this.playlist = playlist;
	}
	
	public void renamePlaylist(String newTitle) {
		playlist.setTitle(newTitle);
	}
	
	public void addTrack(TrackModel track) {
		playlist.getTracks().add(track);
	}
	
	public void removeTrack(TrackModel track) {
		playlist.getTracks().remove(track);
	}
}
