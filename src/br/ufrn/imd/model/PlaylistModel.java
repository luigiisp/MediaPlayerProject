package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaylistModel {

	private UUID uuid = UUID.randomUUID();
	private List<TrackModel> tracks = new ArrayList<TrackModel>();

	public PlaylistModel() {
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}

}
