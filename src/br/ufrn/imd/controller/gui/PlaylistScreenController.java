package br.ufrn.imd.controller.gui;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PlaylistScreenController implements Initializable {

	PlaylistModel playlist;

	public PlaylistModel getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistModel playlist) {
		this.playlist = playlist;
	}

	@FXML
	ListView<TrackModel> tracksListView;

	@FXML
	private Button deleteButton;

	@FXML
	private Button removeTrackButton;

	@FXML
	private Button renameButton;

	@FXML
	private Label titleLable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleLable.setText(playlist.getTitle());
		//tracksListView.getItems().clear();
		/*tracksListView.getItems().addAll(MediaPlayerController.getPlaylistController()
				.findByTitle(MediaPlayerController.getLoggedUser().getUsername(), playlist.getTitle()).getTracks());*/
	}

	@FXML
	public void removeTrack(ActionEvent event) {
		TrackModel track = tracksListView.getSelectionModel().getSelectedItem();
		MediaPlayerController.removeTrackOnPlaylist(track, playlist);
		//tracksListView.getItems().clear();
		/*tracksListView.getItems().addAll(MediaPlayerController.getPlaylistController()
				.findByTitle(MediaPlayerController.getLoggedUser().getUsername(), playlist.getTitle()).getTracks());*/
	}

	@FXML
	public void delete(ActionEvent event) {
		/*MediaPlayerController.getPlaylistController()
				.removePlaylist(MediaPlayerController.getLoggedUser().getUsername(), playlist.getTitle());*/
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	public void rename(ActionEvent event) {
		/*MediaPlayerController.getPlaylistController()
				.renamePlaylist(MediaPlayerController.getLoggedUser().getUsername(), "TESTE", playlist.getTitle());
		titleLable.setText(playlist.getTitle());*/
	}
}