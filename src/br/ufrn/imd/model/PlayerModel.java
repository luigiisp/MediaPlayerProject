package br.ufrn.imd.model;

public class PlayerModel {
	QueueModel queue = new QueueModel();
	private int trackIndex = 0;

	public PlayerModel() {
	}

	public PlayerModel(QueueModel queue) {
		super();
		this.queue = queue;
	}

	public QueueModel getQueue() {
		return queue;
	}

	public void setQueue(QueueModel queue) {
		this.queue = queue;
	}

	public int getTrackIndex() {
		return trackIndex;
	}

	public void setTrackIndex(int trackIndex) {
		this.trackIndex = trackIndex;
	}
}
