package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaylistModel {

	private String title;
	private UUID uuid = UUID.randomUUID();
	private List<TrackModel> tracks = new ArrayList<TrackModel>();

	public PlaylistModel() {
	}
	
	public String getTitle() {
		return title;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}

}
