package br.ufrn.imd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

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
		Player player = null;
		try {
			FileInputStream fis = new FileInputStream(getCurrentTrack().getDirectory());
			player = new Player(fis);
			player.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		} finally {
			player.close();
		}
		
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
