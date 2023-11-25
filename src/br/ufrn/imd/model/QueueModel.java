package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class QueueModel {

	List<TrackModel> tracks = new ArrayList<>();

	public QueueModel() {
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}

	// adds specific track to the queue
	public void addTrack() {
	}

	// removes specific track from the queue
	public void removeTrack() {
	}

	// adds all the tracks from the playlist to the queue
	public void addPlaylist() {
	}

	// resets the queue by removing all tracks
	public void reset() {
		// for each track, removetrack
	}

	// randomly sorts the tracks in the queue
	public void randomize() {

	}
}
