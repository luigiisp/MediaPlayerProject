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
	@FXML
	private Button playButton;
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchButton;
    @FXML
    private ListView<TrackModel> searchListView;
	
	@FXML
	public void onPlayButtonPressed(ActionEvent event) {
		
	}

	//Search bar
	
	public void searchTrack() {
		final int LIST_CELL_HEIGHT = 25;
		
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
		searchListView.setPrefHeight(foundTracks.size() * LIST_CELL_HEIGHT);		
	}
	
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		searchTrack();
	}
	
	@FXML
    void onKeyPressed(KeyEvent event) {
		if(event.getCode().equals(KeyCode.ENTER)) {
			searchTrack();
		}
    }
}
