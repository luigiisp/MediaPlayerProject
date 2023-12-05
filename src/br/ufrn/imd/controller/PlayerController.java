package br.ufrn.imd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController {
	private int currentTrackIndex = 0;
	private PlayerModel player = new PlayerModel();
	private Player trackPlayer = null;
	
	public PlayerController() {
		super();
	}
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}
	
	public PlayerController(QueueController queueController) {
		super();
		this.player.setQueueController(queueController);
	}
	
	public PlayerModel getPlayer() {
		return player;
	}

	public TrackModel getTrackByIndex(int trackIndex) {
		return player.getQueue().getTracks().get(trackIndex);
	}
	
	public void playQueue() {
		while(!player.getQueue().getTracks().isEmpty() && currentTrackIndex < player.getQueue().getTracks().size()) {
			try {
				TrackModel currentTrack = getTrackByIndex(currentTrackIndex);
				FileInputStream fis = new FileInputStream(currentTrack.getDirectory());
				trackPlayer = new Player(fis);
				trackPlayer.play();
				
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
			//track plays until it ends
			
			currentTrackIndex++;
			while(currentTrackIndex > 10) {
				player.getQueueController().removeTrack(getTrackByIndex(0));
				currentTrackIndex--;
			}
		}
		if(trackPlayer != null) {
			trackPlayer.close();
		}
	}

	/*
	public void unpauseTrack() {
		playQueue();
		paused = false;
		System.out.println("Unpaused current track");
	}

	public void pauseTrack() {
		currentFrame = trackPlayer.getPosition();
		trackPlayer.close();
		paused = true;
		System.out.println("Paused current track");
	}
	*/
	
	public void skipTrack() {
		if(trackPlayer == null || currentTrackIndex >= player.getQueue().getTracks().size() - 1) {
			return;
		}
		trackPlayer.close();
		currentTrackIndex++;
		playQueue();
	}
	
	public void backTrack() {
		if(currentTrackIndex <= 0) {
			return;
		}
		trackPlayer.close();
		currentTrackIndex--;
		playQueue();
	}
	
	//since player removes finished songs from queue, backtrack won't work. so maybe stop removing from queue and just check songs with index greater than current
}
