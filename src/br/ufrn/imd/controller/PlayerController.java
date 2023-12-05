package br.ufrn.imd.controller;

import java.io.File;

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

	public void playQueue() {
		while (!player.getQueue().getTracks().isEmpty() && currentTrackIndex < player.getQueue().getTracks().size()) {

			TrackModel currentTrack = getTrackByIndex(currentTrackIndex);
			File file = new File(currentTrack.getDirectory());

			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();

			// track plays until it ends

			currentTrackIndex++;
			while (currentTrackIndex > 10) {
				player.getQueueController().removeTrack(getTrackByIndex(0));
				currentTrackIndex--;
			}
		}
	}

	public void pauseTrack() {
		mediaPlayer.pause();
	}

	public void skipTrack() {
		if (mediaPlayer == null || currentTrackIndex >= player.getQueue().getTracks().size() - 1) {
			return;
		}
		mediaPlayer.stop();
		currentTrackIndex++;
		playQueue();
	}

	public void backTrack() {
		if (mediaPlayer == null || currentTrackIndex <= 0) {
			return;
		}
		mediaPlayer.stop();
		currentTrackIndex--;
		playQueue();
	}

	// since player removes finished songs from queue, backtrack won't work. so
	// maybe stop removing from queue and just check songs with index greater than
	// current
}
