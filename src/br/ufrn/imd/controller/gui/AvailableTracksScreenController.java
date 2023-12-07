package br.ufrn.imd.controller.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.TrackModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class AvailableTracksScreenController implements Initializable {
	@FXML
	private ListView<TrackModel> availableTracksListView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		availableTracksListView.getItems().clear();
		availableTracksListView.getItems().addAll(MediaPlayerController.getAllAvailableTracks());
	}
	
}
