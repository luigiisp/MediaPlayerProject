package br.ufrn.imd.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.TrackModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainScreenController {
	
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
		searchListView.setPrefHeight((foundTracks.size() * LIST_CELL_HEIGHT + 2));		
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
