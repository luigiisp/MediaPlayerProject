package br.ufrn.imd.controller;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;

public class PlayerController {

	PlayerModel player = new PlayerModel();
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}

	private TrackModel getCurrentTrack() {
		return player.getQueueController().getQueue().getTracks().get(player.getTrackIndex());
	}

	public void playTrack() {
		// jlayer play command
		System.out.println("Playing " + getCurrentTrack().getName());
	}

	public void unpauseTrack() {
		// jlayer unpause command
		System.out.println("Unpaused current track");
	}

	public void pauseTrack() {
		// jlayer command
		System.out.println("Paused current track");
	}

	public void skipTrack() {
		player.setTrackIndex(player.getTrackIndex() + 1);
		;
		playTrack();
		System.out.println("Skipped to the next track");
	}

	public void backTrack() {
		player.setTrackIndex(player.getTrackIndex() - 1);
		;
		playTrack();
		System.out.println("Going back to previous track");
	}

	public void loopTrack() {
	}
}
