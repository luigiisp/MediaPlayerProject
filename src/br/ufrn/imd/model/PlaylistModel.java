package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class PlaylistModel {

	private String title;
	private String directory;
	private List<TrackModel> tracks = new ArrayList<TrackModel>();

	public PlaylistModel() {
	}

	public PlaylistModel(String title, String directory) {
		this.title = title;
		this.directory = directory;
	}

	public PlaylistModel(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}
}
