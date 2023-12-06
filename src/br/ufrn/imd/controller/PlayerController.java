package br.ufrn.imd.controller;

import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.TrackModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerController {
	private int currentTrackIndex = 0;
	private PlayerModel player = new PlayerModel();
	private Media media;
	private MediaPlayer mediaPlayer;

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
}
