package br.ufrn.imd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.TrackModel;

public class TrackController {
	String path;
	List<TrackModel> tracks = new ArrayList<>();

	public TrackController() {
	}

	public TrackController(String path) {
		super();
		this.path = path + ".txt";
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

	public void updateListTracks() {
		BufferedReader br = null;
		TrackModel newTrack = new TrackModel();
		try {
			br = new BufferedReader(new FileReader(getPath()));
			String line = br.readLine();

			while (line != null) {
				newTrack.setName(line);
				line = br.readLine();
				newTrack.setDirectory(line);
				tracks.add(newTrack);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTracks() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(getPath()));
			for (TrackModel t : tracks) {
				bw.write(t.getName());
				bw.newLine();
				bw.write(t.getDirectory());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addListTracks(TrackModel track) {
		getTracks().add(track);
		updateTracks();
	}

	public void removeTrack(TrackModel track) {
		getTracks().remove(track);
		updateTracks();
	}
}
