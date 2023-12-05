package br.ufrn.imd.controller.gui;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.TrackModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainScreenController {
	
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
	
	//Player
	@FXML
	private Button playButton;
	
    @FXML
    private Button skipButton;
    
	@FXML
	public void onPlayButtonPressed(ActionEvent event) {
		MediaPlayerController.play();
	}
	
    @FXML
    void onSkipButtonPressed(ActionEvent event) {
    	MediaPlayerController.skip();
    }
    
    @FXML
    private Label currentTrackLabel;

	//Search bar
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
		if(!foundTracks.isEmpty()) {
			searchListView.setOpacity(1);
		} else {
			searchListView.setOpacity(0);
		}
		searchListView.setPrefHeight(Math.min(LIST_CELL_HEIGHT*4 + 2, (foundTracks.size() * LIST_CELL_HEIGHT + 2)));		
	}
	
	@FXML
	void onSearchButtonPressed(ActionEvent event) {
		searchTrack();
	}
	
	@FXML
    void onSearchBarKeyPressed(KeyEvent event) {
		if(event.getCode().equals(KeyCode.ENTER)) {
			searchTrack();
			return;
		}
		if(event.getCode().equals(KeyCode.ESCAPE)) {
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
