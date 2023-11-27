package br.ufrn.imd.model;

import br.ufrn.imd.controller.QueueController;

public class PlayerModel {
	QueueController queueController;
	private int trackIndex = 0;

	public PlayerModel() {
	}

	public PlayerModel(QueueModel queue) {
		super();
		this.queueController = new QueueController(queue);
	}

	public QueueController getQueueController() {
		return queueController;
	}

	public void setQueueController(QueueController queueController) {
		this.queueController = queueController;
	}

	public int getTrackIndex() {
		return trackIndex;
	}

	public void setTrackIndex(int trackIndex) {
		this.trackIndex = trackIndex;
	}
}
