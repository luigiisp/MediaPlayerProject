package br.ufrn.imd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.TrackModel;

public class TrackController {
	List<TrackModel> tracks = new ArrayList<>();
	String path;

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
			br = new BufferedReader(new FileReader(this.path));
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

	public void addListTracks(TrackModel track) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(path, true));
			bw.newLine();
			bw.write(track.getName());
			bw.newLine();
			bw.write(track.getDirectory());
			bw.close();
			getTracks().add(track);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
