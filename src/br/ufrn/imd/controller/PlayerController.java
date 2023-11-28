package br.ufrn.imd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController {
	private int trackIndex = 0;
	PlayerModel player = new PlayerModel();
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}

	public TrackModel getCurrentTrack() {
		return player.getQueue().getTracks().get(trackIndex);
	}
	
	public void playQueue() {
		Player trackPlayer = null;
		while(!player.getQueue().getTracks().isEmpty()) {
			try {
				FileInputStream fis = new FileInputStream(getCurrentTrack().getDirectory());
				trackPlayer = new Player(fis);
				trackPlayer.play();
				System.out.println("Playing " + getCurrentTrack().getName());
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
			
			while(trackPlayer.isComplete() == false) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			trackIndex++;
			
		}
		trackIndex = 0;
		
		trackPlayer.close();
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
