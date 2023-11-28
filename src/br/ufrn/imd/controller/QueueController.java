package br.ufrn.imd.controller;

import java.util.Collections;

import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.QueueModel;
import br.ufrn.imd.model.TrackModel;

public class QueueController {
	QueueModel queue = new QueueModel();

	public QueueController(QueueModel queue) {
		super();
		this.queue = queue;
	}

	public QueueModel getQueue() {
		return queue;
	}

	public void setQueue(QueueModel queue) {
		this.queue = queue;
	}

	public void addTrack(TrackModel track) {
		queue.getTracks().add(track);
	}

	public void listTracks() {
		for (TrackModel track : queue.getTracks()) {
			System.out.println(track.getName());
		}
	}

	public void removeTrack(TrackModel track) {
		queue.getTracks().remove(track);
	}

	public void addPlaylist(PlaylistModel playlist) {
		for (TrackModel track : playlist.getTracks()) {
			queue.getTracks().add(track);
		}
	}

	public void clearQueue() {
		for (TrackModel track : queue.getTracks()) {
			queue.getTracks().remove(track);
		}
	}

	public void randomizeQueue() {
		Collections.shuffle(queue.getTracks());
	}
	
	public void skipTrack() {
		queue.setTrackIndex(queue.getTrackIndex() + 1);
		System.out.println("Skipped to the next track");
	}

	public void backTrack() {
		queue.setTrackIndex(queue.getTrackIndex() - 1);
		System.out.println("Going back to previous track");
	}
	
	public TrackModel getCurrentTrack() {
		return queue.getTracks().get(queue.getTrackIndex());
	}
}
