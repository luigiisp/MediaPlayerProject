package br.ufrn.imd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.model.PlayerModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController {

	PlayerModel player = new PlayerModel();
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}

	public void playTrack() {
		Player trackPlayer = null;
		try {
			FileInputStream fis = new FileInputStream(player.getQueueController().getCurrentTrack().getDirectory());
			trackPlayer = new Player(fis);
			trackPlayer.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		} finally {
			trackPlayer.close();
		}
		
		System.out.println("Playing " + player.getQueueController().getCurrentTrack().getName());
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
		player.getQueueController().skipTrack();
		playTrack();
		System.out.println("Skipped to the next track");
	}

	public void backTrack() {
		player.getQueueController().backTrack();
		playTrack();
		System.out.println("Going back to previous track");
	}

	public void loopTrack() {
	}
}
