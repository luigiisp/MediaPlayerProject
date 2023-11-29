package br.ufrn.imd.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerController {
	private int trackIndex = 0;
	private int currentFrame = 0;
	private boolean paused = false;
	private PlayerModel player = new PlayerModel();
	private Player trackPlayer = null;
	
	public PlayerController() {
		super();
	}
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}
	
	public PlayerModel getPlayer() {
		return player;
	}

	public TrackModel getCurrentTrack() {
		return player.getQueue().getTracks().get(trackIndex);
	}
	
	public void playQueue() {
		while(!player.getQueue().getTracks().isEmpty() && paused == false) {
			try {
				FileInputStream fis = new FileInputStream(getCurrentTrack().getDirectory());
				trackPlayer = new Player(fis);
				System.out.println("Playing " + getCurrentTrack().getName());
				trackPlayer.play(currentFrame);
				currentFrame = 0;
				
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
			//track plays until it ends
			
			player.getQueueController().removeTrack(getCurrentTrack());
			trackIndex++;
		}
		trackIndex = 0;
		trackPlayer.close();
	}

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
	
	public void skipTrack() {
		trackIndex++;
		trackPlayer.close();
		System.out.println("Skipped track");
	}
	
	/*
	public void backTrack() {
		
		int positionOffset = 50000;
		if(trackPlayer.getPosition() > positionOffset) {
			
		}
	}
	*/
	
	//since player removes finished songs from queue, backtrack won't work. so maybe stop removing from queue and just check songs with index greater than current
}
