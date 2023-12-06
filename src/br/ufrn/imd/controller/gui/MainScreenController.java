package br.ufrn.imd.controller.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import br.ufrn.imd.model.UserVipModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
	@FXML
	private Button refreshPlaylistsButton;

	@FXML
	private ListView<PlaylistModel> playlistsListView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MediaPlayerController.createPlaylist("TOPS");
		if (MediaPlayerController.getLoggedUser() instanceof UserVipModel) {
			UserVipModel user = (UserVipModel) MediaPlayerController.getLoggedUser();
			playlistsListView.getItems().clear();
			playlistsListView.getItems().addAll(user.getPlaylists());
		} else {

		}
	}

	@FXML
	private Button profileButton;

	@FXML
	void onProfileButtonPressed(ActionEvent event) {
		String profileScreenFxmlPath = "/br/ufrn/imd/view/ProfileScreen.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(profileScreenFxmlPath));
			Parent root1;

			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Profile information");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private Button refreshQueueButton;

	@FXML
	private ListView<TrackModel> queueListView;

	@FXML
	private Button removeTrackFromQueue;

	@FXML
	void onRefreshQueueButtonPressed(ActionEvent event) {
		queueListView.getItems().clear();
		queueListView.getItems().addAll(MediaPlayerController.getTracksInQueue());
	}

	@FXML
	void onRemoveTrackFromQueuePressed(ActionEvent event) {
		TrackModel selectedTrack = queueListView.getSelectionModel().getSelectedItem();
		MediaPlayerController.removeTrackFromQueue(selectedTrack);
	}

	// Player
	private int currentTrackIndex = 0;
	private Media media;
	private MediaPlayer mediaPlayer;

	@FXML
	private Button playButton;

	@FXML
	private Button skipButton;

	public TrackModel getTrackByIndex(int trackIndex) {
		return MediaPlayerController.getQueueController().getQueue().getTracks().get(trackIndex);
	}

	final int STOPPED = 0;
	final int PLAYING = 1;
	final int PAUSED = 2;
	int playerStatus = STOPPED;

	@FXML
	public void onPlayButtonPressed(ActionEvent event) {

		if (playerStatus == STOPPED) {
			if (MediaPlayerController.getQueueController().getQueue().getTracks().size() - 1 < currentTrackIndex) {
				return;
			}
			TrackModel currentTrack = getTrackByIndex(currentTrackIndex);
			File file = new File(currentTrack.getDirectory());

			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			playButton.setText("Pause");

			playerStatus = PLAYING;
		} else if (playerStatus == PLAYING) {
			mediaPlayer.pause();
			playerStatus = PAUSED;
			playButton.setText("Play");
		} else if (playerStatus == PAUSED) {
			mediaPlayer.play();
			playerStatus = PLAYING;
			playButton.setText("Pause");
		}
	}

	@FXML
	void onSkipButtonPressed(ActionEvent event) {
		MediaPlayerController.skip();
	}

	@FXML
	private Label currentTrackLabel;

	// Search bar
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchButton;
	@FXML
	private ListView<TrackModel> searchListView;
	@FXML
	private Button AddToQueueButton;

	public void searchTrack() {
		final int LIST_CELL_HEIGHT = 24;

		searchListView.getItems().clear();

		List<TrackModel> foundTracks = new ArrayList<TrackModel>();
		String searchedSubstring = searchTextField.getText();
		foundTracks = MediaPlayerController.searchTrackByName(searchedSubstring);
		searchListView.getItems().addAll(foundTracks);
		if (!foundTracks.isEmpty()) {
			searchListView.setOpacity(1);
		} else {
			searchListView.setOpacity(0);
		}
		searchListView.setPrefHeight(Math.min(LIST_CELL_HEIGHT * 4 + 2, (foundTracks.size() * LIST_CELL_HEIGHT + 2)));
	}

	@FXML
	void onSearchButtonPressed(ActionEvent event) {
		searchTrack();
	}

	@FXML
	void onSearchBarKeyPressed(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			searchTrack();
			return;
		}
		if (event.getCode().equals(KeyCode.ESCAPE)) {
			searchListView.getItems().clear();
			searchListView.setOpacity(0);
		}
	}

	@FXML
	void onAddToQueueButtonPressed(ActionEvent event) {
		TrackModel selectedTrack = searchListView.getSelectionModel().getSelectedItem();
		MediaPlayerController.addTrackToQueue(selectedTrack);
	}
}
