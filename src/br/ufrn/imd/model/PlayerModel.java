package br.ufrn.imd.model;

import br.ufrn.imd.controller.QueueController;

public class PlayerModel {
	QueueController queueController;

	public PlayerModel() {
	}

	public PlayerModel(QueueModel queue) {
		super();
		this.queueController = new QueueController(queue);
	}

	public QueueController getQueueController() {
		return queueController;
	}

	public QueueModel getQueue() {
		return getQueueController().getQueue();
	}
	
	public void setQueueController(QueueController queueController) {
		this.queueController = queueController;
	}
}
