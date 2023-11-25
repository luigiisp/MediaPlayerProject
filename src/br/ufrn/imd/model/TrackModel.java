package br.ufrn.imd.model;

import java.util.UUID;

public class TrackModel {

	private String name;
	private String artista;
	private UUID uuid;

	public TrackModel(String name, String artista) {
		this.name = name;
		this.artista = artista;
		this.uuid = UUID.randomUUID();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public UUID getUuid() {
		return uuid;
	}
}
