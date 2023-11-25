package br.ufrn.imd.model;

public class PlayerModel {
	QueueModel queue = new QueueModel();

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

	public void playTrack() {
	}

	public void pauseTrack() {
	}

	public void nextTrack() {
	}

	public void backTrack() {
	}

	public void loopTrack() {
	}

}
