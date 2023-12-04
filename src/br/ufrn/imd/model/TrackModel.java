package br.ufrn.imd.model;

public class TrackModel {

	private String name;
	private String directory;
	
	public TrackModel() {
	}
	
	public TrackModel(String name, String directory) {
		this.name = name;
		this.directory = directory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	
	
}
