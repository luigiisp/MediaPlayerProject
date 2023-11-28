package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class QueueModel {

	List<TrackModel> tracks = new ArrayList<>();
	private int trackIndex = 0;

	public QueueModel() {
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}

	public int getTrackIndex() {
		return trackIndex;
	}

	public void setTrackIndex(int trackIndex) {
		this.trackIndex = trackIndex;
	}

}
