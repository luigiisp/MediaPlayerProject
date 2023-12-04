package br.ufrn.imd.controller;

import br.ufrn.imd.model.TrackModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		searchListView.getItems().clear();
		String searchedSubstring = searchTextField.getText();
		searchListView.getItems().addAll(MediaPlayerController.searchTrackByName(searchedSubstring));
	}
}
