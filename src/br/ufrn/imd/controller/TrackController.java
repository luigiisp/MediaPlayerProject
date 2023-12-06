package br.ufrn.imd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.TrackModel;

public class TrackController {
	private String path;
	private List<TrackModel> tracks = new ArrayList<>();

	public TrackController() {
	}

	public TrackController(String path) {
		super();
		this.path = path;
	}

	public List<TrackModel> getTracks() {
		return tracks;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// Updates tracks list
	public void updateTracksList() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(getPath()));
			String line = br.readLine();

			while (line != null) {
				TrackModel newTrack = new TrackModel();
				newTrack.setName(line);
				line = br.readLine();
				newTrack.setDirectory(line.replace("/", "\\"));
				tracks.add(newTrack);
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Updates tracks file content
	public void updateTracksFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(getPath()));
			for (TrackModel t : tracks) {
				bw.write(t.getName());
				bw.newLine();
				bw.write(t.getDirectory().replace("/", "\\"));
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTrack(TrackModel track) {
		if (getTrackByName(track.getName()) == null) {
			getTracks().add(track);
			updateTracksFile();
		}
	}

	public void removeTrack(TrackModel track) {
		if (getTrackByName(track.getName()) != null) {
			getTracks().remove(track);
			updateTracksFile();
		}
	}

	public void renameTrack(TrackModel track, String newName) {
		if (getTrackByName(track.getName()).equals(track.getName())) {
			getTrackByName(track.getName()).setName(newName);
			updateTracksFile();
		}
	}

	public TrackModel getTrackByName(String name) {
		for (TrackModel track : getTracks()) {
			if (track.getName().equals(name)) {
				return track;
			}
		}
		return null;
	}

	public List<TrackModel> getTracksByNameSubstring(String searched) {
		List<TrackModel> tracksFound = new ArrayList<TrackModel>();
		for (TrackModel track : getTracks()) {
			if (track.getName().toLowerCase().contains(searched.toLowerCase())) {
				tracksFound.add(track);
			}
		}

		return tracksFound;
	}
}
