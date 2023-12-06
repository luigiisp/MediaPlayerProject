package br.ufrn.imd.controller.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.PlaylistModel;
import br.ufrn.imd.model.TrackModel;
import br.ufrn.imd.model.UserVipModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
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
		if (MediaPlayerController.getLoggedUser() instanceof UserVipModel) {
			UserVipModel user = (UserVipModel) MediaPlayerController.getLoggedUser();
			playlistsListView.getItems().clear();
			playlistsListView.getItems().addAll(user.getPlaylists());

			playlistMenu.getItems().clear();
			for (int i = 0; i < user.getPlaylists().size(); i++) {
				PlaylistModel playlist = user.getPlaylists().get(i);
				MenuItem menuItem = new MenuItem(playlist.toString());
				menuItem.setOnAction((new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						TrackModel selectedTrack = searchListView.getSelectionModel().getSelectedItem();
						if (selectedTrack == null) {
							return;
						}
						MediaPlayerController.addTrackToPlaylist(selectedTrack, playlist);
					}
				}));
				playlistMenu.getItems().add(menuItem);
			}
		}
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			}
		});
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
	void onRemoveTrackFromQueuePressed(ActionEvent event) {
		TrackModel selectedTrack = queueListView.getSelectionModel().getSelectedItem();
		queueListView.getItems().remove(selectedTrack);
	}

	@FXML
	private Slider volumeSlider;

	@FXML
	private ProgressBar trackProgressBar;
	Timer timer;
	TimerTask task;

	private boolean progress;

	public void beginTimer() {

		timer = new Timer();

		task = new TimerTask() {

			public void run() {

				progress = true;
				double current = mediaPlayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				trackProgressBar.setProgress(current / end);

				if (current / end == 1) {

					cancelTimer();
				}
			}
		};

		timer.scheduleAtFixedRate(task, 1000, 1000);
	}

	public void cancelTimer() {

		progress = false;
		timer.cancel();
	}

	@FXML
	void onAddNewTrackButtonPressed(ActionEvent event) throws IOException {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {

			public String getDescription() {
				return "mp3 files (*.mp3)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".mp3");
				}
			}
		});
		int result = fc.showOpenDialog(fc);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = new File(fc.getSelectedFile().getAbsolutePath());
			if (file.getAbsolutePath().endsWith(".mp3")) {
				MediaPlayerController.getTrackController()
						.addTrack(new TrackModel(file.getName().replace(".mp3", ""), file.getAbsolutePath()));
				return;
			} else {
				return;
			}
		}
	}

	// Player
	private int currentTrackIndex = 0;
	private Media media;
	private MediaPlayer mediaPlayer;

	@FXML
	private Button playButton;

	@FXML
	private Button skipButton;

	@FXML
	private Button backButton;

	@FXML
	private Label currentTrackLabel;

	final int STOPPED = 0;
	final int PLAYING = 1;
	final int PAUSED = 2;
	int playerStatus = STOPPED;

	void playQueue() {
		if (playerStatus == STOPPED) {
			if (queueListView.getItems().size() - 1 < currentTrackIndex) {
				return;
			}
			TrackModel currentTrack = queueListView.getItems().get(currentTrackIndex);
			File file = new File(currentTrack.getDirectory());
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			beginTimer();
			mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			mediaPlayer.play();
			mediaPlayer.setOnEndOfMedia(this::onTrackEnded);
			playButton.setText("Pause");
			currentTrackLabel.setText("Playing " + currentTrack.getName());

			playerStatus = PLAYING;
		} else if (playerStatus == PLAYING) {
			cancelTimer();
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
	void onPlayButtonPressed(ActionEvent event) {
		playQueue();
	}

	void onTrackEnded() {

		mediaPlayer.stop();
		playerStatus = STOPPED;
		currentTrackLabel.setText("");
		if (queueListView.getItems().size() - 1 <= currentTrackIndex) {
			// ended queue
			currentTrackIndex = 0;
		} else {
			// there are remaining tracks
			currentTrackIndex++;
		}
		if (progress) {
			cancelTimer();
		}
		playQueue();
	}

	@FXML
	void onSkipButtonPressed(ActionEvent event) {
		onTrackEnded();
	}

	@FXML
	void onBackButtonPressed(ActionEvent event) {
		//
	}

	// Search bar
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchButton;
	@FXML
	private ListView<TrackModel> searchListView;

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
	void addTrackToQueue(ActionEvent event) {
		TrackModel selectedTrack = searchListView.getSelectionModel().getSelectedItem();
		if (selectedTrack == null) {
			return;
		}
		queueListView.getItems().add(selectedTrack);
	}

	@FXML
	private Menu playlistMenu;

	@FXML
	void closePlayer(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void listAvailableTracks(ActionEvent event) {
		String availableTracksScreenFxmlPath = "/br/ufrn/imd/view/AvailableTracksScreen.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(availableTracksScreenFxmlPath));
			Parent root1;

			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Available tracks");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void openGithub(ActionEvent event) {
		try {
			Desktop.getDesktop().browse(new URL("https://github.com/luigiisp/MediaPlayerProject").toURI());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
