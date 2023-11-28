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
	
	public PlayerController(PlayerModel player) {
		super();
		this.player = player;
	}

	public TrackModel getCurrentTrack() {
		return player.getQueue().getTracks().get(trackIndex);
	}
	
	public void playQueue() {
		Player trackPlayer = null;
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
			while(trackPlayer.isComplete() == false) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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

	public void pauseTrack(Player trackPlayer) {
		currentFrame = trackPlayer.getPosition();
		trackPlayer.close();
		paused = true;
		System.out.println("Paused current track");
	}
}
