package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class UserVip extends UserModel {

	private List<PlaylistModel> playlists = new ArrayList<PlaylistModel>();

	public List<PlaylistModel> getPlaylists() {
		return playlists;
	}
	
}
