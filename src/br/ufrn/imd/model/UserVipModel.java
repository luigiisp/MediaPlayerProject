package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class UserVipModel extends UserModel {

	public UserVipModel() {
	}

	public UserVipModel(String fullName, String password, String username) {
		super(fullName, password, username);
	}

	private List<PlaylistModel> playlists = new ArrayList<PlaylistModel>();

	public List<PlaylistModel> getPlaylists() {
		return playlists;
	}
}
