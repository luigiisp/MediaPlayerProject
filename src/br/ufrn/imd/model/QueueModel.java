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
}
